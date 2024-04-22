import java.io.*;
import java.util.*;

public class PirateLangInterpreter {
    // Use Deque to define the stack
    private List<String> instructions;
    private Deque<Integer> stack;
    private int pc;

    public PirateLangInterpreter(String fileName) throws IOException {
        this.instructions = new ArrayList<>();
        this.stack = new ArrayDeque<>();
        this.pc = 0;
        loadInstructions("examples/" + fileName);
    }

    private void loadInstructions(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            instructions.add(line);
        }
        reader.close();
    }

    private void executeInstruction(String instruction, String[] parts) {
        switch (instruction) {
            case "ARR":
                stack.push(Integer.parseInt(parts[1]));
                break;
            case "PLUNDER":
                if (!stack.isEmpty()) {
                    stack.push(stack.peek());
                } else {
                    System.err.println("Error: Stack is empty");
                }
                break;
            case "MAROON":
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                break;
            case "PARLEY":
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
                while (!stack.isEmpty()) {
                    System.out.print((char) stack.pop().intValue());
                }
                break;
            case "HOIST":
                if (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
                break;
            case "JOLLYROGER":
                if (!stack.isEmpty()) {
                    System.out.print((char) stack.pop().intValue());
                }
                break;
            case "CANNON":
                if (stack.size() < 2) {
                    throw new IllegalStateException("Stack underflow during multiplication");
                }
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
                break;
            case "KEELHAUL":
                pc = instructions.size();
                break;
            case "CHARGE":
                try {
                    System.out.println("Enter a character:");
                    Scanner scanner = new Scanner(System.in);
                    String inputCharacter = scanner.nextLine();
                    if (inputCharacter.length() == 1) {
                        stack.push((int) inputCharacter.charAt(0));
                    } else if (inputCharacter.length() == 1) {
                        System.err.println("Try again, please enter a character");
                    } else {
                        System.err.println("Try again, please only enter one character");
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred while reading input.");
                }
                break;
            case "BROADSIDE":
                if (stack.size() == 2) {
                    int count = stack.pop();
                    int charValue = stack.pop();
                    char character = (char) charValue;
                    for (int i = 0; i < count; i++) {
                        System.out.print(character);
                    }
                } else {
                    System.err.println("Error: Insufficient data on stack for KLED operation.");
                }
                break;
            case "COMEABOUT":
                Deque<Integer> tempStack = new ArrayDeque<>();
                while (!stack.isEmpty()) {
                    tempStack.push(stack.pop());
                }
                stack = tempStack;
                break;
        }
    }

    public void run() {
        while (pc < instructions.size()) {
            String instructionLine = instructions.get(pc).trim();
            if (!instructionLine.isEmpty()) {
                String[] parts = instructionLine.split("\\s+");
                executeInstruction(parts[0], parts);
            }
            pc++;
        }
    }

    public static void main(String[] args) {
        String[] programs = { "helloWorld.ahoy", "multiply.ahoy", "reverseString.ahoy", "repeater.ahoy", "cat.ahoy" };
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