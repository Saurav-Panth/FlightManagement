# ✈️ FlightManagement API

FlightManagement API is a Spring Boot–based RESTful application that manages **flights, bookings, passengers, and payments**.
It is designed to handle end-to-end flight booking operations with proper exception handling and clean API structure.

---

## 🚀 Features

* Flight management (CRUD operations)
* Booking flights with passengers
* Passenger information management
* Payment tracking and status updates
* Booking status management
* Centralized global exception handling
* Enum-based validations for status, gender, and payment modes

---

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web (REST APIs)**
* **Spring Data JPA / Hibernate**
* **MySQL** 
* **Maven**
* **Postman** 

---

## 📁 Project Structure

```
com.book.project
│
├── entities
│   ├── Booking.java
│   ├── Flight.java
│   ├── Passenger.java
│   └── Payment.java
│
├── enums
│   ├── BookingStatus.java
│   ├── PassengerGender.java
│   ├── PaymentMode.java
│   └── PaymentStatus.java
│
├── exceptions
│   ├── BookingNotFoundException.java
│   ├── FlightNotFoundException.java
│   ├── PassengerNotFoundException.java
│   ├── PaymentNotFoundException.java
│   ├── SeatNotAvailableException.java
│   ├── NoRecordException.java
│   ├── IdNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── controllers
│   ├── BookingController.java
│   ├── FlightController.java
│   ├── PassengerController.java
│   └── PaymentController.java
```

---

## 🔗 API Endpoints

### 🧾 Booking APIs (`/booking`)

| Method | Endpoint                   | Description                 |
| ------ | -------------------------- | --------------------------- |
| POST   | `/booking`                 | Create a new booking        |
| GET    | `/booking`                 | Get all bookings            |
| GET    | `/booking/{id}`            | Get booking by ID           |
| GET    | `/booking/flight/{id}`     | Get bookings by flight      |
| GET    | `/booking/date/{date}`     | Get bookings by date        |
| GET    | `/booking/status/{status}` | Get bookings by status      |
| GET    | `/booking/{id}/passengers` | Get passengers of a booking |
| GET    | `/booking/{id}/payment`    | Get payment of a booking    |
| PUT    | `/booking/{id}/{status}`   | Update booking status       |
| DELETE | `/booking/{id}`            | Delete booking              |

---

### ✈️ Flight APIs (`/flight`)

| Method | Endpoint                         | Description                         |
| ------ | -------------------------------- | ----------------------------------- |
| POST   | `/flight`                        | Add a new flight                    |
| GET    | `/flight`                        | Get all flights                     |
| GET    | `/flight/{id}`                   | Get flight by ID                    |
| GET    | `/flight/{source}/{destination}` | Get flights by source & destination |
| GET    | `/flight/airline/{airline}`      | Get flights by airline              |
| PUT    | `/flight`                        | Update flight                       |
| DELETE | `/flight/{id}`                   | Delete flight                       |

---

### 👤 Passenger APIs (`/passenger`)

| Method | Endpoint                          | Description              |
| ------ | --------------------------------- | ------------------------ |
| GET    | `/passenger`                      | Get all passengers       |
| GET    | `/passenger/{id}`                 | Get passenger by ID      |
| GET    | `/passenger/phone/{contact}`      | Get passenger by phone   |
| PUT    | `/passenger/{id}/age/{age}`       | Update passenger age     |
| PUT    | `/passenger/{id}/name/{name}`     | Update passenger name    |
| PUT    | `/passenger/{id}/phone/{phone}`   | Update passenger phone   |
| PUT    | `/passenger/{id}/gender/{status}` | Update passenger gender  |
| GET    | `/passenger/flight/{id}`          | Get passenger’s flight   |
| PUT    | `/passenger/updateInfo`           | Update passenger details |

---

### 💳 Payment APIs (`/payment`)

| Method | Endpoint                        | Description               |
| ------ | ------------------------------- | ------------------------- |
| GET    | `/payment`                      | Get all payments          |
| GET    | `/payment/{id}`                 | Get payment by ID         |
| GET    | `/payment/status/{status}`      | Get payments by status    |
| PUT    | `/payment/{id}/status/{status}` | Update payment status     |
| GET    | `/payment/morethen/{amount}`    | Get payments above amount |

---

## ⚠️ Exception Handling

The application uses a **GlobalExceptionHandler** to handle errors consistently.

Handled exceptions include:

* `BookingNotFoundException`
* `FlightNotFoundException`
* `PassengerNotFoundException`
* `PaymentNotFoundException`
* `SeatNotAvailableException`
* `NoRecordException`
* `IdNotFoundException`

All exceptions return structured and meaningful API responses.

---

## ▶️ How to Run the Project

1. Clone the repository

   ```bash
   git clone https://github.com/your-username/flight-management-api.git
   ```
2. Open the project in **STS / IntelliJ / Eclipse**
3. Configure `application.properties`

   ```
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Run the application
5. Test APIs using **Postman**

---

## 📌 Future Enhancements

* Authentication & Authorization
* Pagination & sorting
* Swagger / OpenAPI documentation
* Logging & monitoring
* Role-based access control

---

## 👨‍💻 Author

**Saurav Panth**
Java & Spring Boot Developer

---

⭐ If you like this project, don’t forget to **star the repository**!
