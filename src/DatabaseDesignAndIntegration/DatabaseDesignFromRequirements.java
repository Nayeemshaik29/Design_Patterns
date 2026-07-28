package DatabaseDesignAndIntegration;

class Customer1 {

    int id;
    String name;
}

class Restaurant {

    int id;
    String name;
}

class Menu {

    int id;
    String itemName;
}

class Order1 {

    int id;
    Customer customer;
    Restaurant restaurant;
}

class Payment {

    int id;
    Order order;
    double amount;
}

public class DatabaseDesignFromRequirements {

    public static void main(String[] args) {

        System.out.println("Requirement Converted Into Tables Successfully.");

    }
}