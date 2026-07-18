/*
===============================================================================

                        VISITOR DESIGN PATTERN

Definition:
------------
Visitor Pattern allows adding new operations to existing object structures
without modifying their classes.

Instead of putting every operation inside the objects, we move the operations
to separate Visitor classes. Each object accepts a visitor, and the visitor
performs the required operation.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose an E-Commerce application has different item types.

✔ Physical Product
✔ Digital Product
✔ Gift Card

Different business operations can be performed on them:

✔ Generate Invoice
✔ Calculate Shipping Cost
✔ Generate Reports
✔ Apply Discounts

Instead of modifying every product class whenever a new operation is added,
Visitor Pattern keeps operations separate from the object structure.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        ELEMENT INTERFACE

Every item should accept a visitor.

===============================================================================
*/

interface Item {

    void accept(ItemVisitor visitor);
}

/*
===============================================================================

                CONCRETE ELEMENT

Physical Product

===============================================================================
*/

class PhysicalProduct implements Item {

    String name;

    double weight;

    public PhysicalProduct(String name,
                           double weight) {

        this.name = name;
        this.weight = weight;
    }

    @Override
    public void accept(ItemVisitor visitor) {

        visitor.visit(this);
    }
}

/*
===============================================================================

                CONCRETE ELEMENT

Digital Product

===============================================================================
*/

class DigitalProduct implements Item {

    String name;

    int downloadSizeInMB;

    public DigitalProduct(String name,
                          int downloadSizeInMB) {

        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }

    @Override
    public void accept(ItemVisitor visitor) {

        visitor.visit(this);
    }
}

/*
===============================================================================

                CONCRETE ELEMENT

Gift Card

===============================================================================
*/

class GiftCard implements Item {

    String code;

    double amount;

    public GiftCard(String code,
                    double amount) {

        this.code = code;
        this.amount = amount;
    }

    @Override
    public void accept(ItemVisitor visitor) {

        visitor.visit(this);
    }
}

/*
===============================================================================

                    VISITOR INTERFACE

Every visitor should provide operations for all item types.

===============================================================================
*/

interface ItemVisitor {

    void visit(PhysicalProduct item);

    void visit(DigitalProduct item);

    void visit(GiftCard item);
}

/*
===============================================================================

                CONCRETE VISITOR

Invoice Generator

===============================================================================
*/

class InvoiceVisitor implements ItemVisitor {

    @Override
    public void visit(PhysicalProduct item) {

        System.out.println(
                "Invoice : "
                        + item.name
                        + " - Shipping to Customer");
    }

    @Override
    public void visit(DigitalProduct item) {

        System.out.println(
                "Invoice : "
                        + item.name
                        + " - Email with Download Link");
    }

    @Override
    public void visit(GiftCard item) {

        System.out.println(
                "Invoice : Gift Card - Code : "
                        + item.code);
    }
}

/*
===============================================================================

                CONCRETE VISITOR

Shipping Cost Calculator

===============================================================================
*/

class ShippingCostVisitor implements ItemVisitor {

    @Override
    public void visit(PhysicalProduct item) {

        System.out.println(
                "Shipping Cost for "
                        + item.name
                        + " : Rs. "
                        + (item.weight * 10));
    }

    @Override
    public void visit(DigitalProduct item) {

        System.out.println(
                item.name
                        + " is Digital -> No Shipping Cost.");
    }

    @Override
    public void visit(GiftCard item) {

        System.out.println(
                "Gift Card delivered via Email -> No Shipping Cost.");
    }
}

/*
===============================================================================

                        CLIENT

Creates items and visitors.

Each visitor performs a different operation on the same objects.

===============================================================================
*/

public class VisitorPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Items

        ===============================================================
        */

        List<Item> items = new ArrayList<>();

        items.add(
                new PhysicalProduct(
                        "Shoes",
                        1.2));

        items.add(
                new DigitalProduct(
                        "E-Book",
                        100));

        items.add(
                new GiftCard(
                        "TUF500",
                        500));

        /*
        ===============================================================

        Create Visitors

        ===============================================================
        */

        ItemVisitor invoiceVisitor =
                new InvoiceVisitor();

        ItemVisitor shippingVisitor =
                new ShippingCostVisitor();

        /*
        ===============================================================

        Visit Every Item

        ===============================================================
        */

        for (Item item : items) {

            item.accept(invoiceVisitor);

            item.accept(shippingVisitor);

            System.out.println();
        }
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Items

