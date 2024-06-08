package com.ashe.database.service;

import com.ashe.database.dao.CustomerRepository;
import com.ashe.database.domain.entity.Customer;
import com.ashe.database.domain.SnowflakeIdGenerator;
import com.ashe.database.view.request.CreateCustomerReq;
import com.ashe.database.view.CustomersSpecification;
import com.ashe.database.view.RestResult;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public CustomerService(CustomerRepository customerRepository, SnowflakeIdGenerator snowflakeIdGenerator) {
        this.customerRepository = customerRepository;
        this.snowflakeIdGenerator = snowflakeIdGenerator;
    }

    @Transactional
    public RestResult create(CreateCustomerReq request) {
        try {
            Specification<Customer> spec = CustomersSpecification.hasCustomerName(request.getCustomerName());
            Assert.isTrue(!customerRepository.exists(spec), "客户名重复");

            Customer customer = new Customer();
            customer.setCustomerId(snowflakeIdGenerator.nextId());
            customer.setCustomerName(request.getCustomerName());
            customer.setAddress(request.getAddress());
            customer.setCreatedAt(LocalDateTime.now());
            customer.setUpdatedAt(LocalDateTime.now());

            customerRepository.save(customer);
            return RestResult.ok();
        } catch (Exception e) {
            return RestResult.err(e.getMessage());
        }
    }
}
