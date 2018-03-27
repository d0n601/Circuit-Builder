/**
 * This class simulates the "Or" Logic Gate by extending
 * the LogicGate class.
 *
 * https://en.wikipedia.org/wiki/OR_gate
 *
 * @author Ryan Kozak
 * @version March 6th, 2017
 */
public class Or extends LogicGate {

    /**
     * Output Logic for an "Or Gate" (true if either input is true).
     * @return boolean
     */
    public boolean output() {

        input_flags();

        if(input1 || input2)
            return true;

        return false;

    }

}
