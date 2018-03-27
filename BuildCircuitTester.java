import java.util.Map;
import java.util.HashMap;

public class BuildCircuitTester {

    public static void main(String[] args) {
        try {
            boolean pass = true;
            /* Test: A */
            Map<String,GateInputSingle> vars = new HashMap<>();
            GateOutput out = CircuitBuilder.buildCircuit("A", vars);
            pass = pass && (out.output()==false);
            vars.get("A").input(true);
            pass = pass && (out.output()==true);

            /* Test: AA* */
            vars.clear();
            out = CircuitBuilder.buildCircuit("AA*", vars);
            pass = pass && (out.output()==false);
            vars.get("A").input(true);
            pass = pass && (out.output()==true);


            /* Test: TF+ */
            vars.clear();
            out = CircuitBuilder.buildCircuit("TF+", vars);
            pass = pass && (out.output()==true);

            /* Test: A-A* */
            vars.clear();
            out = CircuitBuilder.buildCircuit("A-A*", vars);
            pass = pass && (out.output()==false);
            vars.get("A").input(true);
            pass = pass && (out.output()==false);

            /* Test: ABC+-* */
            vars.clear();
            out = CircuitBuilder.buildCircuit("ABC+-*", vars);
            pass = pass && (out.output()==false);
            vars.get("C").input(true);
            pass = pass && (out.output()==false);
            vars.get("B").input(true);
            pass = pass && (out.output()==false);
            vars.get("C").input(false);
            pass = pass && (out.output()==false);
            vars.get("A").input(true);
            pass = pass && (out.output()==false);
            vars.get("C").input(true);
            pass = pass && (out.output()==false);
            vars.get("B").input(false);
            pass = pass && (out.output()==false);
            vars.get("C").input(false);
            pass = pass && (out.output()==true);

            /* Test: A--B--C--+--*-- */
            vars.clear();
            out = CircuitBuilder.buildCircuit("ABC--+--*--", vars);
            pass = pass && (out.output()==false);
            vars.get("C").input(true);
            pass = pass && (out.output()==false);
            vars.get("B").input(true);
            pass = pass && (out.output()==false);
            vars.get("C").input(false);
            pass = pass && (out.output()==false);
            vars.get("A").input(true);
            pass = pass && (out.output()==true);
            vars.get("C").input(true);
            pass = pass && (out.output()==true);
            vars.get("B").input(false);
            pass = pass && (out.output()==true);
            vars.get("C").input(false);
            pass = pass && (out.output()==false);

            System.out.println("buildCircuit: " + pass);
        }
        catch(Exception e) {
            System.out.println("buildCircuit: false");
        }
    }
}