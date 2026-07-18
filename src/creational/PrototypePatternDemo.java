import java.util.*;

/*
===============================================================================

                    PROTOTYPE DESIGN PATTERN

Definition:
------------
Prototype Pattern creates new objects by cloning an existing object
instead of creating them from scratch.

Instead of:

    new WelcomeEmail();
    new WelcomeEmail();

we create one prototype object and clone it whenever required.

Real World Examples:
--------------------
1. Email Templates
2. Resume Templates
3. Document Templates
4. Game Characters
5. UI Components

===============================================================================
*/


/*
===============================================================================

                        PROTOTYPE INTERFACE

Every Email Template must support:

1. clone()      -> Creates a copy of itself.
2. setContent() -> Updates dynamic content.
3. send()       -> Sends the email.

Cloneable is a marker interface that enables cloning.

===============================================================================
*/

interface EmailTemplate extends Cloneable {

    // Creates a copy of the existing object

    EmailTemplate clone();

    // Update dynamic content

    void setContent(String content);

    // Send email

    void send(String to);
}


/*
===============================================================================

                    CONCRETE PROTOTYPE

WelcomeEmail is the original object that will be cloned.

Default values:

Subject : Welcome to TUF+
Content : Hi there! Thanks for joining us.

===============================================================================
*/

class WelcomeEmail implements EmailTemplate {

    private String subject;

    private String content;

    /*
    ===========================================================================
    Constructor

    Initializes default values only once.

    ===========================================================================
    */

    public WelcomeEmail() {

        this.subject = "Welcome to TUF+";

        this.content = "Hi there! Thanks for joining us.";
    }


    /*
    ===========================================================================
    clone()

    super.clone() creates a copy of the current object.

    This is a shallow copy.

    Instead of creating

        new WelcomeEmail();

    we simply clone the existing object.

    ===========================================================================
    */

    @Override
    public WelcomeEmail clone() {

        try {

            return (WelcomeEmail) super.clone();

        }

        catch (CloneNotSupportedException e) {

            throw new RuntimeException("Clone Failed", e);
        }
    }


    /*
    ===========================================================================
    Update email content.

    Each clone can have different content.

    ===========================================================================
    */

    @Override
    public void setContent(String content) {

        this.content = content;
    }


    /*
    ===========================================================================
    Send Email

    Prints email details.

    ===========================================================================
    */

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

                    PROTOTYPE REGISTRY

Stores original prototype objects.

Only ONE WelcomeEmail object is created.

Whenever client requests a template,

Registry returns

Original Object

        |

     clone()

        |

New Independent Object

===============================================================================
*/

class EmailTemplateRegistry {

    /*
    Stores Prototype Objects

    Key      Value

    ----------------------------

    welcome  WelcomeEmail

    */

    private static final Map<String, EmailTemplate> templates =
            new HashMap<>();


    /*
    ===========================================================================
    Static Block

    Executes only once.

    Creates original prototype object.

    ===========================================================================
    */

    static {

        templates.put("welcome", new WelcomeEmail());

        // templates.put("discount", new DiscountEmail());

        // templates.put("feature", new FeatureUpdateEmail());

    }


    /*
    ===========================================================================
    Returns cloned object.

    Original object never changes.

    ===========================================================================
    */

    public static EmailTemplate getTemplate(String type) {

        EmailTemplate prototype = templates.get(type);

        if (prototype == null) {

            throw new IllegalArgumentException(
                    "Template Not Found : " + type);
        }

        return prototype.clone();
    }

}


/*
===============================================================================

                        DRIVER CLASS

===============================================================================
*/

public class PrototypePatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        First Clone

        Registry

            |

        Original WelcomeEmail

            |

         clone()

            |

        Clone 1

        ===============================================================
        */

        EmailTemplate welcomeEmail1 =

                EmailTemplateRegistry.getTemplate("welcome");

        welcomeEmail1.setContent(

                "Hi Alice, Welcome to TUF Premium!");

        welcomeEmail1.send("alice@example.com");


        /*
        ===============================================================

        Second Clone

        Registry

            |

        Original WelcomeEmail

            |

         clone()

            |

        Clone 2

        ===============================================================
        */

        EmailTemplate welcomeEmail2 =

                EmailTemplateRegistry.getTemplate("welcome");

        welcomeEmail2.setContent(

                "Hi Bob, Thanks for joining!");

        welcomeEmail2.send("bob@example.com");


        /*
        ===============================================================

        Third Clone

        Registry

            |

        Original WelcomeEmail

            |

         clone()

            |

        Clone 3

        ===============================================================
        */

        EmailTemplate welcomeEmail3 =

                EmailTemplateRegistry.getTemplate("welcome");

        welcomeEmail3.setContent(

                "Hi Charlie, Happy Learning!");

        welcomeEmail3.send("charlie@example.com");
    }
}


/*
===============================================================================

Dry Run

Registry

templates

----------------------------------

welcome

        |

        v

Original WelcomeEmail

Subject : Welcome to TUF+

Content : Hi there!

----------------------------------


Client Request 1

getTemplate("welcome")

        |

clone()

        |

Clone 1

Content becomes

Hi Alice, Welcome to TUF Premium!


------------------------------------------------------


Client Request 2

getTemplate("welcome")

        |

clone()

        |

Clone 2

Content becomes

Hi Bob, Thanks for joining!


------------------------------------------------------


Client Request 3

getTemplate("welcome")

        |

clone()

        |

Clone 3

Content becomes

Hi Charlie, Happy Learning!


Original Object Always Remains

Subject : Welcome to TUF+

Content : Hi there!

===============================================================================

Flow Diagram


                    Client

                       |

                       |

            EmailTemplateRegistry

                       |

                       |

              Original Prototype

                 WelcomeEmail

                       |

                    clone()

                       |

        --------------------------------

        |              |              |

     Clone1         Clone2         Clone3

        |              |              |

    setContent()   setContent()   setContent()

        |              |              |

      send()         send()         send()


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

Advantages

✔ Faster object creation.

✔ Reuses existing objects.

✔ Reduces duplicate initialization.

✔ Improves performance.

✔ Easy to create similar objects.

===============================================================================

Disadvantages

✘ Deep copy implementation can be complex.

✘ Mutable objects require careful cloning.

===============================================================================

Interview Questions

Q1. What is Prototype Pattern?

Ans:
Prototype Pattern creates new objects by cloning an existing object
instead of creating them from scratch.

------------------------------------------------

Q2. Why use Prototype Pattern?

Ans:
To improve performance when object creation is expensive.

------------------------------------------------

Q3. Why implement Cloneable?

Ans:
To allow object cloning using super.clone().

------------------------------------------------

Q4. What does Registry do?

Ans:
It stores original prototype objects and returns cloned copies.

------------------------------------------------

Q5. Which Design Pattern category?

Ans:
Creational Design Pattern.

===============================================================================

Interview Summary (30 Seconds)

Prototype Pattern is a Creational Design Pattern that creates new objects
by cloning an existing object instead of creating them from scratch.
In this example, WelcomeEmail acts as the prototype, EmailTemplateRegistry
stores the original object, and whenever a template is requested,
the registry returns a cloned copy. This improves performance,
reduces duplicate initialization, and allows each cloned object to be
customized independently without affecting the original object.

===============================================================================
*/