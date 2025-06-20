USE hotpot;

CREATE TABLE customer (
    CUS_ID int NOT NULL AUTO_INCREMENT,
    CUS_NAME varchar(50) NOT NULL,
    CUS_PHN_NO varchar(50) NOT NULL,
    CUS_USERNAME varchar(50) NOT NULL,
    CUS_PASSWORD varchar(50) NOT NULL,
    CUS_EMAIL varchar(50) NOT NULL,
    PRIMARY KEY (CUS_ID),
    UNIQUE KEY CUS_PHN_NO (CUS_PHN_NO),
    UNIQUE KEY CUS_USERNAME (CUS_USERNAME)
) ENGINE=InnoDB AUTO_INCREMENT=9122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO customer (CUS_ID, CUS_NAME, CUS_PHN_NO, CUS_USERNAME, CUS_PASSWORD, CUS_EMAIL) VALUES 
(1, 'Arun Kumar', '9876543210', 'arunk', 'pass@123', 'arun.kumar@example.com'),
(2, 'Priya Sharma', '9123456789', 'priyash', 'hello@456', 'priya.sharma@example.com'),
(3, 'Karthik M', '9988776655', 'karthikm', 'welcome@789', 'karthik.m@example.com'),
(4, 'Divya R', '9898989898', 'divyar', 'divya@123', 'divya.r@example.com'),
(5, 'Vikram S', '9345678901', 'vikrams', 'vikram@pass', 'vikram.s@example.com'),
(6, 'Meena L', '9567890123', 'meenal', 'meena@456', 'meena.l@example.com'),
(7, 'Rahul Dev', '9789012345', 'rahuldev', 'rahul@2025', 'rahul.dev@example.com'),
(8, 'Sneha Rao', '9090909090', 'snehar', 'sneha@321', 'sneha.rao@example.com'),
(9, 'Anand Raj', '9234567890', 'anandr', 'anand@secure', 'anand.raj@example.com');

DROP TABLE IF EXISTS menu;

