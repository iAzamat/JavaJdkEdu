package Homeworks.Homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Directory {
    public static List<Person> findByExperience(List<Person> list, int experience) {
        return list.stream()
                .filter(p -> p.getExperience() == experience)
                .toList();
    }

    public static List<String> findPhonesByName(List<Person> list, String name) {
        return list.stream().filter(p -> p.getName().equalsIgnoreCase(name))
                .map(Person::getPhone).toList();
    }

    public static <R> List<R> findSomething(List<Person> list, Predicate<Person> filter, Function<Person, R> mapper) {
        return list.stream().filter(filter).map(mapper).toList();
    }

    public static void add(ArrayList<Person> list, String phone, String name, int experience) {
        int lastId = list.stream().mapToInt(Person::getPersonId).max().orElse(-1);
        list.add(new Person(lastId + 1, phone, name, experience));
    }
}
