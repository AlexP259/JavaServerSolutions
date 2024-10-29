package org.spring.mvc.employee.service;

import jakarta.transaction.Transactional;
import org.spring.mvc.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(long employeeId){
        Employee empDB = employeeRepository.findById(employeeId).get();
        return empDB;
    }

//    в чистом виде в CrudRepository метода update не существует. Поэтому мы сначала найдем по Id, а потом сохраним объект, но уже с новыми значениями полей
    public Employee update(Employee employee, Long employeeId){
        Employee empDB = employeeRepository.findById(employeeId).get();
        empDB.setName(employee.getName());
        empDB.setEmail(employee.getEmail());
        empDB.setAddress(employee.getAddress());
        return employeeRepository.save(empDB);
    }

    public void deleteEmployeeById(long employeeId){
        employeeRepository.deleteById(employeeId);
    }


}










