
---

# 🛒 OMS System - Order System Architecture

## 🔍 Overview

**OMS System** is an e-commerce application built using **Microservices Architecture** and **Domain-Driven Design (DDD)** principles. The system is designed to simplify business processes, improve efficiency, and enable scalability.

---

## 🎯 Objectives & Key Features

* **Resilience**: The system remains functional even if a component fails.
* **Scalability**: Handles high traffic and large volumes of transactions.
* **Faster Development**: Microservices enable independent team development.
* **Monitoring & Debugging**: Full observability with Zipkin, MailDev, and Eureka.

### 🔧 Core Capabilities

* Customer, product, order, and payment management.
* Email notifications for order and payment confirmations.
* Communication between services via REST (OpenFeign, RestTemplate) and Kafka.
* Centralized configuration, service discovery, and OAuth2-based security.

---

## 🧱 System Architecture

The system is composed of independent **microservices**, each focused on a specific business domain:

| Domain       | Microservice                         | Database                     |
| ------------ | ------------------------------------ | ---------------------------- |
| Customer     | Customer management (info + address) | MongoDB                      |
| Product      | Product and category management      | PostgreSQL                   |
| Order        | Order and order line processing      | PostgreSQL                   |
| Payment      | Payment recording and handling       | PostgreSQL                   |
| Notification | Email notification service           | MongoDB (optional/stateless) |

### 🏗️ Infrastructure Components

* **Config Server**: Centralized configuration management.
* **Eureka Discovery Server**: Service registration and discovery.
* **API Gateway**: Route and secure requests.
* **Keycloak**: OAuth2-based authentication and authorization.
* **Kafka**: Event-driven communication for order and payment flows.
* **Zipkin**: Distributed tracing.
* **MailDev**: Local SMTP server for testing email functionality.

---

## ⚙️ Technologies & Tools

* **Spring Boot**, **Spring Cloud**, **Spring Security**
* **MongoDB**, **PostgreSQL**, **Flyway**
* **Apache Kafka**, **JavaMailSender**, **Thymeleaf**
* **Docker Compose**, **Keycloak**, **Zipkin**, **MailDev**
* **IntelliJ IDEA**, **Postman**, **PG Admin**, **Mongo Express**

---

## 🚀 Getting Started

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-repo/oms-system.git
   cd oms-system
   ```

2. **Run with Docker Compose**:

   ```bash
   docker-compose up -d
   ```

3. **Access management tools**:

    * **Eureka Dashboard**: `http://localhost:8761`
    * **MailDev**: `http://localhost:1080`
    * **Zipkin**: `http://localhost:9411`
    * **Keycloak**: `http://localhost:8080`
    * **Mongo Express**: `http://localhost:8081`
    * **PG Admin**: `http://localhost:5050`

---

## 🔐 Security with Keycloak

* Uses **Keycloak** as the OAuth2 Authorization Server.
* Each microservice acts as a **resource server**.
* Uses **Client Credentials Grant** for secure service-to-service communication.
* API testing supported via Postman with OAuth2 tokens.

---

## 🧪 Testing & Observability

* **API Testing**: Postman collections available.
* **Database Access**: Use Mongo Express and PG Admin.
* **Email Testing**: Verify emails using MailDev.
* **Distributed Tracing**: Monitor service calls with Zipkin.

---

## 📂 Project Structure

```plaintext
oms-system/
│
├── customer-service/
├── product-service/
├── order-service/
├── payment-service/
├── notification-service/
├── config-server/
├── discovery-server/
├── api-gateway/
├── docker-compose.yml
├── README.md
```

---

## 📚 References

Documentation and source references are labeled with citations (\[1], \[2], ...) in the source code. See the `docs/` directory or comments for detailed explanations.

---

## 💡 Notes

* Each service can be scaled independently based on workload.
* Event-driven design with Kafka increases fault tolerance and decoupling.
* Easily extendable with additional features like cart, coupons, or product reviews.

---

## 👨‍💻 Contributions

Contributions are welcome! Feel free to open an issue or submit a pull request.

---

## 📜 License

Distributed under the MIT License. See the `LICENSE` file for more information.

---

Let me know if you want a markdown version of this exported or if you'd like to add diagrams or setup guides!
