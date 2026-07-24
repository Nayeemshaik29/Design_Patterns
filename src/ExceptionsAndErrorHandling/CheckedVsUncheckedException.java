/**
 * ============================================================================
 * File Name : CheckedVsUncheckedException.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Checked vs Unchecked Exceptions in Java
 * ============================================================================
 *
 * WHAT IS AN EXCEPTION?
 * ---------------------
 *
 * An Exception is an unexpected event
 * that occurs during program execution
 * and interrupts the normal flow of the program.
 *
 * Example:
 *
 *      Divide by Zero
 *
 *      File Not Found
 *
 *      Invalid Array Index
 *
 * ============================================================================
 * TYPES OF EXCEPTIONS
 * ============================================================================
 *
 * Java divides exceptions into two categories.
 *
 *          Exception
 *               │
 *      ┌────────┴────────┐
 *      ▼                 ▼
 * Checked          Unchecked
 * Exception        Exception
 *
 * ============================================================================
 * WHAT IS A CHECKED EXCEPTION?
 * ============================================================================
 *
 * Checked Exceptions are checked
 * at COMPILE TIME.
 *
 * The compiler forces the programmer
 * to either:
 *
 *      Handle the exception
 *
 * OR
 *
 *      Declare it using throws.
 *
 * Otherwise,
 * the program will NOT compile.
 *
 * ============================================================================
 * EXAMPLES
 * ============================================================================
 *
 * IOException
 *
 * SQLException
 *
 * FileNotFoundException
 *
 * ClassNotFoundException
 *
 * ============================================================================
 * WHAT IS AN UNCHECKED EXCEPTION?
 * ============================================================================
 *
 * Unchecked Exceptions are NOT checked
 * by the compiler.
 *
 * They occur during Runtime.
 *
 * Compiler does NOT force handling.
 *
 * ============================================================================
 * EXAMPLES
 * ============================================================================
 *
 * ArithmeticException
 *
 * NullPointerException
 *
 * ArrayIndexOutOfBoundsException
 *
 * NumberFormatException
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Checked Exception
 * -----------------
 *
 * Before driving a car,
 * you MUST wear a seatbelt.
 *
 * If you don't,
 * you cannot start.
 *
 * Compiler behaves the same way.
 *
 * ============================================================================
 *
 * Unchecked Exception
 * -------------------
 *
 * You are allowed to drive.
 *
 * But if you drive carelessly,
 * an accident may happen.
 *
 * Runtime behaves like this.
 *
 * ============================================================================
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckedVsUncheckedException {

    public static void main(String[] args) {

        System.out.println("========== CHECKED EXCEPTION ==========\n");

        /*
         * CHECKED EXCEPTION EXAMPLE
         *
         * FileReader may throw
         * FileNotFoundException.
         *
         * Therefore compiler forces
         * us to handle it.
         */

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("student.txt"));

            System.out.println(reader.readLine());

            reader.close();

        } catch (IOException e) {

            System.out.println("Checked Exception Handled");

            System.out.println(e);
        }

        System.out.println();

        System.out.println("========= UNCHECKED EXCEPTION =========\n");

        /*
         * UNCHECKED EXCEPTION
         *
         * Compiler allows this code.
         *
         * Exception happens only
         * at Runtime.
         */

        try {

            int number = 10;

            int result = number / 0;

            System.out.println(result);

        } catch (ArithmeticException e) {

            System.out.println("Unchecked Exception Handled");

            System.out.println(e);
        }

    }
}

/*
===============================================================================

OUTPUT

========== CHECKED EXCEPTION ==========

Checked Exception Handled

java.io.FileNotFoundException: student.txt

===================================================

========= UNCHECKED EXCEPTION =========

Unchecked Exception Handled

java.lang.ArithmeticException: / by zero

===============================================================================

HIERARCHY

                         Throwable
                             │
               ┌─────────────┴─────────────┐
               ▼                           ▼
             Error                     Exception
                                           │
                           ┌───────────────┴──────────────┐
                           ▼                              ▼
                  Checked Exception             RuntimeException
                                                        │
                                                        ▼
                                             Unchecked Exception

===============================================================================

COMPARISON

+-------------------------+------------------------------+
| Checked Exception       | Unchecked Exception          |
+-------------------------+------------------------------+
| Checked at Compile Time | Checked at Runtime           |
| Must Handle             | Handling Optional            |
| Compiler Enforces       | Compiler Doesn't Enforce     |
| Extends Exception       | Extends RuntimeException     |
| Recoverable             | Mostly Programming Mistakes  |
+-------------------------+------------------------------+

===============================================================================

COMMON CHECKED EXCEPTIONS

✔ IOException

✔ SQLException

✔ FileNotFoundException

✔ InterruptedException

✔ ClassNotFoundException

===============================================================================

COMMON UNCHECKED EXCEPTIONS

✔ ArithmeticException

✔ NullPointerException

✔ NumberFormatException

✔ ArrayIndexOutOfBoundsException

✔ IllegalArgumentException

===============================================================================

INTERVIEW QUESTIONS

Q) What is the difference between Checked and Unchecked Exceptions?

Answer:

Checked Exceptions are verified by the compiler and
must be handled or declared using throws.

Unchecked Exceptions occur at runtime and
are not checked by the compiler.

-------------------------------------------------------------------------------

Q) Which exception is checked?

Answer

IOException

SQLException

InterruptedException

-------------------------------------------------------------------------------

Q) Which exception is unchecked?

Answer

NullPointerException

ArithmeticException

NumberFormatException

-------------------------------------------------------------------------------

Q) Why are Checked Exceptions called Checked?

Answer

Because the Java Compiler checks them
during compilation.

-------------------------------------------------------------------------------

Q) Why are Unchecked Exceptions called Unchecked?

Answer

Because the Compiler does not verify them.

They occur during Runtime.

-------------------------------------------------------------------------------

Q) Can we handle Unchecked Exceptions?

Answer

Yes.

Using try-catch.

But compiler doesn't force us.

-------------------------------------------------------------------------------

Q) Which class is the parent of all Exceptions?

Answer

Throwable

===============================================================================

INTERVIEW TIP

Throwable
│
├── Error
│      (OutOfMemoryError, StackOverflowError)
│
└── Exception
       │
       ├── Checked Exception
       │
       └── RuntimeException
               │
               └── Unchecked Exception

Remember:

✔ Error → JVM Problems

✔ Checked Exception → Recoverable

✔ Unchecked Exception → Programming Mistakes

===============================================================================

 */