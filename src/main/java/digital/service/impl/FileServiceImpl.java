package digital.service.impl;

import digital.domain.Employee;
import digital.service.FileService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    public void saveEmployeesToFile(List<Employee> employees, String filename) {
        try(FileWriter fileWriter = new FileWriter(filename)) {
            for (Employee employee : employees) {
                fileWriter.write(employee.toString());
                fileWriter.write("\n");
            }
            System.out.println("Employees saved to " + filename);
        }catch (IOException e){
            System.err.println("Errors: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> loadEmployeesFromFile(String filename) {
        return null;
    }
}
