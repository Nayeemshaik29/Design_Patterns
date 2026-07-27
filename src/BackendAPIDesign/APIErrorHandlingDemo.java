package BackendAPIDesign;

class APIErrorHandlingDemo {

    public static void getUser(int id){

        if(id<=0){
            throw new IllegalArgumentException("Invalid User Id");
        }

        System.out.println("User Found");
    }

    public static void main(String[] args){

        try{

            getUser(-10);

        }catch(Exception e){

            System.out.println(e.getMessage());

        }
    }
}