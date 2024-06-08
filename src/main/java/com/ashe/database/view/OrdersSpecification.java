package com.ashe.database.view;

import com.ashe.database.domain.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class OrdersSpecification {

    public static Specification<Order> hasCustomerName(String customerName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("customer").get("customerName"), customerName);
    }

    public static Specification<Order> hasCurrency(String currency) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("currency"), currency);
    }

    public static Specification<Order> hasOrderDate(LocalDate orderDate) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("orderDate"), orderDate);
    }

    // 添加其他条件方法
}
