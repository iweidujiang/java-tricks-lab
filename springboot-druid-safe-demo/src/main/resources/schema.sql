-- schema.sql
CREATE DATABASE IF NOT EXISTS test_druid;
USE test_druid;

CREATE TABLE t_user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(100),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);