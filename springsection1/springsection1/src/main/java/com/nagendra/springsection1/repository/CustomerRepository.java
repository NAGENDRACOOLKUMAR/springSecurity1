package com.nagendra.springsection1.repository;

import com.nagendra.springsection1.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {


    Optional<Customer>findByEmail(String email);

}
