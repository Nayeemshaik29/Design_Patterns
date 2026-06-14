// ================================
// Product Interface
// ================================
interface Logistics {
    void send();
}

// ================================
// Concrete Products
// ================================
class Road implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending package by Road 🚚");
    }
}

class Air implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending package by Air ✈️");
    }
}

class Ship implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending package by Ship 🚢");
    }
}

// ================================
// Factory Class
// ================================
class LogisticsFactory {

    public static Logistics getLogistics(String mode) {

        if(mode.equalsIgnoreCase("ROAD")) {
            return new Road();
        }

        else if(mode.equalsIgnoreCase("AIR")) {
            return new Air();
        }

        else if(mode.equalsIgnoreCase("SHIP")) {
            return new Ship();
        }

        throw new IllegalArgumentException(
                "Invalid Logistics Mode : " + mode);
    }
}

// ================================
// Service Class
// ================================
class LogisticsService {

    public void processDelivery(String mode) {

        Logistics logistics =
                LogisticsFactory.getLogistics(mode);

        logistics.send();
    }
}

// ================================
// Main Class
// ================================
public class FactoryPatternDemo {

    public static void main(String[] args) {

        LogisticsService service =
                new LogisticsService();

        System.out.println("=== Road Delivery ===");
        service.processDelivery("ROAD");

        System.out.println();

        System.out.println("=== Air Delivery ===");
        service.processDelivery("AIR");

        System.out.println();

        System.out.println("=== Ship Delivery ===");
        service.processDelivery("SHIP");
    }
}