package Seminars.Seminar4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> maleNames = new ArrayList<>();
        maleNames.add("Ivan");
        maleNames.add("Egor");
        maleNames.add("Dmitri");
        maleNames.add("Aleksei");
        maleNames.add("Ivan");
        maleNames.add("Egor");

        System.out.println("Мужские имена:");
        for (String name: maleNames) {
            System.out.print(name + " ");
        }
        System.out.println(" ");

        List<String> femaleNames = new ArrayList<>();
        femaleNames.add("Dasha");
        femaleNames.add("Glasha");
        femaleNames.add("Vera");
        femaleNames.add("Anna");
        femaleNames.add("Dasha");
        femaleNames.add("Anna");
        femaleNames.add("Ingrid");

        System.out.println("Женские имена:");
        for (String name: femaleNames) {
            System.out.print(name + " ");
        }
        System.out.println(" ");

        Collections.sort(maleNames);

        System.out.println("Мужские имена после сортировки:");
        for (String name: maleNames) {
            System.out.println(name);
        }

        Collections.sort(femaleNames);
        System.out.println("Женские имена после сортировки:");
        for (String name: femaleNames) {
            System.out.println(name);
        }

        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        Collections.sort(maleNames, lengthComparator);
        System.out.println("Мужские имена после сортировки:");
        for (String name: maleNames) {
            System.out.println(name);
        }
        Collections.sort(femaleNames, lengthComparator);
        System.out.println("Женские имена после сортировки:");
        for (String name: femaleNames) {
            System.out.println(name);
        }

        Collections.reverse(maleNames);
        System.out.println("Мужские имена после переворота:");
        for (String name: maleNames) {
            System.out.println(name);
        }

        for (int i = 0; i < maleNames.get(i).length(); i++) {
            String name = maleNames.get(i).toString();
            maleNames.set(i, name);
        }

        Set<String> newNames = new HashSet<>(maleNames);
        System.out.println(newNames);

        maleNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(maleNames.stream().findFirst().get());
        System.out.println(femaleNames.stream().min(Comparator.naturalOrder()).get());

        maleNames.removeIf(element -> element.contains("A"));
        maleNames.removeIf(element -> element.contains("a"));
        System.out.println(maleNames);

        femaleNames.removeIf(element -> element.toLowerCase().contains("a"));
        System.out.println(femaleNames);


    }
}
