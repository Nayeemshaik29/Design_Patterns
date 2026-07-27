package BackendAPIDesign;

import java.util.*;

public class SortingDemo{

    public static void main(String[] args){

        List<Integer> nums=Arrays.asList(8,4,1,6);

        nums.stream()

                .sorted()

                .forEach(System.out::println);

    }
}