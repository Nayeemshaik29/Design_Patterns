/**
 * ============================================================================
 * File Name : FailFastVsFailSafe.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Fail-Fast vs Fail-Safe Iterator
 * ============================================================================
 *
 * WHAT IS AN ITERATOR?
 * --------------------
 *
 * An Iterator is an object used to traverse
 * elements of a Collection one by one.
 *
 * Example Collections:
 *
 *      ArrayList
 *      LinkedList
 *      HashSet
 *      HashMap
 *
 * ============================================================================
 * WHAT IS FAIL-FAST?
 * ============================================================================
 *
 * A Fail-Fast Iterator immediately throws
 * ConcurrentModificationException
 * if the collection is modified while iterating.
 *
 * It checks whether the collection
 * has been structurally modified.
 *
 * If YES,
 *
 * iteration stops immediately.
 *
 * ============================================================================
 * WHAT IS FAIL-SAFE?
 * ============================================================================
 *
 * A Fail-Safe Iterator works on a COPY
 * of the original collection.
 *
 * Therefore,
 *
 * modifying the original collection
 * does NOT affect the iteration.
 *
 * No ConcurrentModificationException occurs.
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine reading a Newspaper.
 *
 * FAIL-FAST
 * ---------
 *
 * Someone changes the newspaper
 * while you are reading.
 *
 * You immediately stop reading.
 *
 * ============================================================================
 *
 * FAIL-SAFE
 * ---------
 *
 * You first make a photocopy.
 *
 * Even if someone changes
 * the original newspaper,
 *
 * your copy never changes.
 *
 * You continue reading safely.
 *
 * ============================================================================
 * DIFFERENCE
 * ============================================================================
 *
 * FAIL-FAST
 *
 * Uses Original Collection
 *
 * Detects Modification
 *
 * Throws Exception
 *
 * Faster
 *
 * ============================================================================
 *
 * FAIL-SAFE
 *
 * Uses Copy of Collection
 *
 * Doesn't Detect Modification
 *
 * No Exception
 *
 * Slightly More Memory
 *
 * ============================================================================
 */

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastVsFailSafe {

    public static void main(String[] args) {

        /*
         * ===============================================================
         * FAIL-FAST EXAMPLE
         * ===============================================================
         */

        System.out.println("=========== FAIL-FAST ===========");

        List<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        /*
         * Iterator is created
         * over ORIGINAL collection.
         */

        Iterator<String> iterator = fruits.iterator();

        try {

            while (iterator.hasNext()) {

                String fruit = iterator.next();

                System.out.println(fruit);

                /*
                 * Structural modification
                 * during iteration.
                 */

                if (fruit.equals("Banana")) {

                    fruits.add("Mango");

                    /*
                     * Next iterator operation
                     * throws exception.
                     */

                }
            }

        } catch (ConcurrentModificationException e) {

            System.out.println();

            System.out.println("ConcurrentModificationException occurred.");

            System.out.println("Reason:");

            System.out.println("Collection modified during iteration.");

        }

        /*
         * ===============================================================
         * FAIL-SAFE EXAMPLE
         * ===============================================================
         */

        System.out.println();

        System.out.println("=========== FAIL-SAFE ===========");

        CopyOnWriteArrayList<String> cities =
                new CopyOnWriteArrayList<>();

        cities.add("Delhi");
        cities.add("Hyderabad");
        cities.add("Bangalore");

        /*
         * Iterator works on a COPY.
         */

        Iterator<String> cityIterator =
                cities.iterator();

        while (cityIterator.hasNext()) {

            String city = cityIterator.next();

            System.out.println(city);

            /*
             * Safe modification.
             */

            if (city.equals("Hyderabad")) {

                cities.add("Chennai");

            }
        }

        System.out.println();

        System.out.println("Final Collection : " + cities);

    }
}

/*
===============================================================================

OUTPUT

=========== FAIL-FAST ===========

Apple

Banana

ConcurrentModificationException occurred.

Reason:

Collection modified during iteration.

===================================================

=========== FAIL-SAFE ===========

Delhi

Hyderabad

Bangalore

Final Collection :

[Delhi, Hyderabad, Bangalore, Chennai]

===============================================================================

EXECUTION FLOW

FAIL-FAST

ArrayList

↓

Iterator Created

↓

Iteration Starts

↓

Collection Modified

↓

Iterator Detects Modification

↓

ConcurrentModificationException

===============================================================================

FAIL-SAFE

CopyOnWriteArrayList

↓

Iterator Created

↓

Iterator Uses Copy

↓

Original Collection Modified

↓

Iterator Continues

↓

No Exception

===============================================================================

INTERVIEW QUESTIONS

Q) What is Fail-Fast?

Answer:

Fail-Fast Iterator throws
ConcurrentModificationException
if the collection is modified
during iteration.

-------------------------------------------------------------------------------

Q) What is Fail-Safe?

Answer:

Fail-Safe Iterator works on
a cloned copy of the collection,
so modifications do not affect iteration.

-------------------------------------------------------------------------------

Q) Which collections are Fail-Fast?

Answer:

✔ ArrayList

✔ LinkedList

✔ HashMap

✔ HashSet

✔ TreeMap

✔ TreeSet

-------------------------------------------------------------------------------

Q) Which collections are Fail-Safe?

Answer:

✔ CopyOnWriteArrayList

✔ CopyOnWriteArraySet

-------------------------------------------------------------------------------

Q) Which is faster?

Answer:

Fail-Fast

Reason:

It doesn't create a copy.

-------------------------------------------------------------------------------

Q) Which uses more memory?

Answer:

Fail-Safe

Reason:

It creates a copy of the collection.

-------------------------------------------------------------------------------

Q) What exception does Fail-Fast throw?

Answer:

ConcurrentModificationException

===============================================================================
*/
