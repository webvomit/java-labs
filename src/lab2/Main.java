package lab2;

import java.util.Scanner;

public class Main {
    private final Scanner scanner;

    public Main(Scanner scanner) { this.scanner = scanner; }

    @Override
    public String toString() { return "лабораторная работа №2, вариант №7"; }

    private String text(String prompt, boolean required) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) throw new IllegalStateException("ввод завершён");
            String value = scanner.nextLine().trim();
            if (!required || !value.isEmpty()) return value;
            System.out.println("значение не должно быть пустым. попробуйте ввести другое.");
        }
    }

    private int number(String prompt, int min, int max) {
        while (true) {
            String value = text(prompt, true);
            try {
                int n = Integer.parseInt(value);
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) { }
            System.out.println("введите целое число от " + min + " до " + max + ".");
        }
    }

    private void examples() {
        System.out.println("\nпримеры из заданий:");
        System.out.println(new FullName("", "Клеопатра", ""));
        System.out.println(new FullName("Пушкин", "Александр", "Сергеевич"));
        System.out.println(new FullName("Маяковский", "Владимир", ""));
        for (int s : new int[]{10, 10000, 100000}) System.out.println(s + " секунд: " + new Time(s));
        System.out.println("2 часа, 3 минуты, 5 секунд: " + new Time(2, 3, 5));
        Department it = new Department("IT");
        Employee petrov = new Employee("Петров", it);
        Employee kozlov = new Employee("Козлов", it);
        Employee sidorov = new Employee("Сидоров", it);
        it.setManager(kozlov);
        System.out.println(it);
        System.out.println(petrov);
        System.out.println(kozlov);
        System.out.println(sidorov);
        System.out.println("сотрудники отдела через Петрова:");
        for (Employee e : petrov.getColleagues()) System.out.println("  " + e.getName());
        System.out.println("34056 секунд — час: " + new Time(34056).getHours());
        System.out.println("4532 секунд — минута: " + new Time(4532).getMinutes());
        System.out.println("123 секунды — секунда: " + new Time(123).getSeconds());
    }

    private void customName() {
        System.out.println("введите части ФИО. неизвестные части можно оставить пустыми.");
        while (true) {
            String surname = text("фамилия: ", false);
            String name = text("имя: ", false);
            String patronymic = text("отчество: ", false);
            try {
                System.out.println("результат: " + new FullName(surname, name, patronymic));
                return;
            } catch (IllegalArgumentException e) { System.out.println(e.getMessage() + ". повторите ввод."); }
        }
    }

    private void customTime() {
        int mode = number("1 — секунды с начала суток, 2 — часы, минуты, секунды: ", 1, 2);
        Time time;
        if (mode == 1) time = new Time(number("секунды: ", 0, Integer.MAX_VALUE));
        else time = new Time(number("часы: ", 0, Integer.MAX_VALUE),
                number("минуты: ", 0, 59), number("секунды: ", 0, 59));
        System.out.println("время: " + time + "; час: " + time.getHours()
                + ", минута: " + time.getMinutes() + ", секунда: " + time.getSeconds());
    }

    private void customDepartment() {
        Department department = new Department(text("название отдела: ", true));
        int count = number("количество сотрудников (1..100): ", 1, 100);
        Employee[] people = new Employee[count];
        for (int i = 0; i < count; i++) people[i] = new Employee(text("имя сотрудника " + (i + 1) + ": ", true), department);
        int boss = number("номер начальника (1.." + count + "): ", 1, count);
        department.setManager(people[boss - 1]);
        System.out.println(department);
        for (Employee e : people) System.out.println(e);
        int selected = number("номер сотрудника для просмотра отдела (1.." + count + "): ", 1, count);
        System.out.println("сотрудники отдела:");
        for (Employee e : people[selected - 1].getColleagues()) System.out.println("  " + e.getName());
    }

    private void run() {
        System.out.println(this);
        while (true) {
            System.out.println("\n1 — примеры из заданий; 2 — имя; 3 — время; 4 — сотрудники и отдел; 0 — выход");
            int choice = number("ваш выбор: ", 0, 4);
            if (choice == 0) { System.out.println("программа завершена."); return; }
            switch (choice) {
                case 1: examples(); break;
                case 2: customName(); break;
                case 3: customTime(); break;
                case 4: customDepartment(); break;
                default: throw new AssertionError();
            }
        }
    }

    public static void main(String[] args) {
        try { new Main(new Scanner(System.in)).run(); }
        catch (IllegalStateException e) { System.out.println("\nввод завершён. программа остановлена."); }
    }
}
