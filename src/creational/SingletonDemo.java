package creational;/*
===============================================================================

                    SINGLETON DESIGN PATTERN
                    (Lazy Initialization)

Definition:
------------
Singleton Pattern ensures that only one object of a class is created
and provides a global access point to that object.

Real World Examples:
--------------------
1. Database Connection
2. Logger
3. Configuration Manager
4. Cache Manager

===============================================================================
*/

// Class implementing Lazy Loading Singleton

class LazySingleton {

    /*
    ===========================================================================
    Static variable to store the single object.

    Initially:
        instance = null

    Object will be created only when getInstance() is called.

    ===========================================================================
    */

    private static LazySingleton instance;

    /*
    ===========================================================================
    Private Constructor

    Why private?

    Prevents object creation from outside the class.

    The following statement is NOT allowed:

        new LazySingleton();

    ===========================================================================
    */

    private LazySingleton() {

        System.out.println("Singleton Object Created");
    }

    /*
    ===========================================================================
    Public Static Method

    Purpose:
    Returns the single object of the class.

    Step 1:
    Check whether object already exists.

    Step 2:
    If not, create the object.

    Step 3:
    Return the same object every time.

    ===========================================================================
    */

    public static LazySingleton getInstance() {

        // Object not created yet

        if (instance == null) {

            instance = new LazySingleton();
        }

        // Return existing object

        return instance;
    }

    /*
    Sample Business Method
    */

    public void display() {

        System.out.println("Singleton Method Called");
    }
}

/*
===============================================================================

Driver Class

===============================================================================
*/

public class SingletonDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        First Call

        instance == null

        Object will be created.

        ===============================================================
        */

        LazySingleton object1 = LazySingleton.getInstance();

        object1.display();

        /*
        ===============================================================

        Second Call

        instance != null

        Existing object will be returned.

        No new object is created.

        ===============================================================
        */

        LazySingleton object2 = LazySingleton.getInstance();

        object2.display();

        /*
        ===============================================================

        Compare both references

        If Singleton works correctly,
        both references should point to same object.

        ===============================================================
        */

        System.out.println();

        System.out.println("Are both objects same?");

        System.out.println(object1 == object2);
    }
}

/*
===============================================================================

Dry Run

Initially

instance

null


First Call

LazySingleton.getInstance()

↓

instance == null

↓

Create Object

instance
    |
    v
+-------------------+
| LazySingleton     |
+-------------------+

Return object


Second Call

LazySingleton.getInstance()

↓

instance != null

↓

Return Existing Object

No new object is created.


Memory Representation


object1 --------------------|
                            |
                            v
                  +-------------------+
                  | LazySingleton     |
                  +-------------------+
                            ^
                            |
object2 --------------------|


Both references point to the SAME object.

===============================================================================

OUTPUT

Singleton Object Created
Singleton Method Called
Singleton Method Called

Are both objects same?
true

===============================================================================

Advantages

✔ Only one object is created.

✔ Object is created only when needed (Lazy Initialization).

✔ Saves memory.

✔ Provides global access point.

✔ Commonly used in Logger, Database Connection, Cache Manager.

===============================================================================

Interview Questions

Q1. Why is constructor private?

Ans:
To prevent object creation using the new keyword.

------------------------------------------------

Q2. Why is instance variable static?

Ans:
Because only one copy should exist for the entire class.

------------------------------------------------

Q3. Why is getInstance() static?

Ans:
So it can be called without creating an object.

------------------------------------------------

Q4. Why is it called Lazy Singleton?

Ans:
Because the object is created only when it is first requested,
not when the class is loaded.

===============================================================================

Interview Summary (30 Seconds)

Singleton is a Creational Design Pattern that ensures only one object
of a class exists throughout the application. It uses a private constructor,
a private static instance variable, and a public static getInstance()
method. In Lazy Initialization, the object is created only when it is
needed for the first time, improving memory efficiency.

===============================================================================
*/