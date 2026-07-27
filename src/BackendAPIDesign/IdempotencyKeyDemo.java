package BackendAPIDesign;

import java.util.*;

class PaymentServer {

    private Set<String> processedKeys = new HashSet<>();

    public void pay(String key) {

        if(processedKeys.contains(key)) {
            System.out.println("Duplicate Request Ignored.");
            return;
        }

        processedKeys.add(key);

        System.out.println("Payment Successful.");
    }

    public static void main(String[] args) {

        PaymentServer server = new PaymentServer();

        server.pay("ABC123");
        server.pay("ABC123");
    }
}