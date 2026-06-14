import java.util.List;
import java.util.Arrays;

// ======================================
// Product Class
// ======================================
class BurgerMeal {

    // Mandatory Fields
    private final String bunType;
    private final String patty;

    // Optional Fields
    private final boolean cheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    // Private Constructor
    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    // Display Method
    public void showMeal() {

        System.out.println("Bun Type : " + bunType);
        System.out.println("Patty    : " + patty);
        System.out.println("Cheese   : " + cheese);

        if (toppings != null)
            System.out.println("Toppings : " + toppings);

        if (side != null)
            System.out.println("Side     : " + side);

        if (drink != null)
            System.out.println("Drink    : " + drink);

        System.out.println("--------------------------------");
    }

    // ======================================
    // Builder Class
    // ======================================
    public static class BurgerBuilder {

        // Mandatory
        private final String bunType;
        private final String patty;

        // Optional
        private boolean cheese;
        private List<String> toppings;
        private String side;
        private String drink;

        public BurgerBuilder(String bunType, String patty) {
            this.bunType = bunType;
            this.patty = patty;
        }

        public BurgerBuilder withCheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public BurgerBuilder withToppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
}

// ======================================
// Main Class
// ======================================
public class BuilderPatternDemo {

    public static void main(String[] args) {

        // Simple Burger
        BurgerMeal simpleBurger =
                new BurgerMeal.BurgerBuilder(
                        "Wheat Bun",
                        "Veg Patty")
                        .build();

        // Cheese Burger
        BurgerMeal cheeseBurger =
                new BurgerMeal.BurgerBuilder(
                        "Wheat Bun",
                        "Chicken Patty")
                        .withCheese(true)
                        .build();

        // Fully Loaded Burger
        BurgerMeal loadedBurger =
                new BurgerMeal.BurgerBuilder(
                        "Multigrain Bun",
                        "Chicken Patty")
                        .withCheese(true)
                        .withToppings(
                                Arrays.asList(
                                        "Lettuce",
                                        "Onion",
                                        "Jalapeno"))
                        .withSide("French Fries")
                        .withDrink("Coke")
                        .build();

        System.out.println("===== Simple Burger =====");
        simpleBurger.showMeal();

        System.out.println("===== Cheese Burger =====");
        cheeseBurger.showMeal();

        System.out.println("===== Loaded Burger =====");
        loadedBurger.showMeal();
    }
}