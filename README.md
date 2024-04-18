# DentalProfitApp

DentalProfitApp is an application designed to collect data about a doctor's daily earnings in relation to the number of hours worked. The application allows for visualization of data from each month and presents the doctor with the highest earnings on a chart. With this application, we can analyze which doctors bring us the highest profit, both month by month and in the annual summary.

## Technologies

- Java
- Spring Boot
- Swagger
- PostgreSQL
- Maven
- Docker
- Lombok
- Thymeleaf
- HTML
- CSS
- REST API

## Features

- Collecting data on a doctor's daily earnings.
- Converting earnings into the number of hours worked.
- Presentation of data from each month.
- Displaying the doctor with the highest earnings on a chart.
- Analysis of doctors bringing the highest profit.

## Requirements

- Java 8+
- Spring Boot
- PostgreSQL
- Maven
- Docker
- Lombok
- Thymeleaf
- HTML
- CSS
- Swagger
- UI library (optional)

## Installation and Running

1. Clone the repository:
   ```
   git clone https://github.com/javne/DentalProfitApp.git
   ```

2. Navigate to the project directory:
   ```
   cd DentalProfitApp
   ```

3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```

4. (Optional) You can build a Docker image and run the application in a container:
   ```
   docker build -t dental-profit-app .
   docker run -p 8080:8080 dental-profit-app
   ```

## Contributions

If you would like to contribute your fixes or features to the application, feel free to contribute. Please adhere to coding standards and clearly describe the changes to facilitate the code review process.

1. Start by forking the repository.
2. Create a new branch (`git checkout -b new-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push the changes to the branch (`git push origin new-branch`).
6. Create a new Pull Request.

## Authors

- Ewelina Borkowska - [Javne](https://github.com/Javne)

## License

This project is licensed under the [DentalProfitAPP License]. See the [LICENSE.md](LICENSE.md) file for details.
