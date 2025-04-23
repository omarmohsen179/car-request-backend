-- Create customer_requests table
CREATE TABLE customer_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    checked_by_company VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create supplier_offers table
CREATE TABLE supplier_offers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    car_condition_details TEXT,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (request_id) REFERENCES customer_requests(id)
);

-- Create inspection_companies table
CREATE TABLE inspection_companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    contact_info VARCHAR(255),
    capabilities TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert default inspection companies
INSERT INTO inspection_companies (company_name, contact_info, capabilities) VALUES
('AUTO_CHECK_CO', 'contact@autocheck.com', 'Full vehicle inspection services'),
('VEHI_VERIFY_INC', 'info@vehiverify.com', 'Comprehensive vehicle verification'); 