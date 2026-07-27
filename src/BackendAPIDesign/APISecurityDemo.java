package BackendAPIDesign;

class APISecurityDemo {

    static String API_KEY="TUF123";

    static void access(String key){

        if(API_KEY.equals(key))

            System.out.println("Access Granted");

        else

            System.out.println("Unauthorized");
    }

    public static void main(String[] args){

        access("ABC");

        access("TUF123");
    }
}