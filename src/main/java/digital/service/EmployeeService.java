package digital.service;

import digital.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee getEmployeesById(int id, List<Employee> employees);
    List<Employee> getEmployeesBySalaryGreaterThan(int targetSalary, List<Employee> employees);
    Map<String, Employee> getEmployeeMap(List<Employee> employees);
}
