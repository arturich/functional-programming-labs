package com.adhoc.java.functional.labs.streams;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class StreamsLab {

	// TODO: replace "REPLACE_WITH_LAMBDA" to filter all the people older than 25
	@Test
	void olderThan25Test() {
		
		List<People> people = new ArrayList<>();
		People.getSomePeople().stream()
				 .filter(p -> p.age > 25)
				.forEach(System.out::println);
		//TODO: Add collector and send results to people
		people = People.getSomePeople().stream()
		 .filter(p -> p.age > 25)
		 .collect(Collectors.toList());

		Assertions.assertEquals(people.size(), 3);		
		
	}

	// TODO: replace "STREAM_OPERATION" and "REPLACE_WITH_LAMBDA" to filter all the
	// people with names of 5 letters
	@Test
	void namesOf5LettersTest() {
		List<People> people = new ArrayList<>();
		people =  People.getSomePeople().stream()
				// .STREAM_OPERATION(REPLACE_WITH_LAMBDA)
				.filter(p -> p.name.length() == 5)
				.collect(Collectors.toList());
				//.forEach(System.out::println);
				;
		Assertions.assertEquals(people.size(), 4);
	}

	// TODO: replace TODO section on bellow method to get all the people younger
	// than 20 and print its name on uppercase
	@Test
	void namesonUpperCaseTest() {
		
		List<People> people = new ArrayList<>();
		People.getSomePeople().stream()
				// TODO: streams operations as requested
				.filter(p -> p.age < 20)
				.forEach(p-> System.out.println(p.name.toUpperCase()));
		people = People.getSomePeople().stream()
		// TODO: streams operations as requested
		.filter(p -> p.age < 20)
		.collect(Collectors.toList());
		Assertions.assertEquals(people.size(), 3);
	}

	// TODO: replace TODO section on bellow method to get the oldest person and
	// print its name
	@Test
	void olderPersonTest() {
		Comparator<People> c1 = Comparator.comparing((People s) -> s.age);
		String olderPerson = "";
		olderPerson = People.getSomePeople().stream()
				// TODO: streams operations as requested				
				.max(c1)
				.get().name;
		
		Assertions.assertEquals("Pedro", olderPerson);
		
	}

	// TODO: replace TODO section on bellow method to sort all the people and print
	// them (not just the name, the complete object)
	@Test
	void sortPeopleByNameTest() {
		List<People> people = new ArrayList<>();
		Comparator<People> c1 = Comparator.comparing((People s) -> s.name);
		People.getSomePeople().stream()
				// TODO: streams operations as requested
				.sorted(c1)
				.forEach(System.out::println);
		people = People.getSomePeople().stream()
		// TODO: streams operations as requested
		.sorted(c1)
		.collect(Collectors.toList());
		
		Assertions.assertEquals(people.size(), 7);
		Assertions.assertEquals(people.get(0).name, "Cori");
		Assertions.assertEquals(people.get(6).name, "Roberto");
	}

	// TODO: replace TODO section on bellow method to calculate the avg age of the
	// people list
	@Test
	void avgAgePeopleTest() {
		int avg = 0;
		Comparator<People> c1 = Comparator.comparing((People s) -> s.age);
		avg = (int)People.getSomePeople().stream()
			  .mapToInt(People::getAge)
			  .average().getAsDouble();
		
		// TODO: streams operations as requested
		Assertions.assertEquals(avg, 26);
		;
	}

	// TODO: replace TODO section on bellow method to search for any person
	@Test
	void findOldestPersonBeetween25And40Test() {
		Comparator<People> c1 = Comparator.comparing((People s) -> s.age);
		String oldestPersonBetween25And40Name =
		People.getSomePeople().stream()
		// TODO: streams operations as requested, then uncomment bellow line
		.filter(p -> p.age >= 20 && p.age <= 40)
		.max(c1)
		.get().name;
		// .ifPresent(System.out::println)
		;
		Assertions.assertEquals(oldestPersonBetween25And40Name, "Pedro");
		
	}

	// TODO: replace TODO section on bellow method to gather all the Persons whose
	// name starts with R in a List, then print it
	@Test
	void collectPeopleTest() {
		List<People> peopleWithR = 
		People.getSomePeople().stream()
		.filter(p -> p.name.startsWith("R"))
		.collect(Collectors.toList())
		// TODO: streams operations as requested, then uncomment bellow line
		// .ifPresent(System.out::println)
		;
		Assertions.assertEquals(peopleWithR.size(), 2);
	}

	// TODO: replace TODO section on bellow method to sort all the people by salary
	@Test
	void sortPeopleBySalaryTest() {
		Comparator<People> c1 = Comparator.comparing((People s) -> s.roundedSalary);
		List<People> people = 
		People.getSomePeople().stream()
				// TODO: streams operations as requested, then uncomment bellow line
				.sorted(c1)
				.collect(Collectors.toList());
				//.forEach(System.out::println);
		Assertions.assertEquals(people.size(), 7);
		Assertions.assertEquals(people.get(0).name, "Roberto");
		Assertions.assertEquals(people.get(6).name, "Pedro");
		
	}

	// TODO: replace TODO section on bellow method to sort all the people by salary
	// and when 2 or more have the same salary sort those by name
	@Test
	void sortPeopleBySalaryAndNameTest() {
		Comparator<People> c2 = Comparator.comparing((People s) -> s.roundedSalary)
				 .thenComparing(s->s.name);
		List<People> people = 
		People.getSomePeople().stream()
				// TODO: streams operations as requested, then uncomment bellow line
				.sorted(c2)
				.collect(Collectors.toList());
				//.forEach(System.out::println);
		Assertions.assertEquals(people.size(), 7);
		Assertions.assertEquals(people.get(0).name, "Roberto");
		Assertions.assertEquals(people.get(6).name, "Pedro");
	}

	// TODO: replace TODO section on bellow method to group people in 2 groups by
	// their age, 1 older than 25, the other younger or equal than 25
	@Test
	void groupPeopleByAgeTest() {
		Map<Boolean, List<Integer>> peopleDivided = new HashMap<>();
		People.getSomePeople().stream()
		// TODO: streams operations as requested, then uncomment bellow line
		.collect(Collectors.groupingBy(p -> p.age > 25));
		;
		Assertions.assertEquals(peopleDivided.get(false).size(), 4);
		Assertions.assertEquals(peopleDivided.get(true).size(), 3);
	}
	
	// TODO: replace TODO section on bellow method to print all the people who earn less than 25k sorted by salary (use takeWhile)
	@Test
	void collectAllPeopleWhoEarnLessThan25KTest() {
		Comparator<People> c1 = Comparator.comparing((People s) -> s.roundedSalary);
		List<People> people = 
		People.getSomePeople().stream()
		// TODO: streams operations as requested, then uncomment bellow line
		.takeWhile(p -> p.roundedSalary < 25000)
		.sorted(c1)
		.collect(Collectors.toList());
		//.forEach(System.out::println);
		Assertions.assertEquals(people.size(), 4);
	}
	
	// TODO: replace TODO section on bellow method to print all the people who earn less than 25k sorted by salary(use dropWhile)
	@Test
	void collectAllPeopleWhoEarnMoreThan25KTest() {
		Comparator<People> c1 = Comparator.comparing((People s) -> s.roundedSalary);
		List<People> people = new ArrayList<>();
		People.getSomePeople().stream()
		// TODO: streams operations as requested, then uncomment bellow line
		.forEach(System.out::println);
		Assertions.assertEquals(people.size(), 3);

	}

}

class People {
	public String name;
	public int age;
	public int roundedSalary;

	public People(String name, int age, int roundedSalary) {
		super();
		this.name = name;
		this.age = age;
		this.roundedSalary = roundedSalary;
	}

	static List<People> getSomePeople() {
		List<People> peopleList = List.of(new People("Cori", 28, 40000)
				,new People("Jason", 18, 15000)
				,new People("Pedro", 40, 80000)
				,new People("Maria", 38, 80000)
				,new People("Roberto", 18, 6000)
				,new People("Raul", 23, 23000)
				,new People("Petra", 18, 12000));

		return peopleList;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "People [name=" + name + "]";
	}
}