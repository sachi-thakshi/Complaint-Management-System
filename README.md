# 🌐 Municipal IT Division - Complaint Management System (CMS)

### JakartaEE Project 2025 - IJSE 72 Sachini Imbulagoda Galle

---

## 📌 Project Overview

This is a full-stack **Complaint Management System (CMS)** developed for the **Municipal IT Division** as part of the IJSE Advanced API Development module. It allows municipal employees to submit complaints related to services and operations, and provides a secure admin interface to monitor and resolve issues effectively.

The project showcases the practical use of **Java EE technologies**, especially **JSP**, **Servlets**, and **JDBC with DBCP**, following the **Model-View-Controller (MVC)** architectural pattern. It strictly adheres to form-based synchronous HTTP interaction — **no AJAX, fetch, or asynchronous calls are used**, per assignment rules.

---

## ✨ Key Features

### 🧑 Employee
- ✅ Register & log in
- ✅ Submit new complaints
- ✅ View list of submitted complaints
- ✅ Edit or delete complaints (if not resolved)

### 👨‍💼 Admin
- ✅ Log in with admin credentials
- ✅ View all complaints from all users
- ✅ Update complaint status and add remarks
- ✅ Delete any complaint regardless of state
- ✅ View all users
- ✅ Delete & Update users

### 🔒 Security & UX
- 🔐 Session management to restrict unauthorized access
- ⚠️ SweetAlert2 integration for clean and modern alerts
- ❌ No AJAX used (strict GET/POST via forms only)
- 🌐 MVC-compliant, modular code

## 🛠️ Technologies Used

| Category       | Tools/Technologies                |
|----------------|-----------------------------------|
| Language       | Java 21                           |
| Web            | JSP, Servlets, HTML, CSS          |
| DB Access      | MySQL, JDBC, Apache Commons DBCP  |
| Architecture   | MVC (Model-View-Controller)       |
| Server         | Apache Tomcat 9+                  |
| Validation     | JavaScript                        |
| Alerts         | SweetAlert2                       |
| Versioning     | Git + GitHub                      |
| Build          | Maven                             |

---

## 🧱 Project Directory Structure

- **Complaint_Management_System/**
  - **src/**
    - **controller/** &nbsp;&nbsp;_# Servlets for handling requests_
    - **dao/** &nbsp;&nbsp;_# DAO classes for DB access_
    - **model/** &nbsp;&nbsp;_# POJOs / JavaBeans_
    - **util/** &nbsp;&nbsp;_# DBCP config and utilities_
  - **web/**
    - **WEB-INF/** &nbsp;&nbsp;_# Web app config files_
    - **web.xml** &nbsp;&nbsp;_# Deployment descriptor_
    - **jsp/** &nbsp;&nbsp;_# JSP files (views)_
    - **css/** &nbsp;&nbsp;_# Styling_
    - **js/** &nbsp;&nbsp;_# JS for validation and SweetAlert_
  - **db/**
    - **schema.sql** &nbsp;&nbsp;_# MySQL schema dump_
  - **README.md**
  - **pom.xml**

## ⚙️ Setup and Configuration Guide

### 📋 Prerequisites

- Java 11+
- Apache Tomcat 9+
- MySQL 8 or newer
- Git
- IDE (e.g., IntelliJ IDEA / Eclipse)
-  Maven

### 🧪 Installation & Run Instructions

```bash
# 1. Clone the project
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

# 2. Import the database schema into MySQL
mysql -u root -p < db/schema.sql

# 3. Configure DB connection
Edit DBCPDataSource.java with your DB credentials

# 4. Deploy to Apache Tomcat (via WAR or IDE integration)

# 5. Run
Access the system at:
http://localhost:8080/cms-jsp-project/
