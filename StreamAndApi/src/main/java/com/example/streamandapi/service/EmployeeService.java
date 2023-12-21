package com.example.streamandapi.service;

import com.example.streamandapi.exeptions.EmployeeAlreadyAddedException;
import com.example.streamandapi.exeptions.EmployeeNotFoundException;
import com.example.streamandapi.exeptions.EmployeeStorageIsFullException;
import com.example.streamandapi.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int MAX_COUNT = 11;

    private final List<Employee> employees = new ArrayList<>(MAX_COUNT);

    public void add(String firstname, String lastName) {
        if (employees.size() >= MAX_COUNT ) {
           throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee(firstname,lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);


    }
    public void remove(String firstname, String lastName) {
        var it = employees.iterator();
        while (it.hasNext()) {
            var employee = it.next();
            if(employee.getFirstName().equals(firstname) && employee.getLastName().equals(lastName)){
                it.remove();
            }
        }
        throw new EmployeeNotFoundException();


    }
    public Employee find(String firstname, String lastName) {
        for (Employee employee : employees) {
           if(employee.getFirstName().equals(firstname) && employee.getLastName().equals(lastName)){
               return employee;
           }
        }
        throw new EmployeeNotFoundException();
    }
    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees);
    }
}
