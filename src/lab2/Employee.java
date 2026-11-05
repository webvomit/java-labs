package lab2;

import java.util.List;

public class Employee {
    private final String name;
    private final Department department;

    public Employee(String name, Department department) {
        if (name == null || name.trim().isEmpty() || department == null)
            throw new IllegalArgumentException("укажите имя и отдел сотрудника");
        this.name = name.trim();
        this.department = department;
        department.addEmployee(this);
    }

    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public List<Employee> getColleagues() { return department.getEmployees(); }

    @Override
    public String toString() {
        if (department.getManager() == this) return name + " начальник отдела " + department.getName();
        Employee manager = department.getManager();
        return name + " работает в отделе " + department.getName() + ", начальник которого "
                + (manager == null ? "не назначен" : manager.getName());
    }
}
