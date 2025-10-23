package digital;

import digital.domain.Employee;
import digital.exception.EmployeeNotFoundException;
import digital.service.EmployeeService;
import digital.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }
    
    @Test
    @DisplayName("Должен вернуть сотрудника по id")
    void shouldReturnEmployeeById() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Ваня", "Кочетов", 500));
        employeeList.add(new Employee(2, "Даня", "Брославский", 300));
        employeeList.add(new Employee(3, "Максим", "Синеполов", 1000));

        Employee employee = employeeService.getEmployeesById(1, employeeList);

        assertNotNull(employee);
        assertEquals(1, employee.getId());
        assertEquals("Ваня", employee.getFirstName());
    }

    @Test
    @DisplayName("Должен выбросить исключение, если сотрудник не найдет по id")
    void throwExceptionWhenEmployeeNotFound() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Ваня", "Кочетов", 500));
        employeeList.add(new Employee(2, "Даня", "Брославский", 300));
        employeeList.add(new Employee(3, "Максим", "Синеполов", 1000));

        EmployeeNotFoundException exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeesById(999, employeeList)
        );

        assertEquals("Employee with id 999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Должен вернуть сотрудника по id")
    void shouldReturnEmployeesWithSalaryGreaterOrEqual() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Ваня", "Кочетов", 500));
        employeeList.add(new Employee(2, "Даня", "Брославский", 300));
        employeeList.add(new Employee(3, "Максим", "Синеполов", 1000));

        List<Employee> employeesListBySalary = employeeService.getEmployeesBySalaryGreaterThan(500, employeeList);

        assertNotNull(employeesListBySalary);
        assertEquals(2, employeesListBySalary.size());
        assertTrue(employeesListBySalary.contains(employeeList.get(0)));
        assertFalse(employeesListBySalary.contains(employeeList.get(1)));
        assertTrue(employeesListBySalary.contains(employeeList.get(2)));
    }
}
