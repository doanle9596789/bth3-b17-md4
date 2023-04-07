package com.example.bth3customer.controller;

import com.example.bth3customer.model.Customer;
import com.example.bth3customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/create-customer")
   public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>>findAll(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Customer>editCustomer(@PathVariable(value = "id") Long id,@RequestBody Customer customer){
        Optional<Customer>optionalCustomer=customerService.findById(id);
        if (!optionalCustomer.isPresent()){
            return ResponseEntity.notFound().build();
        }else {
           customer.setId(optionalCustomer.get().getId());
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
        }
    }@DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer>deleteCustomer(@PathVariable(value = "id")Long id){
        Optional<Customer>optionalCustomer=customerService.findById(id);
        if (optionalCustomer.isPresent()){
            customerService.remove(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }@GetMapping("/find/{id}")
    public ResponseEntity<Customer>findById(@PathVariable(value = "id")Long id){
        Optional<Customer>optionalCustomer=customerService.findById(id);
        if (optionalCustomer.isPresent()){
            return ResponseEntity.ok().body(optionalCustomer.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}