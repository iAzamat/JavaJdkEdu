package Homeworks.Homework4;

// Добавить метод, который ищет сотрудника по табельному номеру
// Добавить метод добавление нового сотрудника в справочник сотрудников

import java.util.ArrayList;
import java.util.List;

import static Homeworks.Homework4.Directory.*;
import static Homeworks.Homework4.UI.*;

/**
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 */
public class Main {
  public static void main(String[] args) {
    var persons = new ArrayList<>(
        List.of(
            new Person(0, "46456456", "Алекс", 10),
            new Person(1, "9575789", "Алекс", 10),
            new Person(2, "575756", "Василий", 8),
            new Person(3, "475675647", "Михаил", 9),
            new Person(4, "56457567657", "Анжелика", 11),
            new Person(5, "45757567", "Александра", 15)));

    findByExperienceUI(persons, 10);
    findByExperienceUI(persons, 20);

    addUI(persons, "1456456", "Вася", 5);
    addUI(persons, "45756", "Маша", 10);
    addUI(persons, "56767657", "Коля", 20);

    printDirectoryUI(persons);

    findPhonesByNameUI(persons, "Алекс");
    findPhonesByNameUI(persons, "Алеj");

    var idsByName = findSomething(persons, p -> p.getName().equals("Анжелика"), Person::getPersonId);
    System.out.println("ID'шники Анжелики: " + idsByName);
  }
}