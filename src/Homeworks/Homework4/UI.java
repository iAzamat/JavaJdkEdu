package Homeworks.Homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static Homeworks.Homework4.Directory.*;

public class UI {

    /**
     * Добавить метод, который ищет сотрудника по стажу (может быть список)
     *
     * @param list
     * @param experience
     */
    public static void findByExperienceUI(List<Person> list, int experience) {
        List<Person> pp = findByExperience(list, experience);
        if (pp.size() != 0) {
            System.out.println("Сотрудник со стажем " + experience + ": ");
            pp.forEach(System.out::println);
        } else {
            System.out.println("нет такого сотрудника");
        }
    }

    /**
     * Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
     *
     * @param list
     * @param name
     */
    public static void findPhonesByNameUI(List<Person> list, String name) {
        List<String> phonesByName = findPhonesByName(list, name);
        if (phonesByName.size() != 0) {
            System.out.println("Номера телефонов сотрудника по имени " + name + ":");
            phonesByName.forEach(System.out::println);
        } else {
            System.out.println("нет сотрудника с таким именем");
        }
    }

    public static void addUI(ArrayList<Person> list, String phone, String name, int experience) {
        add(list, phone, name, experience);
        System.out.println("Сотрудник: " + name + " успешно добавлен");

    }

    public static void printDirectoryUI(List<Person> list) {
        System.out.println("--------------- Список сотрудников ---------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------------------------");
    }
}
