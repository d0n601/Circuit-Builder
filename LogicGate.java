/**
 * This class simulates a generic Logic Gate. It implements all of the
 * interfaces, GateInputSingle, GateInputDouble, and GateOutput.
 *
 * The LogicGate class is intended to be extended to the other gates
 * so that code repetition is minimized.
 *
 * https://en.wikipedia.org/wiki/Logic_gate
 *
 * @author Ryan Kozak
 * @version March 6th, 2017
 */
public class LogicGate implements  GateInputSingle, GateInputDouble, GateOutput {


    /* Inputs that are booleans */
    boolean input1;
    boolean input2;

    /* Flags set if GateInput is a GateOutput */
    private boolean flag1;
    private boolean flag2;

    /* Inputs that are Logic Gates */
    private GateOutput input1_g;
    private GateOutput input2_g;


    /**
     * Constructor for the And gate. Sets inputs to false,
     * sets flags to false.
     */
    public LogicGate() {
        input1 = false;
        input2 = false;
        flag1  = false; // true means input is another gate's output
        flag2  = false; // true means input is another gate's output
    }

    /**
     * Input for GateInputSingle with a boolean parameter.
     * @param i boolean
     */
    public void input(boolean i){
        flag1 = false; // input is not tied to another gate
        input1 = i;
    }

    /**
     * Input for GateInputSingle where parameter is a GateOutput
     * @param i boolean
     */
    public void input(GateOutput i){
        flag1 = true; // input is not tied to another gate
        input1_g = i; // save the input as the gate, not a boolean
    }

    /**
     * First input for GateInputDouble, with boolean parameter.
     * @param i boolean
     */
    public void input1(boolean i) {
        flag1 = false; // input is not tied to another gate
        input1 = i;
    }

    /**
     * First input for GateInputDouble, with a GateOutput parameter.
     * @param i GateOutput
     */
    public void input1(GateOutput i) {
        flag1 = true; // input is another gate's output
        input1_g = i; // save the input as the gate, not a boolean
    }

    /**
     * Second input for the GateInputDouble, with boolean parameter.
     * @param i boolean
     */
    public void input2(boolean i) {
        flag2 = false;
        input2 = i;
    }

    /**
     * Second input for GateInputDouble, with a GateOutput parameter.
     * @param i GateOutput
     */
    public void input2(GateOutput i) {
        flag2 = true; // input is another gate's output
        input2_g = i; // save the input as the gate, not a boolean
    }

    /**
     * Returns false, because this is not an actual LogicGate,
     * just the skeleton of one, so it has no logic...
     *
     * Child classes will apply specific gate logic by overriding this method.
     *
     * @return boolean
     */
    public boolean output() {
        return false;
    }

    /**
     *  This method sets the gate input logic to boolean values
     *  for gates that have inputs tied to the outputs of other logic gates.
     */
    void input_flags() {
        if(flag1)
            input1 = input1_g.output();
        if(flag2)
            input2 = input2_g.output();
    }

}

