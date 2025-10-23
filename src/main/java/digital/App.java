package digital;

import digital.domain.Employee;
import digital.service.impl.EmployeeServiceImpl;
import digital.service.impl.FileServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ){
        List<Employee> employees = new ArrayList<>();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        FileServiceImpl fileService = new FileServiceImpl();
        employees.add(new Employee(1, "Ваня", "Кочетов", 500));
        employees.add(new Employee(2, "Даня", "Брославский", 300));
        employees.add(new Employee(3, "Максим", "Синеполов", 1000));
        employees.add(new Employee(4, "Женя", "Микашевидзе", 750));
        employees.add(new Employee(5, "Света", "Борисенко", 680));

//        System.out.println(employeeService.getEmployeesBySalaryGreaterThan(500, employees));
//        System.out.println(employeeService.getEmployeeMap(employees));
//        System.out.println(employeeService.getEmployeesById(1, employees));
        fileService.saveEmployeesToFile(employees, "employees.txt");
    }
}
