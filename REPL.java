import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


/**
 * The REPL class serves as the Logic Simulator's read-evaluate-print-loop.
 *
 * Users are issued a prompt for either a logical expression to evaluate, or a variable
 * assignment. Inorder to leave the loop users may input "exit".
 *
 *  Sacramento State CSC20
 *  Project # 5
 *
 * @author Ryan Kozak
 * @version May 17th, 2017
 */

public class REPL {

    private Scanner sc;
    private Map<String,GateInputSingle> vars;
    private String expression;
    private String postfix;

    // User Interface Settings
    private static final String WELCOME = "Welcome to Circuit Master 2000";
    private static final String SENTINEL = "exit";
    private static final String GOODBYE = "Goodbye!";
    private static final String INPUT_PROMPT = "> ";


    /**
     * Constructor for REPL, initializes with a false postfix expression and enters loop.
     */
    public REPL() {

        vars = new HashMap<>();
        sc = new Scanner(System.in);

        expression = "";
        postfix = "F";

        loop();
    }


    /* Private method runs the Read Evaluate Print loop */
    private void loop() {

        System.out.println(WELCOME); // say hi.

        GateOutput out = CircuitBuilder.buildCircuit(postfix, vars); // Create output logic.

        while(!expression.equalsIgnoreCase(SENTINEL)) { // until exit is entered.

            System.out.print(INPUT_PROMPT);  // print > without new line.

            expression = sc.nextLine(); // scan for user input string.

            if(!expression.contains("=")) { // if the string is not a variable assignment.
                vars.clear(); // clear existing gate variables.
                postfix = CircuitBuilder.toPostfix(expression); // convert the input to postfix under the assumption it's a properly built infix logic expression.
                out = CircuitBuilder.buildCircuit(postfix, vars); // create new circuit output logic.
                System.out.println(out.output()); // output simulated logic to the user.
            }

            else { // assuming it is a variable assignment

                String variable = expression.substring(0, 1); // assume expression is 3 characters, 1 is the variable.
                String value = expression.substring(2, 3); // character 3 is the t/f expression value for that variable.

                // Output error if t/f are variable names, or if the variable  does not exist in the map.
                if(variable.equalsIgnoreCase("T") || variable.equalsIgnoreCase("F") || !vars.containsKey(variable)) {
                    System.out.println("error");
                }

                else {

                    vars.remove(value); // remove old value.

                    if(value.equalsIgnoreCase("T"))
                        vars.get(variable).input(true); // Assign mapped variable a true input
                    else
                        vars.get(variable).input(false); // Assign mapped variable a false input

                    System.out.println(out.output()); // Output logic for postfix expression with new map input values.
                }

            }
        }

        System.out.println(GOODBYE); // say bye.
    }


    /**
     * Creates an instance of REPL and enters loop.
     */
    public static void main(String[] args) {
        new REPL();
    }

}
