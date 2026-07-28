package DatabaseDesignAndIntegration;

class Customer {

    int customerId;
    String customerName;
}

class Product {

    int productId;
    String productName;
    double price;
}

class Order {

    int orderId;
    Customer customer;
    Product product;
}

public class ERModelDemo {

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.customerId = 101;
        customer.customerName = "Nayeem";

        Product product = new Product();
        product.productId = 1;
        product.productName = "Laptop";
        product.price = 85000;

        Order order = new Order();
        order.orderId = 5001;
        order.customer = customer;
        order.product = product;

        System.out.println(customer.customerName +
                " ordered " +
                product.productName);
    }
}