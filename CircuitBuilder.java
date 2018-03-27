import java.util.Stack;
import java.util.Map;
import java.util.HashMap;


/**
 * A simple logic gate simulator. This class allows users
 * to specify a simple circuit in plain text, and it then simulates
 * the logic of their inputs.
 *
 * Sacramento State CSC20
 * Project # 4 attempt 2 submission 2
 *
 * @author Ryan Kozak.
 * @version May 17th, 2017.
 */
public class CircuitBuilder {

    /**
     *  Takes an infix expression as input and returns the equivalent
     *  postfix expression constructed by following Dijkstra's Shunting-Yard algorithm.
     *
     *  For Example: A*-(B+C) the method should return ABC+-*
     *
     *  @return String postFix
      */

    public static String toPostfix(String infix) {

        String postfix = "";

        boolean first = false;

        Stack<Character> s = new Stack<>();

        for(int c = 0; c < infix.length(); c++) {

            char curr = infix.charAt(c);

            // It is not an operator if it's an alphabetical character.
            if(Character.isAlphabetic(curr)) {

                postfix += curr; // Send it to the postfix string.

                if(c == (infix.length()-1)) { //If it's the last character in the string
                    while(!s.empty())  // Pop the remaining operators off the stack
                        postfix += s.pop();
                }
            }
            else {

                if(first) {

                    if(curr=='*') { // If equal or lower priority
                        while( !s.isEmpty() && (s.peek()=='-' || s.peek()=='*'))
                            postfix += s.pop();
                        s.push(curr);
                    }
                    else if(curr=='-') { // If still equal priority

                        while( c > (infix.length()-1) && s.peek()=='-')
                            postfix += s.pop();
                        s.push(curr);

                    } else if(curr=='+') {

                        while( !s.isEmpty() && (s.peek()=='-' || s.peek()=='*' || s.peek()=='+') )
                            postfix += s.pop();

                        s.push(curr);

                    } else if(curr=='(') {

                        s.push(curr);

                    } else if(curr==')') {

                        while( !s.isEmpty() && s.peek()!='(' )
                            postfix += s.pop();

                        s.pop(); // Pop off that other (

                        if(s.isEmpty())  //we may have emptied the stack
                            first = false;

                    }

                    if(c == (infix.length()-1)) { //Check if last
                        while(!s.empty())
                            postfix += s.pop();
                    }

                } else { // nothing else is first in stack after this
                    s.push(curr);
                    first = true;
                }
            }
        }
        return postfix;
    }


    /**
     *  Takes a postfix expression and builds an  equivalent circuit in memory.
     *  This method returns a reference to the logic gate that produces the circuit's output.
     *
     * @param postfix String
     * @param variables GateInputSingle
     * @return GateOutput
     */
    public static GateOutput buildCircuit(String postfix, Map<String,GateInputSingle> variables) {

        Stack<GateOutput> s = new Stack<>(); // Stack of operands.
        Map<String, Buffer> buffer_map = new HashMap<>();

        for(int c = 0; c < postfix.length(); c++) {

            char curr = postfix.charAt(c);

            if(Character.isAlphabetic(curr)) { // Handle all variables and T/F declarations.

                Buffer buffy = new Buffer();

                if(curr=='T' || curr=='t') {
                    buffy.input(true);
                    s.push(buffy);
                }

                else if(curr=='F' || curr=='f') {
                    buffy.input(false);
                    s.push(buffy);
                }

                else if(variables.containsKey(curr+"")) {
                    s.push(buffer_map.get(curr+""));

                }
                else {
                    variables.put(curr + "", buffy);
                    buffer_map.put(curr + "", buffy);
                    s.push(buffy);
                }


            } else if(curr=='-') { // If operator is an Or Gate.

                Not not = new Not(); // Create Not gate because of '-'.

                not.input(s.pop()); // The input of the Not gate, is the buffer.

                s.push(not); // Push the "Not->Buffer" logic onto the stack.

            } else if(curr=='*') { // If operator is an And Gate.

                And and = new And(); // Create the And Gate.

                and.input2(s.pop()); // Input 2 tied to gate on stack
                and.input1(s.pop()); // Input 1 tied to gate on stack.

                s.push(and); // Push And logic of these two gates from back to stack.

            } else if(curr=='+') { // Handle Or Logic.

                Or or = new Or(); // Create an Or gate.

                or.input1(s.pop()); // Input 1 tied to gate on stack.
                or.input2(s.pop()); // Input 2 tied to gate on stack.

                s.push(or); // Push Or logic from these two gates back onto the stack.
            }
        }
        return s.pop(); // Return output logic.
    }


    // Specific test for bug fix
    public static void main(String[] args) {
        Map<String,GateInputSingle> vars = new HashMap<>();
        GateOutput out = CircuitBuilder.buildCircuit("AA*", vars);
        vars.get("A").input(true);
        System.out.println(out.output());
        vars.get("A").input(false);
        System.out.println(out.output());
    }

}
