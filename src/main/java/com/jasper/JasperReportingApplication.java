package com.jasper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jasper.entity.Employee;
import com.jasper.repository.EmployeeRepository;

@SpringBootApplication
public class JasperReportingApplication implements CommandLineRunner{

	private static Logger logger=LoggerFactory.getLogger(JasperReportingApplication.class);
	
	@Autowired
	private EmployeeRepository repository;
	
    public static List<Employee> employees=new ArrayList<>();
	
	public static void main(String[] args) {
		logger.info("\nStarting : Spring Boot Application Started");
		SpringApplication.run(JasperReportingApplication.class, args);
		logger.info("\nStoping : Spring Boot Application Stoped");
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("\nExecuting Command Line Runner");
		employees.add(new Employee("Manoj","Hno 625 Chandigarh",7894561231l));
		employees.add(new Employee("Sanjay","Hno 1325 Hydrabad",8794561231l));
		employees.add(new Employee("Sajid","Hno 525 Zimbabe",9456123178l));
		employees.add(new Employee("AMir","Hno 787 Hari hari",4561231789l));
		employees.add(new Employee("1Manoj","1Hno 625 Chandigarh",5612317894l));
		employees.add(new Employee("2Sanjay","2Hno 1325 Hydrabad",6123178945l));
		employees.add(new Employee("3Sajid","3Hno 525 Zimbabe",1231789456l));
		employees.add(new Employee( "4AMir","4Hno 787 Hari hari",2317894561l));
		repository.saveAll(employees);
		logger.info("\nStopping Command Line Runner");	
	}
}
