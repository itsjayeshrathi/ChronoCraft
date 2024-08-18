ChronoCraft
ChronoCraft is a comprehensive and user-friendly e-commerce platform specifically designed for watch enthusiasts. Developed with Spring Boot for the backend and React for the frontend, this project offers a seamless shopping experience. Key features include searching and sorting watches by name and price, user authentication with a registration and login system, cart functionality to add or remove watches, and admin capabilities for adding new watches.

Table of Contents
Features
Technologies Used
Getting Started
Prerequisites
Installation
Usage
API Endpoints
Contributing
License
Contact
Features
Search and Sort: Search watches by name or brand, and sort by price (ascending or descending).
User Authentication: Secure user registration and login system.
Shopping Cart: Add or remove watches from the cart and proceed to checkout.
Admin Panel: Admins can add, update, or delete watches.
Technologies Used
Backend: Spring Boot
Frontend: React
Database: MySQL or PostgreSQL (specify if using another database)
API Documentation: Swagger (if applicable)
Getting Started
Prerequisites
Before you begin, ensure you have met the following requirements:

Java 11 or higher
Node.js and npm (for React)
MySQL or PostgreSQL (or your preferred database)
Spring Boot installed
React installed
Installation
Backend (Spring Boot)
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/chronocraft.git
cd chronocraft/backend
Configure the database connection in application.properties:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/chronocraft
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build and run the Spring Boot application:

bash
Copy code
mvn clean install
mvn spring-boot:run
Frontend (React)
Navigate to the frontend directory:

bash
Copy code
cd chronocraft/frontend
Install the dependencies:

bash
Copy code
npm install
Start the React application:

bash
Copy code
npm start
Usage
Access the application at http://localhost:3000.
Use the search bar to find watches by name or brand.
Sort the watch listings by price using the dropdown menu.
Log in or register to add watches to your cart.
As an admin, manage the watch listings through the admin panel.
API Endpoints
Here are some of the key API endpoints:

GET /watches: Retrieve all watches
GET /watches/{id}: Retrieve a specific watch by ID
POST /watches: Create a new watch (admin only)
PUT /watches/{id}: Update a watch (admin only)
DELETE /watches/{id}: Delete a watch (admin only)
POST /users/register: Register a new user
POST /users/login: Authenticate a user
For a full list of API endpoints and details, refer to the API Documentation.

Contributing
Contributions are welcome! Please follow these steps to contribute:

Fork the repository.
Create a new branch: git checkout -b feature/your-feature-name
Commit your changes: git commit -m 'Add some feature'
Push to the branch: git push origin feature/your-feature-name
Create a pull request.
