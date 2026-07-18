/*
===============================================================================

                        FLYWEIGHT DESIGN PATTERN

Definition:
------------
Flyweight Pattern minimizes memory usage by sharing common objects instead of
creating duplicate objects.

It separates:

✔ Intrinsic State (Shared)
✔ Extrinsic State (Unique)

so that multiple objects can reuse the same shared data.

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose a game has 1 million trees.

Each tree has:

Shared Data (Intrinsic State)

✔ Tree Name
✔ Color
✔ Texture

Unique Data (Extrinsic State)

✔ X Coordinate
✔ Y Coordinate

Instead of storing Name, Color, and Texture for every tree,
we store them once and let every tree share them.

This saves a huge amount of memory.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                    FLYWEIGHT

Represents the shared object.

Contains intrinsic state.

Shared among multiple trees.

===============================================================================
*/

class TreeType {

    private String name;
    private String color;
    private String texture;

    public TreeType(String name,
                    String color,
                    String texture) {

        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {

        System.out.println(
                "Drawing "
                        + name
                        + " Tree at ("
                        + x
                        + ", "
                        + y
                        + ")"
        );
    }
}

/*
===============================================================================

                    CONTEXT

Represents one tree.

Contains:

Extrinsic State

x
y

Intrinsic State

TreeType

===============================================================================
*/

class Tree {

    private int x;

    private int y;

    private TreeType treeType;

    public Tree(int x,
                int y,
                TreeType treeType) {

        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw() {

        treeType.draw(x, y);
    }
}

/*
===============================================================================

                    FLYWEIGHT FACTORY

Creates Flyweights only once.

Returns existing object if already created.

===============================================================================
*/

class TreeFactory {

    private static Map<String, TreeType> treeTypes =
            new HashMap<>();

    public static TreeType getTreeType(
            String name,
            String color,
            String texture) {

        String key =
                name + "-" + color + "-" + texture;

        if (!treeTypes.containsKey(key)) {

            System.out.println(
                    "Creating New TreeType : "
                            + key);

            treeTypes.put(
                    key,
                    new TreeType(name,
                            color,
                            texture)
            );
        }

        return treeTypes.get(key);
    }
}

/*
===============================================================================

                    CLIENT OBJECT

Forest contains millions of trees.

All trees share TreeType objects.

===============================================================================
*/

class Forest {

    private List<Tree> trees =
            new ArrayList<>();

    public void plantTree(
            int x,
            int y,
            String name,
            String color,
            String texture) {

        Tree tree =
                new Tree(
                        x,
                        y,
                        TreeFactory.getTreeType(
                                name,
                                color,
                                texture
                        )
                );

        trees.add(tree);
    }

    public void draw() {

        for (Tree tree : trees) {

            tree.draw();
        }
    }
}

/*
===============================================================================

                        CLIENT

Client only plants trees.

Factory automatically shares TreeType objects.

===============================================================================
*/

public class FlyweightPatternDemo {

    public static void main(String[] args) {

        Forest forest = new Forest();

        /*
        ===============================================================

        Planting One Million Trees

        Only ONE TreeType object is created.

        ===============================================================
        */

        for (int i = 0; i < 1000000; i++) {

            forest.plantTree(
                    i,
                    i,
                    "Oak",
                    "Green",
                    "Rough"
            );
        }

        System.out.println();

        System.out.println(
                "Successfully Planted 1 Million Trees.");
    }
}

/*
===============================================================================

Dry Run

Step 1

Client

creates

Forest

------------------------------------------------------------

Step 2

Plant Tree

↓

TreeFactory

↓

Checks

Oak-Green-Rough

------------------------------------------------------------

Not Present

↓

Creates

TreeType

↓

Stores in Map

------------------------------------------------------------

Step 3

Next Tree

↓

Again

TreeFactory

↓

Checks

Oak-Green-Rough

↓

Already Exists

↓

Returns Existing TreeType

------------------------------------------------------------

Step 4

Repeat

1 Million Times

↓

Still Only

ONE

TreeType Object

===============================================================================

Flow Diagram


                    Client

                       |

                    Forest

                       |

                  plantTree()

                       |

                  TreeFactory

                       |

          -------------------------

          |                       |

   Already Exists          Doesn't Exist

          |                       |

Return Existing         Create TreeType

          |                       |

          --------Shared-----------

                     |

                   TreeType
              (Intrinsic State)

                     |

                ----------------

                |              |

             Tree          Tree

          x,y only      x,y only

===============================================================================

Intrinsic vs Extrinsic State


Intrinsic State (Shared)

✔ Name

✔ Color

✔ Texture

-------------------------------

Extrinsic State (Unique)

✔ X Coordinate

✔ Y Coordinate

===============================================================================

OUTPUT

Creating New TreeType :

Oak-Green-Rough

Successfully Planted 1 Million Trees.

===============================================================================

Advantages

✔ Reduces memory usage.

✔ Shares common objects.

✔ Improves performance.

✔ Suitable for millions of similar objects.

✔ Follows Object Pooling concept.

===============================================================================

Disadvantages

✘ Increases code complexity.

✘ Requires separating intrinsic and extrinsic state.

✘ Not useful when objects are mostly unique.

===============================================================================

When to Use

✔ Games

✔ Forest Simulation

✔ Text Editors

✔ Character Rendering

✔ Maps

✔ Icons

✔ Caching Objects

===============================================================================

Interview Questions

Q1. What is Flyweight Pattern?

Ans:

Flyweight Pattern reduces memory usage by sharing common objects.

------------------------------------------------

Q2. Which is the Flyweight?

Ans:

TreeType

------------------------------------------------

Q3. Which is the Context?

Ans:

Tree

------------------------------------------------

Q4. Which class creates Flyweights?

Ans:

TreeFactory

------------------------------------------------

Q5. What is Intrinsic State?

Ans:

Shared state.

Example:

Tree Name

Color

Texture

------------------------------------------------

Q6. What is Extrinsic State?

Ans:

Unique state.

Example:

X Coordinate

Y Coordinate

------------------------------------------------

Q7. Why use Flyweight?

Ans:

To reduce memory consumption when many similar objects exist.

------------------------------------------------

Q8. Which category does Flyweight belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Flyweight Pattern is a Structural Design Pattern that minimizes memory usage
by sharing common objects instead of creating duplicate objects. It separates
shared data (Intrinsic State) from unique data (Extrinsic State), allowing
many objects to reuse the same shared instance.

In this example, TreeType is the Flyweight containing shared properties like
name, color, and texture. Tree is the Context containing unique coordinates.
TreeFactory ensures only one TreeType object is created for identical tree
properties. This allows millions of trees to share a single TreeType object,
making the application highly memory efficient.

===============================================================================
*/