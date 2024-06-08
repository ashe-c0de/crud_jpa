package com.ashe.database.view;

import com.ashe.database.domain.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomersSpecification {

    public static Specification<Customer> hasCustomerName(String customerName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("customerName"), customerName);
    }

}
