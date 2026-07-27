package BackendAPIDesign;

class MonitoringObservabilityDemo {

    public static void process(){

        long start=System.currentTimeMillis();

        System.out.println("Processing Request...");

        long end=System.currentTimeMillis();

        System.out.println("Execution Time : "+(end-start)+" ms");
    }

    public static void main(String[] args){

        process();

    }
}