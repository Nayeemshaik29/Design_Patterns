package BackendAPIDesign;

class RateLimitingDemo {

    int count=0;

    void request(){

        count++;

        if(count>5)

            System.out.println("429 Too Many Requests");

        else

            System.out.println("Request Accepted");
    }

    public static void main(String[] args){

        RateLimitingDemo api=new RateLimitingDemo();

        for(int i=1;i<=7;i++)

            api.request();

    }
}