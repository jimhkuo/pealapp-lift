package code.comet

import code.lib._
import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import peal.domain.z3.PealAst
import peal.synthesis.analysis._

trait MainBody extends CometActor{

  val inputForNewSynthesis = "POLICIES\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.4\n" +
    "b2 = max ((q4 0.1) (q5 0.5) (q6 0.8)) default 0.9\n" +
    "b3 = + ((q7 0.1) (q8 0.3) (q9 0.6)) default 0\n" +
    "b4 = * ((q10 0.3) (q11 0.6) (q12 0.7)) default 1\n" +
    "POLICY_SETS\n" +
    "pSet1 = max(b1, b2)\n" +
    "pSet2 = min(b3, b4)\n" +
    "CONDITIONS\n" +
    "cond1 = pSet1 <= pSet2\n" +
    "cond2 = pSet2 < pSet1\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = always_true? cond2\n"

  val socialNetworkExample = "POLICIES\n% policy that accumulates trust indicators for a PayPal type payment transaction\nb1 = + ((lowCostTransaction 0.3) (enoughMutualFriends 0.1) (enoughMutualFriendsNormalized 0.2)) default 0\n% policy that extracts the worst possible signal for distrusting such a transaction\nb2 = min ((highCostTransaction 0.1) (aFriendOfAliceUnfriendedBob 0.2) (aFriendOfAliceVouchesForBob 0.6)) default 1\nPOLICY_SETS\n% policy set that conservatively (given threshold type th < cond below) combines both policies above\npSet1 = min(b1,b2)\nCONDITIONS\n% asking whether combined trust score is above a certain threshold\ncond1 = 0.5 < pSet1\n% asking whether the combined trust score is above a higher such threshold\ncond2 = 0.6 < pSet1\nDOMAIN_SPECIFICS\n% the amount of the pondered online transaction\n(declare-const amountAlicePays Real)\n% the number of friends that payer and payee share\n(declare-const numberOfMutualFriends Real)\n% number of friends of payee\n(declare-const numberOfBobsFriends Real)\n% definition of what a low cost transaction represents\n(assert (= lowCostTransaction (< amountAlicePays 100)))\n% definition of what a high cost transaction represents\n% note that these transaction types are not logical negations of each other\n(assert (= highCostTransaction (< 1000 amountAlicePays)))\n% definition of that it means to have enough mutual friends for this intended transaction\n(assert (= enoughMutualFriends (< 4 numberOfMutualFriends)))\n% a variant of such a definition that may be more resilient to abnormal values, e.g. for celebrities\n(assert (= enoughMutualFriendsNormalized (< numberOfBobsFriends (* 100 numberOfMutualFriends))))\nANALYSES\n% asking whether the combined trust score can exceed threshold 0.5\nname1 = satisfiable? cond1\n% asking whether the above condition is always true, which would suggest a specification error\nname2 = always_true? cond1\n% asking whether the above condition is always false, which would suggest a specification error\nname3 = always_false? cond1\n% asking whether one threshold behavior implies another, should be the case as 0.5 < 0.6 holds\nname4 = implies? cond2 cond1\n% asking whether both threshold behaviors are equivalent, this may be true or false in general\n% but it is false in this instance\nname5 = equivalent? cond1 cond2"
  val carRentalExample = "POLICIES\n% policy capturing risk of financial loss dependent on type of rented car\nb1 = max ((isLuxuryCar 150000) (isSedan 60000) (isCompact 30000)) default 50000\n% policy capturing trust in rentee dependent on type of his or her driving license\n% trust score for 'hasOtherLicense' contains non-deterministic uncertainty and so is in [0.3,0.5]\nb2 = min ((hasUSLicense 0.9) (hasUKLicense 0.6) (hasEULicense 0.7) (hasOtherLicense 0.4 [-0.1,0.1])) default 0\n% policy that captures potential risk dependent on type of intended car usage\n% this policy happens not to be used in the conditions below\nb3 = max ((someOffRoadDriving 0.8) (onlyCityUsage 0.4) (onlyLongDistanceUsage 0.2) (mixedUsage 0.25)) default 0.3\n% policy that accumulates some signale that may serve as additional trust indicators\nb4 = + ((accidentFreeForYears 0.05*x) (speaksEnglish 0.05) (travelsAlone -0.2) (femaleDriver 0.1)) default 0\n% the next policy is just defining a 'constant' -1, to be used as sub-expression in a policy set\nb_minOne = + () default -1\nPOLICY_SETS\n% policy set that 'converts' the trust expressed in b2 into risk\npSet0 = +(b2,b_minOne)\n% policy set that multiplies risk with potential financial loss\npSet1 = *(b1,pSet0)\n% casting policy p4 into a policy set\npSet_b4 = b4\nCONDITIONS\n% condition that the risk aware potential financial loss is below a certain bound\ncond1 = pSet1 <= 50000\n% condition that the accumulated trust is above a certain threshold\ncond2 = 0.4 < pSet_b4\n% condition that insists that two previous conditions have to hold\ncond3 = cond1 && cond2\n% variant of condition cond2 with a higher threshold\ncond4 = 0.6 < pSet_b4\n% variant of condition cond3 for that higher threshold\ncond5 = cond1 && cond4\nDOMAIN_SPECIFICS\n% real x models the number of years driven without accident, has to be non-negative and is 'truncated' at value 10\n(assert (and (<= 0 x) (<= x 10)))\n% capturing a company policy: luxury cars must not be used for off road driving \n(assert (or (not isLuxuryCar) (not someOffRoadDriving)))\n% capturing that the different types of rental cars are mutually exclusive\n(assert (and (implies isLuxuryCar (and (not isSedan) (not isCompact))) (implies isSedan (and (not isLuxuryCar) (not isCompact))) (implies isCompact (and (not isSedan) (not isLuxuryCar)))))\n% capturing that cars that are only used in cities are not used in a mixed sense\n(assert (implies onlyCityUsage (not mixedUsage)))\n% capturing that cars used only for longdistance driving are not used in a mixed sense\n(assert (implies onlyLongDistanceUsage (not mixedUsage)))\n% capturing domain constraints (or company policy?) that city driving cannot happen off road\n(assert (implies onlyCityUsage (not someOffRoadDriving)))\n% capturing that cars used only for longdistance driving must drive off road\n(assert (implies onlyLongDistanceUsage (not someOffRoadDriving)))\nANALYSES\n% is condition cond3 satisfiable?\nname1 = satisfiable? cond3\n% is condition cond3 always true? this would suggest a specification error\nname2 = always_true? cond3\n% is condition cond3 always false? this also would suggest a specification error\nname3 = always_false? cond3\n% is condition cond5 satisfiable?\nname4 = satisfiable? cond5\n% are conditions cond3 and cond5 equivalent?\nname5 = equivalent? cond3 cond5"
  var inputPolicies = socialNetworkExample
  var majorityVotingCount = 10
  var randomModelParam = "5, 5, 4, 3, 2, 7, 0.5, 0.1"
  var randomModelWithRangeParam = "2, 4, 3, 2, 1, 6, 0.5, 0.1"
  var randomModelParamWithDomain = "2, 3, 1, 1, 1, 9, 0.5, 0.1"
  var uploadFile = ""


