package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Department {
    private final String name;
    private Employee manager;
    private final List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("название отдела пустое");
        this.name = name.trim();
    }

    void addEmployee(Employee employee) { employees.add(employee); }
    public String getName() { return name; }
    public Employee getManager() { return manager; }
    public List<Employee> getEmployees() { return Collections.unmodifiableList(employees); }

    public void setManager(Employee manager) {
        if (manager == null || manager.getDepartment() != this)
            throw new IllegalArgumentException("начальник должен работать в этом отделе");
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "отдел " + name + ", начальник: " + (manager == null ? "не назначен" : manager.getName());
    }
}
