package digital.service;

import digital.domain.Employee;

import java.util.List;

public interface FileService {
    void saveEmployeesToFile(List<Employee> employees, String filename);
    List<Employee> loadEmployeesFromFile(String filename);
}