  def generateContents: RenderOut = {
      <div>
        <form class="lift:form.ajax">
          <div class="row">
            <div class="col-sm-12">
            <h4>1. Generate PEALT input:</h4>
            <h6>Enter policies, policy sets, conditions and analyses in the text area or generate such input by clicking one of the blue buttons.</h6>
            <div>
              {SHtml.ajaxButton("Social Network Access Control example", () => {this ! ResetConstantInput; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
              {SHtml.ajaxButton("Car Rental Risks example", () => {this ! ResetNonConstantInput; _Noop}, "class" -> "btn btn-info btn-sm", "style" -> "margin:2px;")}
              {SHtml.ajaxButton("Majority-voting sample, n =", () => {this ! ResetMajorityVotingInput; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
              {SHtml.ajaxText(majorityVotingCount.toString, s => {majorityVotingCount = s.toInt; _Noop}, "id" -> "n", "size" -> "10")}
            </div>
            <div>
              {SHtml.ajaxButton("Random sample without domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! GenerateConstantScoreInput; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;") }
              {SHtml.ajaxText(randomModelParam, s => {randomModelParam = s; _Noop}, "id" -> "r", "size" -> "30")}
            </div>
            <div>
              {SHtml.ajaxButton("Random sample with domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! GenerateConstantScoreWithDomainSpecifics; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
              {SHtml.ajaxText(randomModelParamWithDomain, s => {randomModelParamWithDomain = s; _Noop}, "id" -> "r", "size" -> "30")}
            </div>
            <div>
              {SHtml.ajaxButton("Random sample with ranges: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! GenerateInputWithRange; _Noop}, "class" -> "btn btn-info btn-sm", "style" -> "margin:2px;")}
              {SHtml.ajaxText(randomModelWithRangeParam, s => {randomModelWithRangeParam = s; _Noop}, "id" -> "r", "size" -> "30")}
            </div>
            <div>
              {SHtml.ajaxButton("Clear text area", () => {this ! Clear; _Noop}, "class" -> "btn btn-warning btn-sm", "style" -> "margin:2px;")}
            </div>
            </div>
          </div>
          <div class="row" style="margin-top: 5px">
            <div class="col-sm-7">
              {SHtml.ajaxTextarea(inputPolicies, s => {inputPolicies = s; _Noop}, "id" -> "policies", "class" -> "form-control", "rows" -> "20")}
            </div>
            <div class="col-sm-5">
              <h4>2. Analyze PEALT input:</h4>
              <h6>Choose method of code generation for analyses. Then choose analysis option by clicking any of the green buttons.</h6>
              <ul class="nav nav-tabs">
                <li class="active"><a href="#explicit" class="nonScore" data-toggle="tab">Non-negative, constant scores</a></li>
                <li><a href="#extended" data-toggle="tab" class="generalScore">General scores</a></li>
              </ul>
            </div>
            <div class="tab-content">
              <div class="tab-pane active" id="explicit">
                <div class="col-sm-5">
                  {SHtml.ajaxButton("Display results of all analyses in pretty printed form and perform independent certification of results", () => {this ! SynthesisAndCallZ3QuietAnalysis; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
                  {SHtml.ajaxButton("Generate, show, and run Z3 code; display results in pretty-printed and raw form", () => {this ! RunAndCertifyExplicitResults; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
                  {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in raw Z3 form", () => {this ! ExplicitSynthesisAndCallZ3; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
                </div>
              </div>
              <div class="tab-pane" id="extended">
                <div class="col-sm-5">
                  {SHtml.ajaxButton("Display results of all analyses in pretty printed form and perform independent certification of results", () => {this ! SynthesisExtendedAndCallZ3QuietAnalysis; _Noop}, "class" -> "btn btn-info btn-sm", "style" -> "margin:2px;")}
                  {SHtml.ajaxButton("Generate, show, and run Z3 code; display results in pretty-printed and raw form", () => {this ! RunAndCertifyExtendedResults; _Noop}, "class" -> "btn btn-info btn-sm", "style" -> "margin:2px;")}
                  {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in raw Z3 form", () => {this ! ExtendedSynthesisAndCallZ3; _Noop}, "class" -> "btn btn-info btn-sm", "style" -> "margin:2px;")}
                </div>
              </div>
            </div>
          </div>
          <div class="row" style="margin-top: 2px">
            <div class="col-sm-12" id="result"></div>
          </div>
        </form>
      </div>
    }


}
