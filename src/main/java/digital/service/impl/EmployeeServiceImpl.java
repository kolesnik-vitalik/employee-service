package digital.service.impl;

import digital.domain.Employee;
import digital.exception.EmployeeNotFoundException;
import digital.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    public Employee getEmployeesById(int id, List<Employee> employees) {
        for (Employee employee : employees) {
            if(id == employee.getId()) return employee;
        }
        throw new EmployeeNotFoundException("Employee with id " + id + " not found");
    }

    public List<Employee> getEmployeesBySalaryGreaterThan(int targetSalary, List<Employee> employees){
        List<Employee> employeesBySalary = new ArrayList<>();

        for(Employee employee : employees){
            if(employee.getSalary() >= targetSalary){
                employeesBySalary.add(employee);
            }
        }
        return employeesBySalary;
    }

    public Map<String, Employee> getEmployeeMap(List<Employee> employees){
        return employees.stream()
                .collect(Collectors.toMap(
                        employeeKey -> "id" + employeeKey.getId(),
                        employeeValue -> employeeValue
                ));
    }
}
