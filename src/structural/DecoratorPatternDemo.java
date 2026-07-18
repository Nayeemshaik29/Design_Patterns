package structural;/*
===============================================================================

                        DECORATOR DESIGN PATTERN

Definition:
------------
Decorator Pattern allows us to add new functionality or behavior to an object
dynamically at runtime without modifying its existing code.

It wraps the original object inside another object (Decorator) that provides
additional features.

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose you order a Pizza.

You first choose:

Margherita Pizza

Then you customize it by adding:

✔ Extra Cheese
✔ Olives
✔ Stuffed Crust

Instead of creating separate classes like:

MargheritaWithCheese
MargheritaWithOlives
MargheritaWithCheeseOlives
MargheritaWithCheeseOlivesStuffedCrust

Decorator Pattern allows us to keep wrapping the pizza with new toppings.

===============================================================================
*/

/*
===============================================================================

                        COMPONENT INTERFACE

Every Pizza should provide:

1. getDescription()
2. getCost()

===============================================================================
*/

interface Pizza {

    String getDescription();

    double getCost();
}

/*
===============================================================================

                    CONCRETE COMPONENTS

These are the basic pizzas.

===============================================================================
*/

class PlainPizza implements Pizza {

    @Override
    public String getDescription() {

        return "Plain Pizza";
    }

    @Override
    public double getCost() {

        return 150.00;
    }
}

class MargheritaPizza implements Pizza {

    @Override
    public String getDescription() {

        return "Margherita Pizza";
    }

    @Override
    public double getCost() {

        return 200.00;
    }
}

/*
===============================================================================

                    ABSTRACT DECORATOR

Implements Pizza interface.

Holds a reference of Pizza.

Every topping extends this decorator.

===============================================================================
*/

abstract class PizzaDecorator implements Pizza {

    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {

        this.pizza = pizza;
    }
}

/*
===============================================================================

                CONCRETE DECORATOR

                Extra Cheese

Adds:

✔ Description
✔ Cost

===============================================================================
*/

class ExtraCheese extends PizzaDecorator {

    public ExtraCheese(Pizza pizza) {

        super(pizza);
    }

    @Override
    public String getDescription() {

        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {

        return pizza.getCost() + 40;
    }
}

/*
===============================================================================

                CONCRETE DECORATOR

                    Olives

===============================================================================
*/

class Olives extends PizzaDecorator {

    public Olives(Pizza pizza) {

        super(pizza);
    }

    @Override
    public String getDescription() {

        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {

        return pizza.getCost() + 30;
    }
}

/*
===============================================================================

                CONCRETE DECORATOR

                Stuffed Crust

===============================================================================
*/

class StuffedCrust extends PizzaDecorator {

    public StuffedCrust(Pizza pizza) {

        super(pizza);
    }

    @Override
    public String getDescription() {

        return pizza.getDescription() + ", Stuffed Crust";
    }

    @Override
    public double getCost() {

        return pizza.getCost() + 50;
    }
}

/*
===============================================================================

                        CLIENT

Client keeps decorating the same Pizza object.

Each decorator wraps the previous object.

===============================================================================
*/

public class DecoratorPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Step 1

        Create Base Pizza

        Margherita Pizza

        ===============================================================
        */

        Pizza myPizza = new MargheritaPizza();

        /*
        ===============================================================

        Step 2

        Add Extra Cheese

        ===============================================================
        */

        myPizza = new ExtraCheese(myPizza);

        /*
        ===============================================================

        Step 3

        Add Olives

        ===============================================================
        */

        myPizza = new Olives(myPizza);

        /*
        ===============================================================

        Step 4

        Add Stuffed Crust

        ===============================================================
        */

        myPizza = new StuffedCrust(myPizza);

        /*
        ===============================================================

        Final Result

        ===============================================================
        */

        System.out.println("Pizza Description : " + myPizza.getDescription());

        System.out.println("Total Cost : ₹" + myPizza.getCost());

    }
}

/*
===============================================================================

Dry Run

Step 1

Create

MargheritaPizza

Description

Margherita Pizza

Cost

₹200

------------------------------------------------------------

Step 2

Wrap with

ExtraCheese

Description

Margherita Pizza,
Extra Cheese

Cost

200 + 40 = ₹240

------------------------------------------------------------

Step 3

Wrap with

Olives

Description

Margherita Pizza,
Extra Cheese,
Olives

Cost

240 + 30 = ₹270

------------------------------------------------------------

Step 4

Wrap with

StuffedCrust

Description

Margherita Pizza,
Extra Cheese,
Olives,
Stuffed Crust

Cost

270 + 50 = ₹320

------------------------------------------------------------

Final Output

Pizza Description :

Margherita Pizza,
Extra Cheese,
Olives,
Stuffed Crust

Total Cost :

₹320

===============================================================================

Flow Diagram


                     Pizza (Component)
                    /                  \
                   /                    \
      PlainPizza                  MargheritaPizza
          |                             |
          +-------------+---------------+
                        |
                  Pizza Object
                        |
                ExtraCheese Decorator
                        |
                    Olives Decorator
                        |
                StuffedCrust Decorator
                        |
                 Final Customized Pizza


===============================================================================

Object Wrapping Flow


MargheritaPizza

      |

ExtraCheese

      |

Olives

      |

StuffedCrust

      |

Client


Each decorator wraps the previous object.

===============================================================================

OUTPUT

Pizza Description :
Margherita Pizza, Extra Cheese, Olives, Stuffed Crust

Total Cost :
₹320.0

===============================================================================

Advantages

✔ Adds functionality dynamically at runtime.

✔ Follows Open/Closed Principle.

✔ No modification of existing classes.

✔ Avoids creating many subclasses.

✔ Flexible and reusable.

✔ Multiple decorators can be combined.

===============================================================================

Disadvantages

✘ Many small decorator classes.

✘ Debugging becomes slightly difficult due to multiple wrappers.

✘ Order of decorators may affect behavior.

===============================================================================

When to Use

✔ When features should be added dynamically.

✔ When subclass explosion should be avoided.

✔ When different combinations of features are required.

✔ When you want to extend behavior without changing existing code.

===============================================================================

Interview Questions

Q1. What is Decorator Pattern?

Ans:
Decorator Pattern is a Structural Design Pattern that adds new functionality
to an object dynamically without modifying its original class.

------------------------------------------------

Q2. Which is the Component?

Ans:
Pizza interface.

------------------------------------------------

Q3. Which are the Concrete Components?

Ans:

PlainPizza

MargheritaPizza

------------------------------------------------

Q4. Which is the Decorator?

Ans:

PizzaDecorator

------------------------------------------------

Q5. Which are the Concrete Decorators?

Ans:

ExtraCheese

Olives

StuffedCrust

------------------------------------------------

Q6. Why not use inheritance?

Ans:

Inheritance creates many subclasses for every possible combination.

Decorator allows features to be added dynamically.

------------------------------------------------

Q7. Which SOLID principle is followed?

Ans:

Open/Closed Principle

Open for Extension

Closed for Modification

------------------------------------------------

Q8. Which category does Decorator belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Decorator Pattern is a Structural Design Pattern that allows us to add
new functionality to an object dynamically by wrapping it inside decorator
objects instead of modifying the original class.

In this example, the Pizza interface is the Component, MargheritaPizza is
the Concrete Component, PizzaDecorator is the Abstract Decorator, and
ExtraCheese, Olives, and StuffedCrust are Concrete Decorators. Each
decorator wraps the previous pizza object and adds its own description and
cost, making the solution flexible, reusable, and compliant with the
Open/Closed Principle.

=================================================================================
/*
===============================================================================
 */