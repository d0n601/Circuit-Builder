/**
 * Buffer to hold input value, input logic = output logic
 *
 * @author Ryan Kozak
 * @version May 17th, 2017
 */
public class Buffer implements GateInputSingle, GateOutput {


    /* Inputs that are booleans */
    private boolean input;

    /* Flags set if GateInput is a GateOutput */
    private boolean flag;

    /* Inputs that are Logic Gates */
    private GateOutput input_g;

    /**
     * Constructor for the Buffer gate.
     */
    public Buffer() {
        input = false;
        flag  = false;
    }

    public void input(boolean i){
        flag = false; // input is not tied to another gate
        input = i;
    }

    /**
     * Input for GateInputSingle where parameter is a GateOutput
     * @param i boolean
     */
    public void input(GateOutput i){
        flag = true; // input is not tied to another gate
        input_g = i; // save the input as the gate, not a boolean
    }

    /**
     * Output logic for a buffer.
     * @return boolean
     */
    public boolean output() {
        input_flag();
        return input;
    }


    /**
     *  This method sets the gate input logic to boolean values
     *  for gates that have inputs tied to the outputs of other logic gates.
     */
    void input_flag() {
        if(flag)
            input = input_g.output();

    }

}
