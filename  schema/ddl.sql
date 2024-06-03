CREATE TABLE Orders (
                        order_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单唯一标识符，主键',
                        customer_id BIGINT COMMENT '客户唯一标识符',
                        order_date DATE COMMENT '订单日期',
                        total_amount INT COMMENT '订单总金额，单位分',
                        currency VARCHAR(10) COMMENT '订单金额使用的货币',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间'
) COMMENT '订单表，用于存储订单信息';

CREATE TABLE Customers (
                           customer_id BIGINT PRIMARY KEY COMMENT '客户唯一标识符，主键',
                           customer_name VARCHAR(10) COMMENT '客户名',
                           address VARCHAR(200) COMMENT '收货地址',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '客户创建时间',
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '客户更新时间'
)COMMENT'客户表，用于存储客户信息';