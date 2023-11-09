/*
Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
Найдите человека с самым маленьким номером телефона
Найдите номер телефона человека чье имя самое большое в алфавитном порядке
 */

package Seminars.Seminar4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Phones {
    public static void main(String[] args) {
        Map<String, String> phones = new HashMap<>(
                Map.of("8-916-234-42-32", "Mary",
                        "8-923-123-21-23", "Ivan",
                        "8-903-345-53-53", "Iliya",
                        "8-929-456-43-11", "Inna",
                        "8-926-231-58-09", "Yana",
                        "8-999-232-12-45","Foma",
                        "9-954-312-45-89", "Vera",
                        "8-925-213-45-78", "Innokentiy")
        );

        String minPhone = phones.entrySet().stream().min(Comparator.comparing(e->e.getKey())).get().toString();
        System.out.println(minPhone);

        String maxName = phones.entrySet().stream().max(Comparator.comparing(e->e.getValue().length())).get().toString();
        System.out.println(maxName);
    }
}
