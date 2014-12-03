The PEALT tool is a workbench for articulating and analyzing policies that aggregate trust evidence. Such policy-based evidence is then meant to inform decision making in a variety of contexts such as access control, policy making, and risk management. PEALT is implemented by Jim Huan-Pu Kuo and the researchers on this project are Michael Huth and Jim Huan-Pu Kuo.

PEALT allows users to formulate conditions as the basis of decision making (be it online or offline), and to statically analyze such conditions with two principal aims:

to determine whether specified conditions and the policies they contain meet expectations of how perceived or observed risk and trust influence decision making
to validate that the expectations that users have do not have unintended consequences when expressed and enforced in such conditions.
The tool PEALT is written to reflect the methodology of auto-interactive verification. This means users can rely on automated verification tools that provide easily comprehended feedback which may trigger subsequent automated verification. And this process would be repeated until users are satisfied to have captured conditions as desired.

The input language for PEALT gives users two options:

to write policies whose scores are all non-negative and constant and where all policies with composition operator * (multiplication) have scores in the unit interval [0,1]; users can then analyze conditions by clicking on the dark blue button "Non-negative, constant scores"
to write policies whose scores may contain negative numbers, variables, products of constants and variables or uncertainty intervals; users then have to click on the light blue button "General scores" in order to analyze conditions.
The editor and runner interface of the tool offer buttons for generating analyzable conditions. Those buttons coulored in light blue should only be analyzed by clicking on the light blue button "General scores". Buttons coulored in dark blue may be analyzed by clicking either "General scores" or "Non-negative, constant scores".

Using option "Non-negative, constant scores" for PEALT input that has negative scores, non-constant scores or scores outside of the unit interval in * policies will produce errors. Fortunately, these errors are likely to be identified; for example, during parsing if scores contain variables or during certification if * policies contain scores not in [0,1]. Users should take care to only use that option when the input is known to meet the above score constraints. At the same time, since certification of found scenarios is agnostic to the manner in which the Z3 code was generated for finding this scenario, a successfully certified scenario will be valid even if the wrong option was clicked; this will only occur for examples with no variables in scores.

The description of the available analyzes, of the parameters n, m, m_min etc. used in the Random sample buttons of PEALT, and of the Z3 code generation for the PEALT input language with non-negative, constant scores can be found in the paper

Huth M., Kuo H.-P. J., PEALT: An Automated Reasoning Tool for Numerical Aggregation of Trust Evidence, proceedings of International Conference on Tools and Algorithms for the Construction and Analysis of Systems (TACAS 2014), pp. 109-123, Springer

An informal description of the PEALT input language for general scores can be found in the paper

Huth M. and Kuo H.-P. J., On Designing Usable Policy Languages for Declarative Trust Aggregation, Best Paper Award, proceedings of 2nd International Conference on Human Aspects of Information Security, Privacy, and Trust, pp. 45-56, part of HCII 2014, Springer

Contact information:

If you have any comments or queries about the tool PEALT, or if you would like to contribute a case study written in the PEALT input language, please get in touch with Michael Huth at m.huth@imperial.ac.uk
