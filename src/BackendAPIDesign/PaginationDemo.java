package BackendAPIDesign;

import java.util.*;

public class PaginationDemo{

    public static void main(String[] args){

        List<Integer> users=new ArrayList<>();

        for(int i=1;i<=100;i++)
            users.add(i);

        users.stream()

                .skip(20)

                .limit(10)

                .forEach(System.out::println);
    }
}