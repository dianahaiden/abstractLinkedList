package cs1302.genlist;

import java.util.*;
import cs1302.genlistadt.GenList;
import java.util.function.*;

/**
 * This class tests some of the methods in the {@code GenList<T>} class.
 * Each method is tested with two different parameterizations of {@code GenList<T>} class.
 */
public class LinkedGenListTest {

    public static void main(String[] args) {

        System.out.println("demoMap() method");
        demoMap();

        System.out.println("\n" + "demoReduce() method");
        demoReduce();

        System.out.println("\n" + "demoFilter() method");
        demoFilter();

        System.out.println("\n" + "demoMin() method");
        demoMin();

        System.out.println("\n" + "demoAllMatch method");
        demoAllMatch();

        GenList<Integer> intList = new LinkedGenList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        GenList<Integer> intList2 = new LinkedGenList<>();
        intList2.add(1);
        intList2.add(2);
        intList2.add(3);
    } // main

    /**
     * This method tests the {@code map()} method.
     */
    public static void demoMap() {
        GenList<Integer> intList = new LinkedGenList<>();

        intList.add(1);
        intList.add(2);
        intList.add(3);

        Function<Integer, String> intToString = p -> {
            String change = p.toString();
            change += "String";
            return change;
        };

        GenList<String> stringList = intList.map(intToString);

        System.out.println("Changed Integer into String and added the word String "
                           + "to the end of each list.");
        System.out.println("Integer List: " + intList.makeString(", "));
        System.out.println("String List: " + stringList.makeString(", "));

        GenList<Double> doubleList = new LinkedGenList<>();

        doubleList.add(1.3);
        doubleList.add(4.2);
        doubleList.add(5.6);

        Function<Double, Integer> doubleToInt = p -> {
            Integer a = p.intValue();
            return a;
        };

        GenList<Integer> intList2 = doubleList.map(doubleToInt);

        System.out.println("Changed the doubles into ints");
        System.out.println("Double List: " + doubleList.makeString(", "));
        System.out.println("Int List: " + intList2.makeString(", "));

    } // demomap

    /**
     * This method tests the {@code reduce() method}.
     */
    public static void demoReduce() {
        GenList<Integer> intList = new LinkedGenList<>();

        intList.add(1);
        intList.add(2);
        intList.add(3);

        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        Integer result = intList.reduce(2, multiply);
        System.out.println("List contents: " + intList.makeString(", "));
        System.out.println("Result after multiplying with a start of 2: " + result);

        GenList<String> stringList = new LinkedGenList<>();

        stringList.add("hello");
        stringList.add("hi");
        stringList.add("hey");

        BinaryOperator<String> concat = (a, b) -> a.concat(b);

        String result2 = stringList.reduce("!!!", concat);
        System.out.println("List contents: " + stringList.makeString(", "));
        System.out.println("Result after concatenation: " + result2);
    } // demoReduce


    /**
     * This method tests the {@code filter()} method.
     */
    public static void demoFilter() {
        GenList<Integer> intList = new LinkedGenList<>();

        intList.add(2);
        intList.add(3);
        intList.add(18);

        System.out.println("List Contents before: " + intList.makeString(", "));

        Predicate<Integer> pred = value -> value % 2 == 0 && value < 10 ;
        GenList<Integer> intList2 = intList.filter(pred);

        System.out.println("List Contents after filtering for even numbers less than 10: "
                           + intList2.makeString(", "));

        GenList<String> stringList = new LinkedGenList<>();

        stringList.add("hello");
        stringList.add("hi");
        stringList.add("hey");

        System.out.println("List Contents before: " + stringList.makeString(", "));

        Predicate<String> pred2 = value -> (value.charAt(1) == 'e' && value.length() < 4);

        GenList<String> filteredStringList = stringList.filter(pred2);

        System.out.println("List Contents after filtering for words "
                           + "with less than 4 characters and e as the second letter: "
                           + filteredStringList.makeString(", "));

    } // demoFilter

    /**
     * This method tests the {@code min()} method.
     */
    public static void demoMin() {
        GenList<String> stringList = new LinkedGenList<>();

        stringList.add("hello");
        stringList.add("hi!");
        stringList.add("hey");

        System.out.println("List Contents before: " + stringList.makeString(", "));

        Comparator<String> comp = (String a, String b) -> a.charAt(2) - (b.charAt(2));

        String result = stringList.min(comp);

        System.out.println("Result after comparing charAt(2): " + result);

        GenList<Double> doubleList = new LinkedGenList<>();

        doubleList.add(1.5);
        doubleList.add(2.6);
        doubleList.add(3.2);

        System.out.println("List Contents before: " + doubleList.makeString(", "));

        Comparator<Double> comp2 = (a, b) -> a.toString().compareTo(b.toString());

        double result2 = doubleList.min(comp2);
        System.out.println("Result after comparing toString() values: " + result2);
    } // demoMin

    /**
     * This method tests the {@code allMatch()} method.
     */
    public static void demoAllMatch() {
        GenList<Integer> intList = new LinkedGenList<>();

        intList.add(2);
        intList.add(4);
        intList.add(18);

        System.out.println("List Contents before: " + intList.makeString(", "));

        Predicate<Integer> pred = value -> value % 2 == 0 && value < 20 ;

        System.out.println("AllMatch for values that are even and less than 20: "
                           + intList.allMatch(pred));

        GenList<String> stringList = new LinkedGenList<>();

        stringList.add("hello");
        stringList.add("hi");
        stringList.add("hey");

        System.out.println("List Contents before: " + stringList.makeString(", "));

        Predicate<String> pred2 = value -> (value.charAt(0) == 'h' && value.length() > 1);

        System.out.println("AllMatch for Strings that begin with h and have a length that is"
                           + " greater than 1: "
                           + stringList.allMatch(pred2));

    } // demoAllMatch
} // LinkedGenListTest
