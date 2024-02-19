import java.io.*;
import java.util.*;

public class PirateLangInterpreter {
    // List to store instructions read from the file
    private List<String> instructions;
    // Stack to perform operations on, following LIFO principle
    private Stack<Integer> stack;
    // Program counter to keep track of the current instruction
    private int pc;

    // Constructor that initializes the interpreter with instructions from a file
    public PirateLangInterpreter(String fileName) throws IOException {
        this.instructions = new ArrayList<>();
        this.stack = new Stack<>();
        this.pc = 0;
        loadInstructions(fileName);
    }

    // Loads instructions from a given file into the instructions list
    private void loadInstructions(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            instructions.add(line);
        }
        reader.close();
    }

    // Executes a single instruction based on its type
    private void executeInstruction(String instruction, String[] parts) {
        switch (instruction) {
            case "ARR":
                // Pushes a number onto the stack
                stack.push(Integer.parseInt(parts[1]));
                break;
            case "PLUNDER":
                // Duplicates the top value of the stack
                if (!stack.isEmpty()) {
                    stack.push(stack.peek());
                } else {
                    System.err.println("Error: Stack is empty");
                }
                break;
            case "MAROON":
                // Removes the top value of the stack
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                break;
            case "PARLEY":
                // Takes a digit as input and pushes it onto the stack
                try {
                    System.out.println("Enter a digit:");
                    Scanner scanner = new Scanner(System.in);
                    int inputDigit = scanner.nextInt();
                    stack.push(inputDigit);
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a digit.");
                }
                break;
            case "HEAVEHO":
                // Takes a line of input and pushes each character's ASCII value onto the stack
                try {
                    System.out.println("Enter a line of characters:");
                    Scanner scanner = new Scanner(System.in);
                    String inputLine = scanner.nextLine();
                    for (char c : inputLine.toCharArray()) {
                        stack.push((int) c);
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred while reading input.");
                }
                break;
            case "GALLEON":
                // Pops and prints characters from the stack until it is empty
                while (!stack.isEmpty()) {
                    System.out.print((char) stack.pop().intValue());
                }
                break;
            case "HOIST":
                // Pops the top value of the stack and prints it as an integer
                if (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
                break;
            case "JOLLYROGER":
                // Pops the top value of the stack and prints it as a character
                if (!stack.isEmpty()) {
                    System.out.print((char) stack.pop().intValue());
                }
                break;
            case "CANNON":
                // Multiplies the two topmost values on the stack and pushes the result
                if (stack.size() < 2) {
                    throw new IllegalStateException("Stack underflow during multiplication");
                }
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
                break;
            case "KEELHAUL":
                // Terminates the program execution
                pc = instructions.size();
                break;
        }
    }

    // Main loop that runs the program by executing instructions until the end is reached
    public void run() {
        while (pc < instructions.size()) {
            String instructionLine = instructions.get(pc).trim();
            if (!instructionLine.isEmpty()) {
                String[] parts = instructionLine.split("\\s+");
                executeInstruction(parts[0], parts);
            }
            pc++; // Increment the program counter
        }
    }

    // Used to test example files.
    public static void main(String[] args) {
        // Example programs to be executed
        String[] programs = {"helloWorld.ahoy", "multiply.ahoy", "reverseString.ahoy"};
        for (String program : programs) {
            try {
                System.out.println("=================================");
                System.out.println("Executing program: " + program);
                PirateLangInterpreter interpreter = new PirateLangInterpreter(program);
                interpreter.run();
                System.out.println();
            } catch (IOException e) {
                System.err.println("Failed to load the PirateLang program: " + e.getMessage());
            }
        }
    }
}