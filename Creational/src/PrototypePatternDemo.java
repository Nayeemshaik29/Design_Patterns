import java.util.HashMap;
import java.util.Map;

/*
===============================================================================
                    PROTOTYPE DESIGN PATTERN IN JAVA
===============================================================================

Definition:
------------
Prototype Pattern creates new objects by cloning an existing object instead
of creating them from scratch.

Real World Example:
-------------------
Suppose an application sends thousands of welcome emails every day.

Instead of creating a new WelcomeEmail object every time,

        new WelcomeEmail()
        new WelcomeEmail()
        new WelcomeEmail()

we create one prototype object and clone it whenever needed.

Benefits:
----------
1. Faster object creation
2. Reuses existing objects
3. Reduces duplicate initialization
4. Improves performance

===============================================================================
*/


/*
===============================================================================
Prototype Interface

Every class implementing this interface must provide:
1. clone()      -> Creates a copy of itself
2. setContent() -> Updates dynamic content
3. send()       -> Sends the email

Cloneable is a marker interface that allows object cloning.
===============================================================================
*/

interface EmailTemplate extends Cloneable {

    // Creates a copy of the existing object
    EmailTemplate clone();

    // Updates the email content
    void setContent(String content);

    // Sends the email
    void send(String to);
}


/*
===============================================================================
Concrete Prototype

This is the actual object that will be cloned.

Default values:
Subject : Welcome to TUF+
Content : Hi there! Thanks for joining us.

Instead of creating this object repeatedly,
we will clone it whenever required.
===============================================================================
*/

class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    // Constructor initializes default values only once
    public WelcomeEmail() {

        this.subject = "Welcome to TUF+";
        this.content = "Hi there! Thanks for joining us.";
    }

    /*
    ===========================================================================
    clone()

    super.clone() creates a copy of the existing object.

    This is a SHALLOW COPY.

    If cloning fails,
    RuntimeException is thrown.

    ===========================================================================
    */

    @Override
    public WelcomeEmail clone() {

        try {

            return (WelcomeEmail) super.clone();

        } catch (CloneNotSupportedException e) {

            throw new RuntimeException("Clone Failed", e);
        }
    }

    // Update dynamic content

    @Override
    public void setContent(String content) {

        this.content = content;
    }

    // Simulate sending email

    @Override
    public void send(String to) {

        System.out.println("---------------------------------------");
        System.out.println("To      : " + to);
        System.out.println("Subject : " + subject);
        System.out.println("Content : " + content);
        System.out.println("---------------------------------------");
        System.out.println();
    }
}


/*
===============================================================================
Prototype Registry

Stores already created prototype objects.

Only ONE original WelcomeEmail object is created.

Whenever client requests an email template,

Registry DOES NOT create a new object.

Instead,

Original Object
        |
      clone()
        |
        v
   New Independent Object

===============================================================================
*/

class EmailTemplateRegistry {

    // Stores prototype objects

    private static final Map<String, EmailTemplate> templates =
            new HashMap<>();

    /*
    Static block executes only once when class loads.

    Original prototype object is stored here.

    templates

    welcome
        |
        v
    WelcomeEmail Object

    */

    static {

        templates.put("welcome", new WelcomeEmail());

    }

    /*
    Returns a CLONED object.

    Original object is never modified.

    */

    public static EmailTemplate getTemplate(String type) {

        EmailTemplate prototype = templates.get(type);

        if (prototype == null) {

            throw new IllegalArgumentException("Template Not Found");
        }

        // Return cloned object

        return prototype.clone();
    }
}


/*
===============================================================================
Driver Class

Dry Run

Registry

welcome
    |
    v
--------------------------------
Subject : Welcome to TUF+
Content : Hi there!
--------------------------------


Request 1

getTemplate("welcome")

        |

clone()

        |

Clone1

Content changes to

Hi Alice, Welcome to Premium!


Request 2

getTemplate("welcome")

        |

clone()

        |

Clone2

Content changes to

Hi Bob, Thanks for joining!


Notice:

Original object always remains unchanged.

===============================================================================
*/

public class PrototypePatternDemo {

    public static void main(String[] args) {

        /*
        ===========================================================
        Clone 1
        ===========================================================
        */

        EmailTemplate email1 =
                EmailTemplateRegistry.getTemplate("welcome");

        email1.setContent(
                "Hi Alice, Welcome to TUF Premium!");

        email1.send("alice@example.com");


        /*
        ===========================================================
        Clone 2
        ===========================================================
        */

        EmailTemplate email2 =
                EmailTemplateRegistry.getTemplate("welcome");

        email2.setContent(
                "Hi Bob, Thanks for joining!");

        email2.send("bob@example.com");


        /*
        ===========================================================
        Clone 3
        ===========================================================
        */

        EmailTemplate email3 =
                EmailTemplateRegistry.getTemplate("welcome");

        email3.setContent(
                "Hi Charlie, Happy Learning!");

        email3.send("charlie@example.com");
    }
}

/*
===============================================================================

OUTPUT

---------------------------------------
To      : alice@example.com
Subject : Welcome to TUF+
Content : Hi Alice, Welcome to TUF Premium!
---------------------------------------

---------------------------------------
To      : bob@example.com
Subject : Welcome to TUF+
Content : Hi Bob, Thanks for joining!
---------------------------------------

---------------------------------------
To      : charlie@example.com
Subject : Welcome to TUF+
Content : Hi Charlie, Happy Learning!
---------------------------------------

===============================================================================

INTERVIEW EXPLANATION (30 Seconds)

Prototype Pattern creates new objects by cloning an existing object instead
of creating them from scratch.

EmailTemplate is the Prototype interface.

WelcomeEmail is the Concrete Prototype implementing clone().

EmailTemplateRegistry stores one original object and returns cloned copies.

Each cloned email can be modified independently without affecting the
original object, making object creation efficient and reusable.

===============================================================================
*/