CREATE TABLE menu (
    MEN_ID int NOT NULL AUTO_INCREMENT,
    MEN_ITEM varchar(50) DEFAULT NULL,
    MEN_PRICE decimal(9,2) DEFAULT NULL,
    MEN_CALORIES double NOT NULL,
    MEN_SPECIALITY varchar(50) NOT NULL,
    PRIMARY KEY (MEN_ID)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO menu (MEN_ID, MEN_ITEM, MEN_PRICE, MEN_CALORIES, MEN_SPECIALITY) VALUES
(1, 'Paneer Tikka', 320.00, 450, 'VEG'),
(2, 'Mutton Rogan Josh', 560.00, 720, 'NON-VEG'),
(3, 'Veg Hakka Noodles', 250.00, 400, 'VEG'),
(4, 'Butter Chicken', 480.00, 800, 'NON-VEG'),
(5, 'Palak Paneer', 300.00, 350, 'VEG'),
(6, 'Grilled Salmon', 750.00, 680, 'NON-VEG'),
(7, 'Tandoori Roti', 40.00, 150, 'VEG'),
(8, 'Chicken Tikka Masala', 520.00, 770, 'NON-VEG'),
(9, 'Veg Thali', 280.00, 600, 'VEG'),
(10, 'Hyderabadi Mutton Biryani', 650.00, 890, 'NON-VEG');

CREATE TABLE wallet (
    CUS_ID int unsigned DEFAULT NULL,
    WAL_ID int unsigned NOT NULL AUTO_INCREMENT,
    WAL_AMOUNT double unsigned NOT NULL,
    WAL_SOURCE enum('PAYTM','CREDIT_CARD','DEBIT_CARD') DEFAULT 'DEBIT_CARD',
    PRIMARY KEY (WAL_ID),
    UNIQUE KEY CUS_ID (CUS_ID, WAL_SOURCE)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO wallet (CUS_ID, WAL_ID, WAL_AMOUNT, WAL_SOURCE) VALUES
(1, 1, 1500.50, 'PAYTM'),
(1, 2, 2200.00, 'DEBIT_CARD'),
(1, 3, 3100.75, 'CREDIT_CARD'),
(2, 4, 1200.00, 'DEBIT_CARD'),
(2, 5, 3000.00, 'PAYTM'),
(2, 6, 4500.25, 'CREDIT_CARD'),
(3, 7, 650.00, 'PAYTM'),
(3, 8, 1800.90, 'DEBIT_CARD'),
(3, 9, 2999.99, 'CREDIT_CARD');

CREATE TABLE Restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    location VARCHAR(150),
    contact_number VARCHAR(15),
    email VARCHAR(100)
);

INSERT INTO Restaurants (name, location, contact_number, email) VALUES
('The Spice Route', 'Chennai', '9876501234', 'spiceroute@hotpot.com'),
('Tandoori Trails', 'Hyderabad', '9811102233', 'tandoori@hotpot.com'),
('Veggie Delights', 'Bangalore', '9822203344', 'veggied@hotpot.com'),
('Bombay Biryani House', 'Mumbai', '9833304455', 'bombaybiryani@hotpot.com'),
('Royal Punjabi Dhaba', 'Delhi', '9844405566', 'royalpunjabi@hotpot.com');

CREATE TABLE orders (
    ORD_ID int NOT NULL AUTO_INCREMENT,
    CUS_ID int unsigned NOT NULL,
    VEN_ID int unsigned NOT NULL,
    WAL_SOURCE varchar(30) DEFAULT NULL,
    MEN_ID int NOT NULL,
    ORD_DATE date DEFAULT NULL,
    ORD_QUANTITY int DEFAULT NULL,
    ORD_BILLAMOUNT double DEFAULT NULL,
    ORD_STATUS enum('ACCEPTED','DENIED','PENDING') DEFAULT 'PENDING',
    ORD_COMMENTS varchar(50) DEFAULT NULL,
    PRIMARY KEY (ORD_ID)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO orders (ORD_ID, CUS_ID, VEN_ID, WAL_SOURCE, MEN_ID, ORD_DATE, ORD_QUANTITY, ORD_BILLAMOUNT, ORD_STATUS, ORD_COMMENTS) VALUES
(1, 1, 1, 'PAYTM', 3, '2025-06-15', 2, 640.00, 'ACCEPTED', 'Less spicy please'),
(2, 2, 1, 'DEBIT_CARD', 5, '2025-06-16', 1, 750.00, 'PENDING', 'Fresh fish only'),
(3, 3, 2, 'CREDIT_CARD', 1, '2025-06-17', 3, 960.00, 'PENDING', 'Extra paneer'),
(4, 1, 3, 'PAYTM', 6, '2025-06-18', 2, 222.00, 'DENIED', 'Not available'),
(5, 2, 1, 'CREDIT_CARD', 9, '2025-06-18', 1, 299.99, 'PENDING', 'Deliver hot'),
(6, 3, 2, 'PAYTM', 8, '2025-06-19', 2, 1040.00, 'PENDING', 'Extra chutney'),
(7, 1, 2, 'DEBIT_CARD', 4, '2025-06-19', 1, 480.00, 'PENDING', 'No onions'),
(8, 2, 3, 'PAYTM', 10, '2025-06-20', 1, 650.00, 'ACCEPTED', 'Party order'),
(9, 3, 1, 'CREDIT_CARD', 7, '2025-06-20', 3, 120.00, 'PENDING', 'For 3 guests'),
(10, 1, 1, 'PAYTM', 2, '2025-06-21', 1, 560.00, 'DENIED', 'Retry tomorrow');

DROP TABLE IF EXISTS ordersnew;

CREATE TABLE ordersnew (
    order_id int NOT NULL AUTO_INCREMENT,
    custId int DEFAULT NULL,
    venId int DEFAULT NULL,
    MenuId int DEFAULT NULL,
    WalSource varchar(30) DEFAULT NULL,
    qtyord int DEFAULT NULL,
    billAmount double DEFAULT NULL,
    ordstatus enum('ACCEPTED','DENIED','PENDING') DEFAULT 'PENDING',
    comments varchar(30) DEFAULT NULL,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO ordersnew (order_id, custId, venId, MenuId, WalSource, qtyord, billAmount, ordstatus, comments) VALUES
(1, 1, 1, 3, 'PAYTM', 2, 640.00, 'PENDING', 'No spice'),
(2, 2, 2, 4, 'DEBIT_CARD', 1, 480.00, 'ACCEPTED', 'Extra gravy'),
(3, 3, 1, 5, 'CREDIT_CARD', 1, 750.00, 'DENIED', 'Item not available'),
(4, 1, 2, 8, 'PAYTM', 3, 1350.00, 'PENDING', 'Include tissues'),
(5, 2, 3, 10, 'DEBIT_CARD', 2, 1300.00, 'PENDING', 'Birthday meal'),
(6, 3, 2, 2, 'CREDIT_CARD', 1, 560.00, 'PENDING', 'Lunch order');
