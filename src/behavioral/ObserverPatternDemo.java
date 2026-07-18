/*
===============================================================================

                        OBSERVER DESIGN PATTERN

Definition:
------------
Observer Pattern defines a one-to-many dependency between objects so that
when one object (Subject) changes its state, all its dependent objects
(Observers) are automatically notified and updated.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you subscribe to a YouTube channel.

Whenever the creator uploads a new video,

✔ Email Subscribers receive an email.

✔ Mobile App Users receive a push notification.

The YouTube channel doesn't know how notifications are sent.
It simply informs all subscribers.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        OBSERVER

Every subscriber should implement:

update()

===============================================================================
*/

interface Subscriber {

    void update(String videoTitle);
}

/*
===============================================================================

                CONCRETE OBSERVER

Email Subscriber

===============================================================================
*/

class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {

        this.email = email;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "Email sent to "
                        + email
                        + " : New Video Uploaded - "
                        + videoTitle
        );
    }
}

/*
===============================================================================

                CONCRETE OBSERVER

Mobile App Subscriber

===============================================================================
*/

class MobileAppSubscriber implements Subscriber {

    private String username;

    public MobileAppSubscriber(String username) {

        this.username = username;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println(
                "In-App Notification for "
                        + username
                        + " : New Video - "
                        + videoTitle
        );
    }
}

/*
===============================================================================

                        SUBJECT

Every channel should provide:

subscribe()

unsubscribe()

notifySubscribers()

===============================================================================
*/

interface Channel {

    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void notifySubscribers(String videoTitle);
}

/*
===============================================================================

                CONCRETE SUBJECT

YouTube Channel

Maintains list of subscribers.

Whenever a new video is uploaded,

all subscribers are notified.

===============================================================================
*/

class YouTubeChannel implements Channel {

    private String channelName;

    private List<Subscriber> subscribers =
            new ArrayList<>();

    public YouTubeChannel(String channelName) {

        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber) {

        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {

        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {

        for (Subscriber subscriber : subscribers) {

            subscriber.update(videoTitle);
        }
    }

    /*
    ===============================================================

    Upload Video

    Notify everyone

    ===============================================================
    */

    public void uploadVideo(String videoTitle) {

        System.out.println(
                channelName
                        + " uploaded : "
                        + videoTitle
        );

        System.out.println();

        notifySubscribers(videoTitle);
    }
}

/*
===============================================================================

                        CLIENT

Client subscribes users to the YouTube channel.

Whenever a video is uploaded,

all subscribers receive notifications automatically.

===============================================================================
*/

public class ObserverPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Subject

        ===============================================================
        */

        YouTubeChannel tuf =
                new YouTubeChannel("takeUforward");

        /*
        ===============================================================

        Add Subscribers

        ===============================================================
        */

        tuf.subscribe(

                new MobileAppSubscriber("Raj")
        );

        tuf.subscribe(

                new EmailSubscriber(
                        "rahul@example.com")
        );

        /*
        ===============================================================

        Upload Video

        ===============================================================
        */

        tuf.uploadVideo("Observer Design Pattern");
    }
}

/*
===============================================================================

Dry Run

Step 1

Create

YouTubeChannel

↓

takeUforward

------------------------------------------------------------

Step 2

Subscribe

Raj

↓

Subscribe

rahul@example.com

------------------------------------------------------------

Step 3

Upload Video

↓

Observer Design Pattern

------------------------------------------------------------

notifySubscribers()

↓

Raj

↓

Receives Mobile Notification

------------------------------------------------------------

rahul@example.com

↓

Receives Email

===============================================================================

Flow Diagram


                 Subscriber
              (Observer Interface)

                /            \

               /              \

EmailSubscriber      MobileAppSubscriber

                    ▲

                    |

            YouTubeChannel

          (Concrete Subject)

                    ▲

                    |

                 Channel

          (Subject Interface)

===============================================================================

Execution Flow


Client

   |

YouTubeChannel

   |

uploadVideo()

   |

notifySubscribers()

   |

-----------------------------

|                           |

EmailSubscriber     MobileAppSubscriber

|                           |

Email                  Push Notification

===============================================================================

OUTPUT

takeUforward uploaded : Observer Design Pattern

In-App Notification for Raj :
New Video - Observer Design Pattern

Email sent to rahul@example.com :
New Video Uploaded - Observer Design Pattern

===============================================================================

Advantages

✔ Loose coupling.

✔ Easy to add new observers.

✔ Supports broadcast communication.

✔ Follows Open/Closed Principle.

✔ Automatic notification mechanism.

===============================================================================

Disadvantages

✘ Large number of observers may impact performance.

✘ Difficult to debug notification chains.

✘ Order of notifications is usually not guaranteed.

===============================================================================

When to Use

✔ Event Notification Systems.

✔ YouTube Subscribers.

✔ Stock Market Updates.

✔ Weather Applications.

✔ Chat Applications.

✔ GUI Event Listeners.

===============================================================================

Interview Questions

Q1. What is Observer Pattern?

Ans:

Observer Pattern defines a one-to-many relationship where multiple observers
are automatically notified whenever the subject changes.

------------------------------------------------

Q2. Which is the Subject?

Ans:

Channel

------------------------------------------------

Q3. Which is the Concrete Subject?

Ans:

YouTubeChannel

------------------------------------------------

Q4. Which is the Observer?

Ans:

Subscriber

------------------------------------------------

Q5. Which are the Concrete Observers?

Ans:

EmailSubscriber

MobileAppSubscriber

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

New observers can be added without modifying the subject.

------------------------------------------------

Q7. Which category does Observer belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Observer Pattern is a Behavioral Design Pattern that establishes a
one-to-many relationship between objects. Whenever the Subject changes,
all registered Observers are automatically notified.

In this example, Channel is the Subject, YouTubeChannel is the Concrete
Subject, Subscriber is the Observer interface, and EmailSubscriber and
MobileAppSubscriber are the Concrete Observers. When a new video is uploaded,
the YouTube channel notifies all subscribers automatically, making the
system loosely coupled, scalable, and easy to extend.

===============================================================================
*/