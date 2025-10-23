package digital.service.impl;

import digital.domain.Employee;
import digital.exception.FileLoadException;
import digital.service.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    public void saveEmployeesToFile(List<Employee> employees, String filename) {
        try(FileWriter fileWriter = new FileWriter(filename)) {
            for (Employee employee : employees) {
                String employeeString = String.format("%d,%s,%s,%d", employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary());
                fileWriter.write(employeeString);
                fileWriter.write("\n");
            }
            System.out.println("Employees saved to " + filename);
        }catch (IOException e){
            System.err.println("Errors: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> loadEmployeesFromFile(String filename) {
        File file = new File(filename);
        List<Employee> employees = new ArrayList<>();
        if(!file.exists()){
            throw new FileLoadException("File not found");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filename));){
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                try{
                    Employee employee = employeeFromFile(line);
                    employees.add(employee);
                }catch (IllegalArgumentException e){
                    System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage() + " | Содержимое: " + line);
                }
            }
        }catch(IOException e){
            System.err.println("Errors: " + e.getMessage());
        }
        System.out.println("Employees loaded from " + filename);
        return employees;
    }

    private Employee employeeFromFile(String line) {
        String[] fields = line.split(",", -1);
        int id;
        int salary;
        String firstName = fields[1];
        String lastName = fields[2];
        try {
            id = Integer.parseInt(fields[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("id не является целым числом: '" + fields[0] + "'");
        }
        try{
            salary = Integer.parseInt(fields[3]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("salary не является целым числом: '" + fields[3] + "'");
        }
        return Employee.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .salary(salary)
                .build();
    }
}
