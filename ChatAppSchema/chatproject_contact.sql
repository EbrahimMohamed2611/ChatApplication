CREATE TABLE `contact` (
                        `userPhone` varchar(11) NOT NULL,
                        `contactPhone` varchar(11) NOT NULL,
                        `email` varchar(30) NOT NULL,
                        user_name VARCHAR(30) NOT NULL,
                        `bio` varchar(30) DEFAULT NULL,
                        CONSTRAINT `userPhone` FOREIGN KEY (`userPhone`) REFERENCES `user` (`phone_number`) ON DELETE CASCADE ON UPDATE CASCADE,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;