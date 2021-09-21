package com.example.Managment.Managment.service;

import com.example.Managment.Managment.model.Employee;
import com.example.Managment.Managment.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void saveNewEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(long id){
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException("Employee not found for id");
        }
        return employee;
    }

    public void deleteEmployeeById(long id){
        employeeRepository.deleteById(id);
    }

    public Page<Employee> findPaginated(int pageNo , int pageSize , String sortField,String sortDirection){

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        return employeeRepository.findAll(pageable);
    }



}
