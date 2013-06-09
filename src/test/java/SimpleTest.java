import org.junit.Test;
import peal.domain.Predicate;
import peal.domain.Rule;
import scala.collection.Seq;
import scala.collection.mutable.ListBuffer;

import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    @Test
    public void test() {
        Double d = Double.valueOf("1.2");
        System.out.println(d);

        List<Rule> r = new ArrayList<Rule>();

        r.add(new Rule(new Predicate("a", ""), 0.4));

        System.out.println(r);
    }
}
