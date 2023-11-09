/*
Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавление нового сотрудника в справочник сотрудников
 */

package Seminars.Seminar4;

import java.util.*;
import java.util.function.Consumer;

public class Guide {
    public static void main(String[] args) {
        List<Employee> guide = new ArrayList<>(List.of(
                new Employee(12, "8-924-123-56-98", "Ignat", 5),
                new Employee(34, "8-925-877-46-28", "Uliana", 2),
                new Employee(45, "8-916-223-66-88", "Alex", 10),
                new Employee(234, "8-926-568-09-98", "Ulia", 1),
                new Employee(1, "8-929-908-56-76", "Slava", 26),
                new Employee(49, "8-921-897-43-91", "Ignat", 6)));

        System.out.println("Сотрудник со стажем 5 лет: " + findByExperience(guide, 5));


        Employee emp = findByExperience(guide, 8);
        if (emp == null) {
            System.out.println("Нет такого сотрудника! ");
        } else {
            System.out.printf("Сотрудник со стажем %d лет: %s", emp.getExperience(), emp);
        }
        System.out.println("Сотрудник со стажем 8 лет: " + findByExperience(guide, 8));
        System.out.println("Сотрудник со стажем 26 лет: " + findByExperience(guide, 26));

        add(guide, "8-901-433-45-21", "Васька", 16);
        add(guide, "8-916-455-89-75", "Иришка", 7);
        add(guide, "8-926-111-11-11", "Алёнка", 1);
        guide.forEach(System.out::println);

        var phoneNumbers = findPhoneNumber(guide, "Ignat");
        System.out.println(Arrays.toString(phoneNumbers));

        Employee e = findByNumber(guide, 2);
        if (e == null) {
            System.out.println("Нет такого сотрудника! ");
        } else {
            System.out.printf("Сотрудник с табельным номером %d: %s\n", e.getNumber(), e);
        }
        System.out.println("Сотрудник с табельным номером 234: " + findByNumber(guide, 234));
        System.out.println("Сотрудник с табельным номером 2: " + findByNumber(guide, 2));


    }

    static Employee findByExperience(List<Employee> list, int experience) {
        var a = list.stream().filter(p -> p.getExperience() == experience).findAny();
        if (a.isPresent()) {
            return a.get();
        }
        return null;
    }

    static String[] findPhoneNumber(List<Employee> list, String name) {
        return list.stream().filter(p -> p.getName().equalsIgnoreCase(name))
                .map(p -> p.getPhoneNumber()).toArray(String[]::new);
    }

    static Employee findByNumber(List<Employee> list, int number) {
        var a = list.stream().filter(p -> p.getNumber() == number).findAny();
        if (a.isPresent()) {
            return a.get();
        }
        return null;
    }

    static void add(List<Employee> list, String phoneNumber, String name, int experience) {
        int lastId = list.stream().mapToInt(Employee::getNumber).max().getAsInt();
        list.add(new Employee(lastId + 1, phoneNumber, name, experience));
    }


}

class Employee {
    int number;
    String phoneNumber;
    String name;
    int experience;

    public Employee(int number, String phoneNumber, String name, int experience) {
        this.number = number;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return String.format("Табельный номер: %d, номер телефона: %s," +
                " Имя сотрудника: %s, стаж работы: %d", number, phoneNumber, name, experience);
    }
}

