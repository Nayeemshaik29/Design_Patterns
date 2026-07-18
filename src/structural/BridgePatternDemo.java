/*
===============================================================================

                        BRIDGE DESIGN PATTERN

Definition:
------------
Bridge Pattern separates an abstraction from its implementation so that
both can vary independently.

Instead of tightly coupling two dimensions (like Platform and Quality),
the Bridge Pattern connects them through composition.

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose you use Netflix.

You can watch movies on:

✔ Web
✔ Mobile
✔ Smart TV

And each platform supports different video qualities:

✔ SD
✔ HD
✔ 4K Ultra HD

Instead of creating classes like:

WebSDPlayer
WebHDPlayer
Web4KPlayer
MobileSDPlayer
MobileHDPlayer
Mobile4KPlayer

Bridge Pattern separates:

Platform (Web/Mobile)

from

Video Quality (SD/HD/4K)

Both can change independently.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                    IMPLEMENTOR INTERFACE

Every video quality should provide:

load()

===============================================================================
*/

interface VideoQuality {

    void load(String title);
}

/*
===============================================================================

                CONCRETE IMPLEMENTORS

Different video qualities.

===============================================================================
*/

class SDQuality implements VideoQuality {

    @Override
    public void load(String title) {

        System.out.println("Streaming " + title +
                " in SD Quality");
    }
}

class HDQuality implements VideoQuality {

    @Override
    public void load(String title) {

        System.out.println("Streaming " + title +
                " in HD Quality");
    }
}

class UltraHDQuality implements VideoQuality {

    @Override
    public void load(String title) {

        System.out.println("Streaming " + title +
                " in 4K Ultra HD Quality");
    }
}

/*
===============================================================================

                        ABSTRACTION

VideoPlayer contains a reference to VideoQuality.

This creates the Bridge.

===============================================================================
*/

abstract class VideoPlayer {

    protected VideoQuality quality;

    public VideoPlayer(VideoQuality quality) {

        this.quality = quality;
    }

    public abstract void play(String title);
}

/*
===============================================================================

                REFINED ABSTRACTION

Web Player

===============================================================================
*/

class WebPlayer extends VideoPlayer {

    public WebPlayer(VideoQuality quality) {

        super(quality);
    }

    @Override
    public void play(String title) {

        System.out.println("Web Platform");

        quality.load(title);
    }
}

/*
===============================================================================

                REFINED ABSTRACTION

Mobile Player

===============================================================================
*/

class MobilePlayer extends VideoPlayer {

    public MobilePlayer(VideoQuality quality) {

        super(quality);
    }

    @Override
    public void play(String title) {

        System.out.println("Mobile Platform");

        quality.load(title);
    }
}

/*
===============================================================================

                        CLIENT

Client chooses:

Platform

+

Quality

Both are independent.

===============================================================================
*/

public class BridgePatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Web Platform

        HD Quality

        ===============================================================
        */

        VideoPlayer player1 =

                new WebPlayer(new HDQuality());

        player1.play("Interstellar");

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();

        /*
        ===============================================================

        Mobile Platform

        4K Ultra HD

        ===============================================================
        */

        VideoPlayer player2 =

                new MobilePlayer(new UltraHDQuality());

        player2.play("Inception");
    }
}

/*
===============================================================================

Dry Run

Step 1

Client creates

WebPlayer

↓

Injects

HDQuality

------------------------------------------------------------

Step 2

play("Interstellar")

↓

WebPlayer

↓

HDQuality.load()

↓

Streaming Interstellar in HD Quality

------------------------------------------------------------

Step 3

Client creates

MobilePlayer

↓

Injects

UltraHDQuality

------------------------------------------------------------

Step 4

play("Inception")

↓

MobilePlayer

↓

UltraHDQuality.load()

↓

Streaming Inception in 4K Ultra HD Quality

===============================================================================

Flow Diagram


                  VideoPlayer
                  (Abstraction)
                         |
          --------------------------------
          |                              |
          |                              |
     WebPlayer                   MobilePlayer
          |                              |
          |                              |
          ----------Bridge---------------
                     |
                     |
               VideoQuality
             (Implementor)
              /      |      \
             /       |       \
            /        |        \
      SDQuality  HDQuality  UltraHDQuality


===============================================================================

Execution Flow


Client

   |

VideoPlayer

   |

Platform

(Web/Mobile)

   |

VideoQuality

(SD/HD/4K)

   |

Streaming Video

===============================================================================

OUTPUT

Web Platform

Streaming Interstellar in HD Quality

--------------------------------

Mobile Platform

Streaming Inception in 4K Ultra HD Quality

===============================================================================

Advantages

✔ Abstraction and implementation evolve independently.

✔ Avoids class explosion.

✔ Follows Open/Closed Principle.

✔ Better scalability.

✔ Promotes composition over inheritance.

===============================================================================

Disadvantages

✘ Adds extra abstraction.

✘ Slightly more complex design.

===============================================================================

When to Use

✔ When abstraction and implementation should vary independently.

✔ When avoiding subclass explosion.

✔ When composition is preferred over inheritance.

✔ Cross-platform applications.

✔ Drivers and Device APIs.

===============================================================================

Interview Questions

Q1. What is Bridge Pattern?

Ans:

Bridge Pattern separates abstraction from implementation so both can vary
independently.

------------------------------------------------

Q2. Which is the Abstraction?

Ans:

VideoPlayer

------------------------------------------------

Q3. Which are the Refined Abstractions?

Ans:

WebPlayer

MobilePlayer

------------------------------------------------

Q4. Which is the Implementor?

Ans:

VideoQuality

------------------------------------------------

Q5. Which are the Concrete Implementors?

Ans:

SDQuality

HDQuality

UltraHDQuality

------------------------------------------------

Q6. Why use Bridge Pattern?

Ans:

To avoid creating many subclasses for every possible combination.

------------------------------------------------

Q7. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

------------------------------------------------

Q8. Which category does Bridge belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Bridge Pattern is a Structural Design Pattern that separates an abstraction
from its implementation so that both can change independently. Instead of
creating many subclasses for every possible combination, it uses composition
to connect the abstraction with its implementation.

In this example, VideoPlayer is the Abstraction, WebPlayer and MobilePlayer
are the Refined Abstractions, VideoQuality is the Implementor, and
SDQuality, HDQuality, and UltraHDQuality are the Concrete Implementors.
This design allows any platform to work with any video quality without
creating unnecessary subclasses, making the code flexible, scalable, and
easy to maintain.

===============================================================================
*/