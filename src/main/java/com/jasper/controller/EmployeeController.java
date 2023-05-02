package com.jasper.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.entity.Employee;
import com.jasper.repository.EmployeeRepository;
import com.jasper.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/getAddress")
    public List<Employee> getAddress() {
        List<Employee> address = repository.findAll();
        return address;
    }
 
         
    @GetMapping("/jasperpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
  
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
  
        service.exportJasperReport(response);
    }
}
