# PirateLang
Welcome to PirateLang, a stack-based esoteric programming language inspired by the high seas.

## Getting Started

To run PirateLang programs, you'll need the PirateLangInterpreter. This interpreter reads `.ahoy` files containing PirateLang instructions and executes them.

## Instructions

- `ARR n`: Pushes the integer `n` onto the stack.
- `PLUNDER`: Duplicates the top value of the stack.
- `MAROON`: Pops the top value off the stack.
- `PARLEY`: Prompts for a digit input and pushes it onto the stack.
- `HEAVEHO`: Reads a line of characters and pushes their ASCII codes onto the stack.
- `GALLEON`: Pops and prints characters from the stack until it's empty.
- `HOIST`: Pops the top value of the stack and prints it as an integer.
- `JOLLYROGER`: Pops the top value of the stack and prints it as a character.
- `CANNON`: Multiplies the two topmost values on the stack and pushes the result.
- `KEELHAUL`: Terminates the program.

## Writing Your First Program

Hello World in PirateLang:

ARR 72
JOLLYROGER
ARR 101
JOLLYROGER
...
ARR 33
JOLLYROGER
KEELHAUL

markdown
Copy code

This program pushes ASCII codes for "Hello, World!" onto the stack and prints them as characters.

## Input and Output

- Use `PARLEY` for numeric input and `HEAVEHO` for string input.
- Use `HOIST`, `JOLLYROGER`, and `GALLEON` for output.

## Running Programs

To run a PirateLang program, ensure the PirateLangInterpreter is set up and pass the filename of your `.ahoy` program as an argument.
