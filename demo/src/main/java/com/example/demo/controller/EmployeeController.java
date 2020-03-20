package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@GetMapping("/employees")
	public List<Employee> getAllEmp(){
		return employeeRepository.findAll();
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable(value="id") Long empId) throws ResourceNotFoundException
	{
		Employee employee=employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("employee not found for this id::"+empId));
		return ResponseEntity.ok().body(employee);
	}
}
