/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellolambda;

import static java.lang.Character.isDigit;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 *
 * @author pkipping
 */
public class App {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		basicStreaming();

//		streamCollection();
//		streamParallel();
//		personStream();
		List<Foo> foos = new ArrayList<>();

// create foos
		IntStream
				.range(1, 4)
				.forEach(i -> foos.add(new Foo("Foo" + i)));

// create bars
		foos.forEach(f
				-> IntStream
						.range(1, 4)
						.forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		foos.stream()
				.flatMap(f -> f.bars.stream())
				.forEach(b -> System.out.println(b.name));

//		IntStream.range(1, 4)
//				.mapToObj(i -> new Foo("Foo" + i))
//				.peek(f -> IntStream.range(1, 4)
//					.mapToObj(i -> new Bar("Bar" + i + " <- " f.name))
//					.forEach(f.bars::add))
//				.flatMap(f -> f.bars.stream())
//				.forEach(b -> System.out.println(b.name));
		List<List<Integer>> listOfLists = Arrays.asList(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5),
				Arrays.asList(6, 7, 8),
				Arrays.asList(10, 11, 13, 15)
		);

		List<Integer> flattenedList = listOfLists.stream()
				.flatMap(list -> list.stream()) // Flattening step
				.collect(Collectors.toList());

		System.out.println(flattenedList); // Output: [1, 2, 3, 4, 5, 6, 7, 8]

		// Create a list of employees
		List<Employee> employees = Arrays.asList(
				new Employee("Alice", Arrays.asList("123-456-7890", "987-654-3210")),
				new Employee("Bob", Arrays.asList("555-555-5555")),
				new Employee("Charlie", Arrays.asList("111-222-3333", "444-555-6666"))
		);

		// Use flatMap to flatten the list of phone numbers
		List<String> allPhoneNumbers = employees.stream()
				.flatMap(employee -> employee.getPhoneNumbers().stream())
				.collect(Collectors.toList());

		System.out.println(allPhoneNumbers);

	}

	protected static void personStream() {
		List<Person> persons
				= Arrays.asList(
						new Person("Max", 18),
						new Person("Peter", 23),
						new Person("Pamela", 23),
						new Person("David", 12));

		List<Person> filtered
				= persons
						.stream()
						.filter(p -> p.name.startsWith("P"))
						.collect(Collectors.toList());

		System.out.println(filtered);    // [Peter, Pamela]

		Map<Integer, List<Person>> personsByAge = persons
				.stream()
				.collect(Collectors.groupingBy(p -> p.age));

		personsByAge
				.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
	}

	protected static void streamParallel() {
		int max = 10000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		long t0 = System.nanoTime();

//        long count = values.stream().sorted().count();
		long count = values.parallelStream().sorted().count();

		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential or parallel sort took: %d ms", millis));

		List<String> myList
				= Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList
				.stream()
				.filter(s -> s.startsWith("c"))
				.map(String::toUpperCase)
				.sorted()
				.forEach(System.out::println);
	}

	protected static void streamCollection() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		stringCollection
				.stream()
				.filter((s) -> s.startsWith("a"))
				.forEach(System.out::println);

		stringCollection
				.stream()
				.sorted()
				.filter((s) -> s.startsWith("a"))
				.forEach(System.out::println);

		System.out.println(stringCollection);

		stringCollection
				.stream()
				.map(String::toUpperCase)
				.sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);

		Optional<String> reduced
				= stringCollection
						.stream()
						.sorted()
						.reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
	}

	protected static void basicStreaming() {
		// Java 8 stuff!!!

		//another comment
		//using IntelliJ now
		Runnable noArguments = () -> System.out.println("Hello World");

		BinaryOperator<Long> add = (x, y) -> x + y;

		BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

		Predicate<Integer> atLeast5 = x -> x > 5;

		List<String> collected = Stream.of("a", "b", "hello")
				.map(string -> string.toUpperCase())
				.collect(toList());

		assertEquals(asList("A", "B", "HELLO"), collected);

		List<String> beginningWithNumbers
				= Stream.of("a", "1abc", "abc1")
						.filter(value -> isDigit(value.charAt(0)))
						.collect(toList());

		assertEquals(asList("1abc"), beginningWithNumbers);
	}

}
