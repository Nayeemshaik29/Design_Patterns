

package structural;
import java.util.ArrayList;
import java.util.List;
/*
===============================================================================

                        COMPOSITE DESIGN PATTERN

Definition:
------------
Composite Pattern allows us to treat individual objects and groups of objects
uniformly.

It organizes objects into a tree structure where both individual objects
(Leaf) and groups of objects (Composite) implement the same interface.

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose you are shopping on Amazon.

Your cart contains:

✔ Individual Products
✔ Product Bundles (Combo Offers)

A Product Bundle itself can contain multiple products.

When calculating the total price, the customer doesn't care whether an item
is a single product or a bundle.

Both are treated the same.

===============================================================================
*/

/*
===============================================================================

                        COMPONENT

Every cart item should provide:

1. getPrice()
2. display()

===============================================================================
*/

interface CartItem {

    double getPrice();

    void display(String indent);
}

/*
===============================================================================

                        LEAF

Represents an individual product.

===============================================================================
*/

class Product implements CartItem {

    private String name;
    private double price;

    public Product(String name, double price) {

        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {

        return price;
    }

    @Override
    public void display(String indent) {

        System.out.println(indent +
                "Product : " + name +
                " - ₹" + price);
    }
}

/*
===============================================================================

                        COMPOSITE

Represents a bundle containing multiple CartItems.

A bundle can contain:

✔ Products
✔ Other Bundles

===============================================================================
*/

class ProductBundle implements CartItem {

    private String bundleName;

    private List<CartItem> items = new ArrayList<>();

    public ProductBundle(String bundleName) {

        this.bundleName = bundleName;
    }

    public void addItem(CartItem item) {

        items.add(item);
    }

    @Override
    public double getPrice() {

        double total = 0;

        for (CartItem item : items) {

            total += item.getPrice();
        }

        return total;
    }

    @Override
    public void display(String indent) {

        System.out.println(indent +
                "Bundle : " + bundleName);

        for (CartItem item : items) {

            item.display(indent + "   ");
        }
    }
}

/*
===============================================================================

                        CLIENT

Client treats Product and ProductBundle exactly the same.

===============================================================================
*/

public class CompositePatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Individual Products

        ===============================================================
        */

        CartItem book =
                new Product("Atomic Habits", 499);

        CartItem phone =
                new Product("iPhone 15", 79999);

        CartItem earbuds =
                new Product("AirPods", 15999);

        CartItem charger =
                new Product("20W Charger", 1999);

        /*
        ===============================================================

        Bundle 1

        iPhone Essentials Combo

        ===============================================================
        */

        ProductBundle iphoneCombo =
                new ProductBundle("iPhone Essentials Combo");

        iphoneCombo.addItem(phone);

        iphoneCombo.addItem(earbuds);

        iphoneCombo.addItem(charger);

        /*
        ===============================================================

        Bundle 2

        Back To School Kit

        ===============================================================
        */

        ProductBundle schoolKit =
                new ProductBundle("Back To School Kit");

        schoolKit.addItem(
                new Product("Notebook Pack",249));

        schoolKit.addItem(
                new Product("Pen Set",99));

        schoolKit.addItem(
                new Product("Highlighter",149));

        /*
        ===============================================================

        Shopping Cart

        ===============================================================
        */

        List<CartItem> cart = new ArrayList<>();

        cart.add(book);

        cart.add(iphoneCombo);

        cart.add(schoolKit);

        /*
        ===============================================================

        Display Cart

        ===============================================================
        */

        System.out.println("Your Amazon Cart\n");

        double total = 0;

        for(CartItem item : cart){

            item.display("   ");

            total += item.getPrice();
        }

        System.out.println();

        System.out.println("Total : ₹" + total);
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Products

↓

Book

↓

Phone

↓

AirPods

↓

Charger

------------------------------------------------------------

Step 2

Create Bundle

iPhone Essentials Combo

↓

Phone

↓

AirPods

↓

Charger

------------------------------------------------------------

Step 3

Create Bundle

Back To School Kit

↓

Notebook

↓

Pen

↓

Highlighter

------------------------------------------------------------

Step 4

Add everything into Cart

↓

Book

↓

iPhone Combo

↓

School Kit

------------------------------------------------------------

Step 5

Display Cart

Each item calls

display()

Each item calculates

getPrice()

------------------------------------------------------------

Final Total

Book

499

+

iPhone Combo

97997

+

School Kit

497

=

₹98993

===============================================================================

Flow Diagram


                     CartItem
                        |
            -------------------------
            |                       |
            |                       |
        Product              ProductBundle
      (Leaf Node)          (Composite Node)
                                 |
                         -------------------
                         |        |         |
                     Product   Product   Product
                     (Leaf)    (Leaf)    (Leaf)

===============================================================================

Object Tree


Amazon Cart

|

|------ Atomic Habits

|

|------ iPhone Essentials Combo

|          |

|          |------ iPhone 15

|          |

|          |------ AirPods

|          |

|          |------ Charger

|

|------ Back To School Kit

           |

           |------ Notebook

           |

           |------ Pen

           |

           |------ Highlighter

===============================================================================

OUTPUT

Your Amazon Cart

Product : Atomic Habits - ₹499

Bundle : iPhone Essentials Combo

   Product : iPhone 15 - ₹79999

   Product : AirPods - ₹15999

   Product : 20W Charger - ₹1999

Bundle : Back To School Kit

   Product : Notebook Pack - ₹249

   Product : Pen Set - ₹99

   Product : Highlighter - ₹149

Total : ₹98993

===============================================================================

Advantages

✔ Treats individual objects and groups uniformly.

✔ Simplifies client code.

✔ Easy to add new composite structures.

✔ Represents hierarchical tree structures.

✔ Supports recursion naturally.

===============================================================================

Disadvantages

✘ Difficult to restrict components in some hierarchies.

✘ Can make design overly generic.

===============================================================================

When to Use

✔ Tree structures.

✔ File Systems.

✔ Organization Hierarchy.

✔ Shopping Cart.

✔ Menu Systems.

✔ GUI Components.

===============================================================================

Interview Questions

Q1. What is Composite Pattern?

Ans:

Composite Pattern allows treating individual objects and groups of objects
uniformly.

------------------------------------------------

Q2. Which is the Component?

Ans:

CartItem

------------------------------------------------

Q3. Which is the Leaf?

Ans:

Product

------------------------------------------------

Q4. Which is the Composite?

Ans:

ProductBundle

------------------------------------------------

Q5. What data structure does Composite represent?

Ans:

Tree Structure.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

------------------------------------------------

Q7. Which category does Composite belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Composite Pattern is a Structural Design Pattern that represents part-whole
hierarchies using a tree structure. It allows clients to treat individual
objects (Leaf) and groups of objects (Composite) uniformly through a common
interface.

In this example, CartItem is the Component, Product is the Leaf, and
ProductBundle is the Composite. A ProductBundle can contain multiple
CartItems, including other bundles, enabling recursive structures. This
makes the code simple, extensible, and ideal for hierarchical data such as
shopping carts, file systems, and organization charts.

===============================================================================
*/