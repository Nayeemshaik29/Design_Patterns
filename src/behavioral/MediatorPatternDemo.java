package behavioral;/*
===============================================================================

                    MEDIATOR DESIGN PATTERN

Definition:
------------
Mediator Pattern defines an object that encapsulates how multiple objects
communicate with each other.

Instead of objects communicating directly, they communicate through a
Mediator. This reduces coupling between objects.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose multiple users are editing a Google Docs document.

Without a Mediator:

Alice ↔ Bob ↔ Charlie

Every user needs to know every other user.

With a Mediator:

Alice

   |

Google Docs Server (Mediator)

   |

Bob

   |

Charlie

Users only communicate with the server, and the server distributes the
changes to everyone.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                    MEDIATOR INTERFACE

Every mediator should provide:

join()

broadcastChange()

===============================================================================
*/

interface DocumentSessionMediator {

    void join(User user);

    void broadcastChange(String change,
                         User sender);
}

/*
===============================================================================

                CONCRETE MEDIATOR

Collaborative Document

Manages communication between users.

===============================================================================
*/

class CollaborativeDocument
        implements DocumentSessionMediator {

    private List<User> users =
            new ArrayList<>();

    @Override
    public void join(User user) {

        users.add(user);
    }

    @Override
    public void broadcastChange(String change,
                                User sender) {

        for (User user : users) {

            if (user != sender) {

                user.receiveChange(change,
                        sender);
            }
        }
    }
}

/*
===============================================================================

                        COLLEAGUE

User

Doesn't communicate with other users directly.

Uses Mediator.

===============================================================================
*/

class User {

    protected String name;

    protected DocumentSessionMediator mediator;

    public User(String name,
                DocumentSessionMediator mediator) {

        this.name = name;
        this.mediator = mediator;
    }

    /*
    ===============================================================

    Make Change

    ===============================================================
    */

    public void makeChange(String change) {

        System.out.println(
                name
                        + " edited the document : "
                        + change);

        mediator.broadcastChange(
                change,
                this);
    }

    /*
    ===============================================================

    Receive Change

    ===============================================================
    */

    public void receiveChange(String change,
                              User sender) {

        System.out.println(
                name
                        + " saw change from "
                        + sender.name
                        + " : \""
                        + change
                        + "\"");
    }
}

/*
===============================================================================

                        CLIENT

Creates Mediator.

Registers Users.

Users communicate through the Mediator.

===============================================================================
*/

public class MediatorPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Mediator

        ===============================================================
        */

        CollaborativeDocument document =

                new CollaborativeDocument();

        /*
        ===============================================================

        Create Users

        ===============================================================
        */

        User alice =

                new User(
                        "Alice",
                        document);

        User bob =

                new User(
                        "Bob",
                        document);

        User charlie =

                new User(
                        "Charlie",
                        document);

        /*
        ===============================================================

        Join Session

        ===============================================================
        */

        document.join(alice);

        document.join(bob);

        document.join(charlie);

        /*
        ===============================================================

        Alice edits document

        ===============================================================
        */

        alice.makeChange(
                "Added Project Title");

        System.out.println();

        /*
        ===============================================================

        Bob edits document

        ===============================================================
        */

        bob.makeChange(
                "Corrected Grammar in Paragraph 2");
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Mediator

↓

CollaborativeDocument

------------------------------------------------------------

Step 2

Create Users

↓

Alice

↓

Bob

↓

Charlie

------------------------------------------------------------

Step 3

Users Join Session

↓

Mediator stores users

------------------------------------------------------------

Step 4

Alice edits document

↓

Mediator receives change

↓

Mediator broadcasts

↓

Bob receives update

↓

Charlie receives update

------------------------------------------------------------

Step 5

Bob edits document

↓

Mediator receives change

↓

Mediator broadcasts

↓

Alice receives update

↓

Charlie receives update

===============================================================================

Flow Diagram


                 User (Alice)

                      |

                 User (Bob)

                      |

          CollaborativeDocument
                (Mediator)

                      |

               User (Charlie)

===============================================================================

Execution Flow


Client

   |

CollaborativeDocument

   |

User makes change

   |

Mediator

   |

Broadcast Change

   |

Other Users Receive Update

===============================================================================

OUTPUT

Alice edited the document : Added Project Title

Bob saw change from Alice : "Added Project Title"

Charlie saw change from Alice : "Added Project Title"

Bob edited the document : Corrected Grammar in Paragraph 2

Alice saw change from Bob : "Corrected Grammar in Paragraph 2"

Charlie saw change from Bob : "Corrected Grammar in Paragraph 2"

===============================================================================

Advantages

✔ Reduces coupling between objects.

✔ Centralizes communication logic.

✔ Easy to add new colleagues.

✔ Follows Single Responsibility Principle.

✔ Improves maintainability.

===============================================================================

Disadvantages

✘ Mediator can become very complex.

✘ Large mediator may become a God Object.

===============================================================================

When to Use

✔ Chat Applications.

✔ Google Docs Collaboration.

✔ Air Traffic Control Systems.

✔ Smart Home Controllers.

✔ GUI Components.

✔ Multiplayer Games.

===============================================================================

Interview Questions

Q1. What is Mediator Pattern?

Ans:

Mediator Pattern centralizes communication between objects so that they do
not communicate directly with each other.

------------------------------------------------

Q2. Which is the Mediator?

Ans:

DocumentSessionMediator.

------------------------------------------------

Q3. Which is the Concrete Mediator?

Ans:

CollaborativeDocument.

------------------------------------------------

Q4. Which are the Colleagues?

Ans:

User objects.

------------------------------------------------

Q5. Why use Mediator Pattern?

Ans:

To reduce coupling between multiple interacting objects.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Single Responsibility Principle.

Communication logic is separated from colleague objects.

------------------------------------------------

Q7. Which category does Mediator belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Mediator Pattern is a Behavioral Design Pattern that centralizes
communication between multiple objects using a Mediator, reducing direct
dependencies among them.

In this example, DocumentSessionMediator is the Mediator interface,
CollaborativeDocument is the Concrete Mediator, and User objects are the
Colleagues. Whenever a user edits the document, the change is sent to the
Mediator, which broadcasts it to all other users. This keeps the design
loosely coupled, scalable, and easy to maintain.

===============================================================================
*/