/**
 * ============================================================================
 * File Name : CustomExceptionExample.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Custom Exception in Java
 * ============================================================================
 *
 * WHAT IS A CUSTOM EXCEPTION?
 * ---------------------------
 *
 * Java provides many built-in exceptions like:
 *
 *      ArithmeticException
 *      NullPointerException
 *      IOException
 *
 * But sometimes these exceptions
 * are not suitable for business requirements.
 *
 * In such cases,
 * we create our own exception class.
 *
 * This is called
 *
 *          Custom Exception
 *
 * ============================================================================
 * WHY DO WE NEED CUSTOM EXCEPTIONS?
 * ============================================================================
 *
 * Imagine a Banking Application.
 *
 * Rule:
 *
 * Customer cannot withdraw money
 * greater than available balance.
 *
 * Throwing
 *
 *      ArithmeticException
 *
 * doesn't clearly explain the problem.
 *
 * Instead,
 *
 * create
 *
 *      InsufficientBalanceException
 *
 * which clearly describes the business rule.
 *
 * ============================================================================
 * HOW TO CREATE A CUSTOM EXCEPTION?
 * ============================================================================
 *
 * Step 1
 *
 * Create a class
 *
 * Step 2
 *
 * Extend Exception
 *      (Checked Exception)
 *
 * OR
 *
 * Extend RuntimeException
 *      (Unchecked Exception)
 *
 * Step 3
 *
 * Call super(message)
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * ATM Machine
 *
 * Balance = ₹5000
 *
 * Customer wants ₹8000
 *
 * ATM displays
 *
 * "Insufficient Balance"
 *
 * instead of
 *
 * "Something Went Wrong"
 *
 * That's exactly what
 * Custom Exceptions do.
 *
 * ============================================================================
 */

/*
 * ------------------------------------------------------------------------
 * CUSTOM EXCEPTION CLASS
 * ------------------------------------------------------------------------
 *
 * This exception represents
 * a business-specific problem.
 */

class InsufficientBalanceException extends Exception {

    /*
     * Constructor
     *
     * Pass message to parent class.
     */

    public InsufficientBalanceException(String message) {

        super(message);
    }
}

/*
 * ------------------------------------------------------------------------
 * BANK ACCOUNT
 * ------------------------------------------------------------------------
 */

class BankAccount {

    /*
     * Initial Balance
     */

    private double balance = 5000;

    /*
     * Withdraw Method
     */

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        /*
         * Business Validation
         */

        if (amount > balance) {

            /*
             * Throw Custom Exception
             */

            throw new InsufficientBalanceException(

                    "Withdrawal Failed! Insufficient Balance."
            );
        }

        balance -= amount;

        System.out.println("Withdrawal Successful.");

        System.out.println("Remaining Balance : ₹" + balance);
    }
}

/*
 * ------------------------------------------------------------------------
 * DRIVER CLASS
 * ------------------------------------------------------------------------
 */

public class CustomExceptionExample {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        try {

            /*
             * Try withdrawing more
             * than available balance.
             */

            account.withdraw(8000);

        }

        /*
         * Catch Custom Exception
         */

        catch (InsufficientBalanceException e) {

            System.out.println();

            System.out.println("Custom Exception Caught");

            System.out.println();

            System.out.println(e.getMessage());
        }
    }
}

/*
===============================================================================

OUTPUT

Custom Exception Caught

Withdrawal Failed! Insufficient Balance.

===============================================================================

EXECUTION FLOW

Application Starts

↓

Create BankAccount

↓

withdraw(8000)

↓

Balance Check

↓

Amount > Balance ?

↓

YES

↓

Throw InsufficientBalanceException

↓

Catch Block Executes

↓

Print Exception Message

===============================================================================

HOW DOES throw WORK?

throw

↓

Creates Exception Object

↓

Stops Current Method

↓

Transfers Control

↓

Nearest Matching catch Block

===============================================================================

WHEN SHOULD WE CREATE CUSTOM EXCEPTIONS?

✔ Banking Applications

✔ E-Commerce Applications

✔ Student Management Systems

✔ Hospital Management Systems

✔ Employee Management Systems

✔ Business Rule Validation

===============================================================================

INTERVIEW QUESTIONS

Q) What is a Custom Exception?

Answer

A Custom Exception is a user-defined exception
created to represent business-specific errors
that built-in Java exceptions cannot describe clearly.

-------------------------------------------------------------------------------

Q) How do you create a Custom Exception?

Answer

1. Create a class.

2. Extend Exception
   OR RuntimeException.

3. Call super(message).

-------------------------------------------------------------------------------

Q) Why do we call super(message)?

Answer

It passes the error message
to the parent Exception class,
which allows us to retrieve it
using getMessage().

-------------------------------------------------------------------------------

Q) Which class should we extend?

Answer

Extend Exception

→ Checked Custom Exception

Extend RuntimeException

→ Unchecked Custom Exception

-------------------------------------------------------------------------------

Q) Can a Custom Exception be Checked?

Answer

Yes.

If it extends Exception.

-------------------------------------------------------------------------------

Q) Can a Custom Exception be Unchecked?

Answer

Yes.

If it extends RuntimeException.

-------------------------------------------------------------------------------

Q) Why use Custom Exceptions?

Answer

✔ Better Readability

✔ Business-specific Errors

✔ Easy Debugging

✔ Better Maintenance

✔ Professional Code

===============================================================================

CHECKED vs UNCHECKED CUSTOM EXCEPTION

class MyCheckedException
        extends Exception

↓

Compiler forces handling.

------------------------------------------------

class MyUncheckedException
        extends RuntimeException

↓

Compiler does not force handling.

===============================================================================

INTERVIEW TIP

Java Interviewers often ask:

"Why not throw Exception directly?"

Best Answer:

Throwing generic Exception
doesn't explain the actual problem.

A Custom Exception clearly represents
the business rule being violated,
making the code more readable,
maintainable, and easier to debug.

===============================================================================

 */