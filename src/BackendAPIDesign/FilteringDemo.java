package BackendAPIDesign;

import java.util.*;

public class FilteringDemo {

    public static void main(String[] args){

        List<String> products=Arrays.asList("Phone","Laptop","Phone Cover");

        products.stream()

                .filter(x->x.contains("Phone"))

                .forEach(System.out::println);

    }
}