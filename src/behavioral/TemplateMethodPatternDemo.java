/*
===============================================================================

                    TEMPLATE METHOD DESIGN PATTERN

Definition:
------------
Template Method Pattern defines the skeleton of an algorithm in a superclass
while allowing subclasses to override specific steps without changing the
overall algorithm.

The superclass controls the execution flow, while subclasses customize only
the required parts.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose a Notification System sends different types of notifications.

Every notification follows the same process:

✔ Check Rate Limit
✔ Validate Recipient
✔ Format Message
✔ Audit Log
✔ Compose Message
✔ Send Message
✔ Update Analytics

The overall workflow is fixed, but composing and sending messages differs for:

✔ Email
✔ SMS
✔ Push Notification

Template Method keeps the workflow common while allowing subclasses to
customize specific steps.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                    ABSTRACT CLASS

Defines the Template Method.

Contains:

✔ Common Steps
✔ Template Method
✔ Hooks
✔ Abstract Methods

===============================================================================
*/

abstract class NotificationSender {

    /*
    ===============================================================

    Template Method

    Final so subclasses cannot change the workflow.

    ===============================================================
    */

    public final void send(String to, String rawMessage) {

        rateLimitCheck(to);

        validateRecipient(to);

        String formattedMessage = formatMessage(rawMessage);

        preSendAuditLog(to, formattedMessage);

        String composedMessage =
                composeMessage(formattedMessage);

        sendMessage(to, composedMessage);

        postSendAnalytics(to);
    }

    /*
    ===============================================================

    Common Step

    ===============================================================
    */

    private void rateLimitCheck(String to) {

        System.out.println(
                "Checking Rate Limits for : " + to);
    }

    /*
    ===============================================================

    Common Step

    ===============================================================
    */

    private void validateRecipient(String to) {

        System.out.println(
                "Validating Recipient : " + to);
    }

    /*
    ===============================================================

    Common Step

    ===============================================================
    */

    private String formatMessage(String message) {

        return message.trim();
    }

    /*
    ===============================================================

    Common Step

    ===============================================================
    */

    private void preSendAuditLog(
            String to,
            String formattedMessage) {

        System.out.println(
                "Audit Log : "
                        + formattedMessage
                        + " -> "
                        + to);
    }

    /*
    ===============================================================

    Steps customized by subclasses

    ===============================================================
    */

    protected abstract String composeMessage(
            String formattedMessage);

    protected abstract void sendMessage(
            String to,
            String message);

    /*
    ===============================================================

    Hook Method

    Can be overridden.

    ===============================================================
    */

    protected void postSendAnalytics(String to) {

        System.out.println(
                "Analytics Updated for : " + to);
    }
}

/*
===============================================================================

                CONCRETE CLASS

Email Notification

===============================================================================
*/

class EmailNotification extends NotificationSender {

    @Override
    protected String composeMessage(
            String formattedMessage) {

        return "<html><body><p>"
                + formattedMessage
                + "</p></body></html>";
    }

    @Override
    protected void sendMessage(
            String to,
            String message) {

        System.out.println(
                "Sending EMAIL to "
                        + to
                        + "\n"
                        + message);
    }
}

/*
===============================================================================

                CONCRETE CLASS

SMS Notification

===============================================================================
*/

class SMSNotification extends NotificationSender {

    @Override
    protected String composeMessage(
            String formattedMessage) {

        return "[SMS] " + formattedMessage;
    }

    @Override
    protected void sendMessage(
            String to,
            String message) {

        System.out.println(
                "Sending SMS to "
                        + to
                        + "\n"
                        + message);
    }

    /*
    ===============================================================

    Override Hook

    ===============================================================
    */

    @Override
    protected void postSendAnalytics(String to) {

        System.out.println(
                "Custom SMS Analytics for : "
                        + to);
    }
}

/*
===============================================================================

                        CLIENT

Uses different notification senders.

Workflow remains the same.

Only specific steps change.

===============================================================================
*/