↓

Physical Product

↓

Digital Product

↓

Gift Card

------------------------------------------------------------

Step 2

Create Visitors

↓

Invoice Visitor

↓

Shipping Visitor

------------------------------------------------------------

Step 3

Physical Product

↓

accept()

↓

Invoice Visitor

↓

Generate Invoice

↓

Shipping Visitor

↓

Calculate Shipping Cost

------------------------------------------------------------

Step 4

Digital Product

↓

accept()

↓

Invoice Visitor

↓

Generate Invoice

↓

Shipping Visitor

↓

No Shipping Cost

------------------------------------------------------------

Step 5

Gift Card

↓

accept()

↓

Invoice Visitor

↓

Generate Invoice

↓

Shipping Visitor

↓

No Shipping Cost

===============================================================================

Flow Diagram


                    Item
             (Element Interface)

            /         |          \

           /          |           \

 PhysicalProduct  DigitalProduct  GiftCard

           \          |           /

            \         |          /

              ItemVisitor
          (Visitor Interface)

           /                  \

          /                    \

InvoiceVisitor     ShippingCostVisitor

===============================================================================

Execution Flow


Client

   |

Item

   |

accept(visitor)

   |

Visitor

   |

visit(item)

   |

Perform Operation

===============================================================================

OUTPUT

Invoice : Shoes - Shipping to Customer

Shipping Cost for Shoes : Rs. 12.0

Invoice : E-Book - Email with Download Link

E-Book is Digital -> No Shipping Cost.

Invoice : Gift Card - Code : TUF500

Gift Card delivered via Email -> No Shipping Cost.

===============================================================================

Advantages

✔ Easily add new operations without modifying existing classes.

✔ Follows Open/Closed Principle.

✔ Separates business logic from object structure.

✔ Clean and maintainable design.

✔ Good for stable object hierarchies.

===============================================================================

Disadvantages

✘ Adding a new Element requires modifying all Visitors.

✘ Can become complex with many element types.

✘ Breaks encapsulation if visitors need internal object details.

===============================================================================

When to Use

✔ Report Generation.

✔ Invoice Processing.

✔ Tax Calculation.

✔ Shipping Cost Calculation.

✔ Compiler Syntax Trees (AST).

✔ File System Operations.

===============================================================================

Interview Questions

Q1. What is Visitor Pattern?

Ans:

Visitor Pattern allows adding new operations to an object structure
without modifying the object classes.

------------------------------------------------

Q2. Which is the Element?

Ans:

Item.

------------------------------------------------

Q3. Which are the Concrete Elements?

Ans:

PhysicalProduct

DigitalProduct

GiftCard

------------------------------------------------

Q4. Which is the Visitor?

Ans:

ItemVisitor.

------------------------------------------------

Q5. Which are the Concrete Visitors?

Ans:

InvoiceVisitor

ShippingCostVisitor

------------------------------------------------

Q6. Why use Visitor Pattern?

Ans:

To add new operations without changing existing object classes.

------------------------------------------------

Q7. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

New visitors can be added without modifying element classes.

------------------------------------------------

Q8. Which category does Visitor belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Visitor Pattern is a Behavioral Design Pattern that separates operations
from the objects on which they operate. Instead of adding new methods to
existing classes, new functionality is introduced through Visitor classes.

In this example, Item is the Element interface, PhysicalProduct,
DigitalProduct, and GiftCard are the Concrete Elements, ItemVisitor is the
Visitor interface, and InvoiceVisitor and ShippingCostVisitor are the
Concrete Visitors. Each item accepts a visitor, allowing different
operations like invoice generation and shipping cost calculation without
modifying the item classes, making the design flexible and maintainable.

===============================================================================
*/