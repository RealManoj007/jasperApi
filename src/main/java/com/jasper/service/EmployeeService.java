package com.jasper.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.jasper.entity.Employee;
import com.jasper.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeService {

	    @Autowired
	    private EmployeeRepository repository;
	  
	  
	    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
	        List<Employee> address = repository.findAll();
	        //Get file and compile it
	        File file = ResourceUtils.getFile("classpath:Employee.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);
	        System.err.println(dataSource);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Manoj Kumar Dogra");
	        //Fill Jasper report
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        //Export report
	        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
	    }
}
