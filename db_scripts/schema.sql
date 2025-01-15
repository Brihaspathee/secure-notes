DROP DATABASE IF EXISTS securenotesdb;
DROP USER IF EXISTS `securenotesadmin`@`%`;
DROP USER IF EXISTS `securenotesapp`@`%`;
CREATE DATABASE IF NOT EXISTS securenotesdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `securenotesadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `securenotesdb`.* TO `securenotesadmin`@`%`;
CREATE USER IF NOT EXISTS `securenotesapp`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `securenotesdb`.* TO `securenotesapp`@`%`;
FLUSH PRIVILEGES;