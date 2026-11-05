package lab2;

public class FullName {
    private final String surname;
    private final String name;
    private final String patronymic;

    public FullName(String surname, String name, String patronymic) {
        this.surname = surname.trim();
        this.name = name.trim();
        this.patronymic = patronymic.trim();
        if (this.surname.isEmpty() && this.name.isEmpty() && this.patronymic.isEmpty()) {
            throw new IllegalArgumentException("укажите хотя бы одну часть ФИО");
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String part : new String[]{surname, name, patronymic}) {
            if (!part.isEmpty()) {
                if (result.length() > 0) result.append(' ');
                result.append(part);
            }
        }
        return result.toString();
    }
}
