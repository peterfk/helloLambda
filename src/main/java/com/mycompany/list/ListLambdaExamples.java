package com.mycompany.list;

import java.util.ArrayList;
import java.util.List;

public class ListLambdaExamples {
    public static void main(String[] args) {
        // Creating a sample list
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("avocado");

        // 1. forEach(Consumer<T> action)
        System.out.println("1. forEach example:");
        fruits.forEach(fruit -> System.out.println("I like " + fruit));

        // 2. removeIf(Predicate<T> filter)
        System.out.println("\n2. removeIf example:");
        System.out.println("Before removeIf: " + fruits);
        // Remove fruits that start with a lowercase letter
        fruits.removeIf(fruit -> Character.isLowerCase(fruit.charAt(0)));
        System.out.println("After removeIf: " + fruits);

        // Adding more elements for next examples
        fruits.add("Elderberry");
        fruits.add("Fig");

        // 3. replaceAll(UnaryOperator<T> operator)
        System.out.println("\n3. replaceAll example:");
        System.out.println("Before replaceAll: " + fruits);
        // Convert all fruits to uppercase
        fruits.replaceAll(fruit -> fruit.toUpperCase());
        System.out.println("After replaceAll: " + fruits);

        // 4. sort(Comparator<T> c)
        System.out.println("\n4. sort example:");
        System.out.println("Before sort: " + fruits);
        // Sort by string length (shortest to longest)
        fruits.sort((fruit1, fruit2) -> fruit1.length() - fruit2.length());
        System.out.println("After sorting by length: " + fruits);

        // Another sort example: alphabetical order
        fruits.sort((fruit1, fruit2) -> fruit1.compareTo(fruit2));
        System.out.println("After sorting alphabetically: " + fruits);
    }
}