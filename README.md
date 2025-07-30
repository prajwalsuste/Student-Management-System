# Student Management System (Java Swing + MySQL)

## 📌 Description
A simple desktop-based Student Management System built using Java Swing and MySQL, following MVC architecture. Supports CRUD operations.

## 💻 Tech Stack
- Java (Swing)
- MySQL
- JDBC
- MVC Design Pattern

## 🚀 Features
- Add new student
- View all students
- Delete student
- GUI with JTable integration

## 🛠️ How to Run
1. Import project in any Java IDE (Eclipse, IntelliJ, VS Code).
2. Set MySQL port and DB credentials in `ConnectionObject.java`.
3. Create a database named `javadb` with a `student` table:
```sql
CREATE DATABASE javadb;
USE javadb;
CREATE TABLE student (
    rno INT PRIMARY KEY,
    name VARCHAR(100)
);
