// ===============================
// Eager Singleton
// ===============================
class EagerSingleton {

    private static final EagerSingleton instance =
            new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Eager Singleton Instance");
    }
}

// ===============================
// Lazy Singleton
// ===============================
class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {

        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

    public void showMessage() {
        System.out.println("Lazy Singleton Instance");
    }
}

// ===============================
// Thread Safe Singleton
// ===============================
class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {

        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }

    public void showMessage() {
        System.out.println("Thread Safe Singleton Instance");
    }
}

// ===============================
// Double Checked Locking Singleton
// ===============================
class DoubleCheckedSingleton {

    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {

        if (instance == null) {

            synchronized (DoubleCheckedSingleton.class) {

                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }

    public void showMessage() {
        System.out.println("Double Checked Locking Instance");
    }
}

// ===============================
// Bill Pugh Singleton
// ===============================
class BillPughSingleton {

    private BillPughSingleton() {}

    private static class Holder {

        private static final BillPughSingleton INSTANCE =
                new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Bill Pugh Singleton Instance");
    }
}

// ===============================
// Main Class
// ===============================
public class SingletonDemo {

    public static void main(String[] args) {

        System.out.println("===== Eager Singleton =====");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();

        eager1.showMessage();
        System.out.println("Same Object: " + (eager1 == eager2));

        System.out.println();

        System.out.println("===== Lazy Singleton =====");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();

        lazy1.showMessage();
        System.out.println("Same Object: " + (lazy1 == lazy2));

        System.out.println();

        System.out.println("===== Thread Safe Singleton =====");
        ThreadSafeSingleton ts1 =
                ThreadSafeSingleton.getInstance();

        ThreadSafeSingleton ts2 =
                ThreadSafeSingleton.getInstance();

        ts1.showMessage();
        System.out.println("Same Object: " + (ts1 == ts2));

        System.out.println();

        System.out.println("===== Double Checked Singleton =====");
        DoubleCheckedSingleton dc1 =
                DoubleCheckedSingleton.getInstance();

        DoubleCheckedSingleton dc2 =
                DoubleCheckedSingleton.getInstance();

        dc1.showMessage();
        System.out.println("Same Object: " + (dc1 == dc2));

        System.out.println();

        System.out.println("===== Bill Pugh Singleton =====");
        BillPughSingleton bp1 =
                BillPughSingleton.getInstance();

        BillPughSingleton bp2 =
                BillPughSingleton.getInstance();

        bp1.showMessage();
        System.out.println("Same Object: " + (bp1 == bp2));
    }
}