package com.ashe.database.service;

import com.ashe.database.dao.CustomerRepository;
import com.ashe.database.dao.OrderRepository;
import com.ashe.database.domain.Customer;
import com.ashe.database.domain.Order;
import com.ashe.database.domain.SnowflakeIdGenerator;
import com.ashe.database.view.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, SnowflakeIdGenerator snowflakeIdGenerator) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.snowflakeIdGenerator = snowflakeIdGenerator;
    }

    @Transactional
    public RestResult create(OrderReq dto) {
        try {
            Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new RuntimeException("不存在的客户"));
            Order order = new Order();
            order.setOrderId(snowflakeIdGenerator.nextId());
            order.setCustomer(customer);
            order.setOrderDate(LocalDate.now());
            order.setTotalAmount(dto.getTotalAmount());
            order.setCurrency(dto.getCurrency());
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
        } catch (Exception e) {
            return RestResult.err(e.getMessage());
        }
        return RestResult.ok();
    }

    public RestResult pages(OrderReq dto) {
        try {
            Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize());
            Specification<Order> spec = Specification.where(null);
            if (dto.getCustomerId() != null) {
                spec = spec.and(OrdersSpecification.hasCustomerId(dto.getCustomerId()));
            }
            if (dto.getCurrency() != null) {
                spec = spec.and(OrdersSpecification.hasCurrency(dto.getCurrency()));
            }
            if (dto.getOrderDate() != null) {
                spec = spec.and(OrdersSpecification.hasOrderDate(dto.getOrderDate()));
            }
            Page<Order> pages = orderRepository.findAll(spec, pageable);
            PagedModel<OrderRsp> pagedModel = new PagedModel<>(pages.map(OrderMapper::OrderRsp));
            return RestResult.ok(pagedModel);
        } catch (Exception e) {
            return RestResult.err(e.getMessage());
        }
    }
}
