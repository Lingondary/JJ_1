package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Homework {
    public void printNamesOrdered(List<Streams.Person> persons) {
        persons.stream()
                .map(Streams.Person::getName)
                .sorted()
                .forEach(System.out::println);
    }

    public Map<Streams.Department, Streams.Person> printDepartmentOldestPerson(List<Streams.Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(Streams.Person::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Streams.Person::getAge)),
                                Optional::get
                        )));
    }

    public List<Streams.Person> findFirstPersons(List<Streams.Person> persons) {
        return persons.stream()
                .filter(person -> person.getAge() < 30 && person.getSalary() > 50_000)
                .limit(10)
                .collect(Collectors.toList());
    }

    public Optional<Streams.Department> findTopDepartment(List<Streams.Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(Streams.Person::getDepartment,
                        Collectors.summingInt(Streams.Person::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
