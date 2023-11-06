package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.springboot.cruddemo.dtos.FulfillmentResponse;
import com.luv2code.springboot.cruddemo.dtos.Message;
import com.luv2code.springboot.cruddemo.dtos.Messages;
import com.luv2code.springboot.cruddemo.dtos.Text;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // expose "/isAlive" and return only simple string to make sure app is running
    @GetMapping("/isAlive")
    public String isAlive() {
        return "Server is Running";
    }


    @PostMapping("/employees")
    public FulfillmentResponse addEmployee(@RequestBody Object theEmployee) throws IOException {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        // theEmployee.setId(0);
        String povLink = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(theEmployee);
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // Extracting email ID
            String emailId = rootNode
                    .path("sessionInfo")
                    .path("parameters")
                    .path("email_id")
                    .asText();

            String lastName = rootNode
                    .path("sessionInfo")
                    .path("parameters")
                    .path("last_name")
                    .path("original")
                    .asText();

            // Extracting name
            String firstName = rootNode
                    .path("sessionInfo")
                    .path("parameters")
                    .path("first_name")
                    .path("original")
                    .asText();

            // Extracting company name
            String companyName = rootNode
                    .path("sessionInfo")
                    .path("parameters")
                    .path("organization_name")
                    .asText();

            String POVNUmber = rootNode
                    .path("sessionInfo")
                    .path("parameters")
                    .path("pov_number")
                    .asText();



            System.out.println("Email ID: " + emailId);
            System.out.println("Name: " + firstName +" "+lastName);
            System.out.println("Company Name: " + companyName);

            Properties newProperty= new Properties();

            InputStream inputStream = new FileInputStream("src/main/java/com/luv2code/springboot/cruddemo/pov's.properties");
            newProperty.load(inputStream);
            povLink = newProperty.getProperty(POVNUmber);
            Employee employee = new Employee();
            employee.setEmail(emailId);
            employee.setFirstName(firstName);
            employee.setOrganizationName(companyName);
            employee.setLastName(lastName);
            employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FulfillmentResponse response = new FulfillmentResponse();
        Messages messages = new Messages();
        Message message = new Message();
        Text text = new Text();
        List<String> text_List = new ArrayList<>();
        text_List.add(povLink);
        text.setText(text_List);
        message.setText(text);
        messages.setMessages(Collections.singletonList(message));
        response.setFulfillment_response(messages);

        // Employee dbEmployee = employeeService.save(theEmployee);
        // System.out.println(theEmployee.sessionInfo.session.);

        return response;
    }

}














