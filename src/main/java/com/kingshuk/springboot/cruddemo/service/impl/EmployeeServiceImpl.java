package com.kingshuk.springboot.cruddemo.service.impl;

import com.kingshuk.springboot.cruddemo.entity.Employee;
import com.kingshuk.springboot.cruddemo.exception.EmployeeNotFoundException;
import com.kingshuk.springboot.cruddemo.repo.EmployeeRepository;
import com.kingshuk.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        if(!result.isPresent()){
            throw new EmployeeNotFoundException(id);
        }
        return result.get();
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(0);
        Employee dbEmployee = employeeRepository.save(employee);
        return dbEmployee;
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee deleteById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if(!result.isPresent()){
            throw new EmployeeNotFoundException(id);
        }else{
            employee =  result.get();
            employeeRepository.deleteById(id);
            return employee;
        }
    }
}
