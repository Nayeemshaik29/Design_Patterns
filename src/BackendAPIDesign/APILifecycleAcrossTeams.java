package BackendAPIDesign;

class APILifecycleAcrossTeams {

    static void designAPI() {
        System.out.println("Backend Team: Designing API Contract...");
    }

    static void frontendDevelopment() {
        System.out.println("Frontend Team: Integrating API...");
    }

    static void testing() {
        System.out.println("QA Team: Testing API...");
    }

    static void deployment() {
        System.out.println("DevOps Team: Deploying API...");
    }

    public static void main(String[] args) {

        designAPI();
        frontendDevelopment();
        testing();
        deployment();

        System.out.println("API Successfully Released.");
    }
}