public class TemplateMethodPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Email Notification

        ===============================================================
        */

        NotificationSender email =

                new EmailNotification();

        email.send(
                "john@example.com",
                "Welcome to TUF+!"
        );

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();

        /*
        ===============================================================

        SMS Notification

        ===============================================================
        */

        NotificationSender sms =

                new SMSNotification();

        sms.send(
                "9876543210",
                "Your OTP is 4567."
        );
    }
}

/*
===============================================================================

Dry Run

Step 1

Client creates

EmailNotification

------------------------------------------------------------

Step 2

send()

↓

Rate Limit Check

↓

Validate Recipient

↓

Format Message

↓

Audit Log

↓

composeMessage()

↓

Email HTML Generated

↓

sendMessage()

↓

Email Sent

↓

Analytics Updated

------------------------------------------------------------

Step 3

Client creates

SMSNotification

------------------------------------------------------------

Step 4

send()

↓

Rate Limit Check

↓

Validate Recipient

↓

Format Message

↓

Audit Log

↓

composeMessage()

↓

SMS Format Generated

↓

sendMessage()

↓

SMS Sent

↓

Custom SMS Analytics

===============================================================================

Flow Diagram


                 NotificationSender
                (Abstract Class)

                       |

      -----------------------------------

      |                                 |

EmailNotification              SMSNotification

                       |

                 Template Method

                    send()

                       |

----------------------------------------------------

Rate Limit

↓

Validate Recipient

↓

Format Message

↓

Audit Log

↓

Compose Message

↓

Send Message

↓

Analytics

===============================================================================

Execution Flow


Client

   |

NotificationSender

   |

send()

   |

Common Steps

   |

Subclass Implementation

   |

Notification Sent

===============================================================================

OUTPUT

Checking Rate Limits for : john@example.com

Validating Recipient : john@example.com

Audit Log : Welcome to TUF+! -> john@example.com

Sending EMAIL to john@example.com

<html><body><p>Welcome to TUF+!</p></body></html>

Analytics Updated for : john@example.com

--------------------------------

Checking Rate Limits for : 9876543210

Validating Recipient : 9876543210

Audit Log : Your OTP is 4567. -> 9876543210

Sending SMS to 9876543210

[SMS] Your OTP is 4567.

Custom SMS Analytics for : 9876543210

===============================================================================

Advantages

✔ Defines a fixed workflow.

✔ Avoids code duplication.

✔ Easy to extend.

✔ Follows Open/Closed Principle.

✔ Promotes code reuse.

===============================================================================

Disadvantages

✘ Inheritance-based.

✘ Changes in template affect all subclasses.

✘ Deep inheritance can become difficult to manage.

===============================================================================

When to Use

✔ Notification Systems.

✔ Payment Processing.

✔ Authentication Flow.

✔ File Processing.

✔ Report Generation.

✔ Data Import/Export.

===============================================================================

Interview Questions

Q1. What is Template Method Pattern?

Ans:

Template Method Pattern defines the skeleton of an algorithm in a superclass
while allowing subclasses to customize specific steps.

------------------------------------------------

Q2. Which is the Template Method?

Ans:

send()

------------------------------------------------

Q3. Which is the Abstract Class?

Ans:

NotificationSender

------------------------------------------------

Q4. Which are the Concrete Classes?

Ans:

EmailNotification

SMSNotification

------------------------------------------------

Q5. What is a Hook Method?

Ans:

A hook is an optional method with a default implementation that subclasses
may override.

Example:

postSendAnalytics()

------------------------------------------------

Q6. Why is send() declared final?

Ans:

To prevent subclasses from changing the predefined workflow.

------------------------------------------------

Q7. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

------------------------------------------------

Q8. Which category does Template Method belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Template Method Pattern is a Behavioral Design Pattern that defines the
overall algorithm in a superclass while allowing subclasses to customize
specific steps without modifying the workflow.

In this example, NotificationSender is the Abstract Class,
send() is the Template Method, EmailNotification and SMSNotification are
the Concrete Classes, and postSendAnalytics() is a Hook Method. The
workflow remains fixed, while message composition and sending logic vary
between subclasses, making the design reusable, extensible, and easy to
maintain.

===============================================================================
*/