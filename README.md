# Vahan Vehicle Registration Dashboard

![Vahan Dashboard Final Screenshot](https://i.imgur.com/05ebed.png)

## 🚀 Project Overview

This project is an interactive web dashboard developed as part of the Backend Developer Internship assignment. The application provides an investor-focused view of vehicle registration data in India, inspired by the public Vahan Dashboard. It processes and displays key growth metrics, allowing users to analyze trends across different states, time periods, and vehicle manufacturers.

The core of the application is built using the **Java Spring Boot** framework, demonstrating robust backend development practices, RESTful API design, and server-side rendering with a dynamic, modern frontend. The primary goal is to transform raw registration numbers into actionable insights for stakeholders interested in the Indian automotive market.

---

## ✨ Key Features

- **YoY & QoQ Growth Analysis:** Automatically calculates and displays Year-over-Year (YoY) and Quarter-over-Quarter (QoQ) growth percentages for key metrics.
- **Detailed Data Views:**
    - **Vehicle Class Analysis:** Breaks down vehicle registrations by primary type (2W, 3W, 4W) and specific classes (e.g., MOTOR CAR, SCOOTER, LMV).
    - **Manufacturer Analysis:** Provides a summary of total registrations and growth rates for each manufacturer.
- **Interactive Filtering:** Users can dynamically filter the entire dataset by:
    - **State / Union Territory**
    - **Year**
    - **Quarter**
    - **Manufacturer**
- **Dynamic UI:** The user interface is clean, responsive, and updates automatically based on filter selections.
- **SQL Database:** Utilizes a MySQL database to store and manage the vehicle registration data.

---
## 🏛️ Project Architecture

The application follows the classic **Model-View-Controller (MVC)** architectural pattern, which ensures a clean separation of concerns and makes the codebase modular and maintainable.

* **Model:** Represents the data structure. In our case, this is the `VehicleData` entity, which directly maps to the `vehicle` table in our MySQL database. We also use Data Transfer Objects (DTOs) like `DashboardDataDto` to shape the data specifically for the view.
* **View:** The user interface of the application. This is handled by **Thymeleaf** templates (`index.html`), which render the data provided by the controller into a dynamic web page. The styling is managed by CSS, and client-side interactions are handled by JavaScript.
* **Controller:** Acts as the bridge between the Model and the View. The `DashboardController` receives user requests from the browser, calls the `DashboardService` to fetch and process the required data, and then passes this data to the View for rendering.
* **Service Layer:** An additional layer that contains the core business logic. The `DashboardServiceImpl` is responsible for all the complex calculations, such as data aggregation and YoY/QoQ growth analysis. This keeps the controller lean and focused on handling requests.

---

## 🛠️ Technology Stack

This project was built using a modern and robust set of tools and technologies:

### **Backend**
- **Java 21:** The core programming language for building the application logic.
- **Spring Boot 3.2.5:** A powerful framework for creating stand-alone, production-grade Spring-based applications with minimal configuration.
- **Spring Data JPA (Hibernate):** Used for data persistence, simplifying database interactions by mapping Java objects to database tables.
- **Maven:** A build automation and dependency management tool for Java projects.

### **Frontend**
- **Thymeleaf:** A modern server-side Java template engine used to build the dynamic HTML pages.
- **HTML5:** The standard markup language for creating the structure of the web pages.
- **CSS3:** Used for styling the application, creating a vibrant and professional user interface.
- **JavaScript:** For enhancing client-side interactivity, such as the loading spinner.

### **Database**
- **MySQL:** An open-source relational database used to store and manage the vehicle registration data.

### **Server**
- **Embedded Apache Tomcat:** The web server that is included and auto-configured by Spring Boot, making deployment simple.

---

## ⚙️ Setup and Installation

To get this project running on your local machine, please follow these steps:

### **1. Prerequisites:**
- **Java JDK 21** or later installed.
- **Apache Maven** installed.
- **MySQL Server** installed and running.
- An IDE like **IntelliJ IDEA** or **Eclipse**.

### **2. Database Setup:**
1.  Open your MySQL client (e.g., MySQL Workbench, DBeaver).
2.  Create a new database named `vehicledb`.
3.  Execute the complete SQL script provided in the project to create the `vehicle` table and populate it with the full dataset (2021-2025).

### **3. Application Configuration:**
1.  Open the `src/main/resources/application.properties` file.
2.  Update the following properties with your MySQL username and password:
    ```properties
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    ```
    (For most local setups, the username is `root`).

### **4. Running the Application:**
1.  Open the project in your IDE.
2.  Let Maven download all the project dependencies.
3.  Navigate to the `ParivahanDashboardApplication.java` file and run it.
4.  Once the application has started, open your web browser and go to:
    **[http://localhost:9090](http://localhost:9090)**

---

## 🎥 Video Walkthrough

A short screen recording that explains the project, demonstrates how to use the dashboard, and discusses key insights can be found at the following link:

- **[View the Project Demo Video](https://drive.google.com/file/d/1jtO7-0o1eIslqvV_3bVo2tvj-o0Aw_Ti/view?usp=drive_link)**

---

## 📊 Data Assumptions

- The dataset used in this project is a comprehensive, generated dataset based on publicly available trends from the Vahan dashboard. It is not live data.
- The data has been structured to be realistic, showing seasonal trends and year-over-year growth across various states and vehicle categories to provide meaningful analysis.
- The `vehicle_class` for each record was programmatically assigned based on the `vehicle_type` and `manufacturer`.

---

## 💡 Bonus: Investor Insights

*(This is a placeholder for you to add your insights)*

While working with the data, a few interesting trends emerged that would be valuable for an investor:

1.  **Trend 1:** Describe a surprising or valuable trend you noticed (e.g., "The demand for electric 3-wheelers in Delhi showed a QoQ growth of over 15%, significantly outpacing the national average...").
2.  **Trend 2:** Describe another insight (e.g., "Despite Hero's dominance in the 2W market in Uttar Pradesh, Yamaha showed a higher percentage growth in the premium motorcycle segment...").

---

## 🗺️ Future Roadmap

While the current application meets the core requirements, here are some features that could be added in the future:

- **Graphical Data Visualization:** Implement charts and graphs (using a library like Chart.js) to visually represent registration trends.
- **Advanced RTO Filtering:** Add a filter for "Regional Transport Office (RTO)" to allow for more granular, city-level analysis.
- **Data Export:** Add a feature to export the filtered data as a CSV or Excel file.
- **API Endpoints:** Expose the data through public RESTful API endpoints.
