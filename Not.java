/**
 * This class simulates the "Not" Logic Gate by extending
 * the LogicGate class.
 *
 * https://en.wikipedia.org/wiki/NOT_gate
 *
 * @author Ryan Kozak
 * @version March 6th, 2017
 */
public class Not extends LogicGate {

    /**
     * Output logic for a "Not Gate" (input logic is inverted and then output).
     * @return boolean
     */
    public boolean output() {
        input_flags();
        return !input1;
    }

}
