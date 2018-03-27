/**
 * This class simulates the "And" Logic Gate by extending
 * the LogicGate class.
 *
 * https://en.wikipedia.org/wiki/AND_gate
 *
 * @author Ryan Kozak
 * @version March 6th, 2017
 */
public class And extends LogicGate {

    /**
     * Output logic for an "And Gate" (true only if both inputs are true).
     * @return boolean
     */
    public boolean output() {

        input_flags();

        if(input1 && input2)
            return true;

        return false;

    }

}
