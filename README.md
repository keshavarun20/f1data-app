# F1 Data App - Lap Time Analysis

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://your-ci-cd-link.com)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-brightgreen.svg)](https://www.thymeleaf.org/)

## Overview

The **F1 Data App** is a web application built using Spring Boot and Thymeleaf that allows users to retrieve and display detailed lap time data for Formula 1 races. By selecting a specific **year**, **Grand Prix (GP)**, and **driver name**, the application fetches data from the [Ergast Developer API](https://api.jolpi.ca/ergast) and presents a clear table showing the lap times for each lap and the corresponding position of the selected driver during that lap.

This application provides a user-friendly interface to explore the intricate details of F1 race performance.

## Features

* **Year Selection:** Allows users to choose a specific Formula 1 season.
* **Grand Prix Selection:** Provides a dropdown list of races for the selected year.
* **Driver Selection:** Offers a dropdown list of drivers who participated in the selected Grand Prix.
* **Lap Time Display:** Presents a table showing:
    * **Lap Number:** The sequential number of the lap.
    * **Position:** The selected driver's position at the end of that lap.
    * **Lap Time:** The duration of the lap.
* **Clear and Intuitive User Interface:** Built with Thymeleaf for dynamic HTML rendering.
* **Utilizes Ergast API:** Leverages the reliable and comprehensive F1 data provided by the Ergast Developer API.

## Technologies Used

* **Spring Boot:** A powerful Java-based framework for building web applications.
* **Thymeleaf:** A Java server-side template engine for creating dynamic web pages.
* **Java:** The programming language used for the backend logic.
* **HTTP Client (e.g., RestTemplate):** For making requests to the Ergast API.
* **HTML/CSS:** For structuring and styling the user interface.

## Setup and Installation

1.  **Prerequisites:**
    * Java Development Kit (JDK) 17 or later.
    * Maven (for dependency management and building).

2.  **Clone the Repository:**
    ```bash
    git clone https://github.com/keshavarun20/f1data-app/
    cd f1-data-app
    ```

3.  **Build the Application:**
    ```bash
    mvn clean install
    ```

4.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```

    The application will typically start on `http://localhost:8080`.

## Usage

1.  Open your web browser and navigate to `http://localhost:8080`.
2.  On the main page, you will find dropdown menus for:
    * **Year:** Select the desired Formula 1 season.
    * **Grand Prix:** Once a year is selected, this dropdown will populate with the races from that season.
    * **Driver:** After selecting a year and Grand Prix, this dropdown will list the drivers who participated in that race.
3.  Click the "Get Lap Times" button.
4.  The application will fetch the lap time data for the selected year, Grand Prix, and driver from the Ergast API.
5.  A table will be displayed showing the lap number, the driver's position at the end of that lap, and the lap time.

## API Integration

The application interacts with the [Ergast Developer API](https://api.jolpi.ca/ergast) to retrieve F1 data. Specifically, it will likely use the following endpoints:

* `/api/f1/{year}/races.json`: To fetch the list of Grand Prix for a given year.
* `/api/f1/{year}/{round}/drivers.json`: To fetch the list of drivers who participated in a specific race.
* `/api/f1/{year}/{round}/drivers/{driverId}/laps.json`: To fetch the lap times for a specific driver in a specific race.

The `F1Service` component in the Spring Boot application handles the communication with these API endpoints, parsing the JSON responses and mapping them to appropriate Java objects.

## Contributing

Contributions to the F1 Data App are welcome! If you have any ideas for improvements, bug fixes, or new features, please feel free to:

1.  Fork the repository.
2.  Create a new branch for your changes.
3.  Make your changes and commit them.
4.  Push your changes to your fork.
5.  Submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). See the `LICENSE` file for more details.

## Acknowledgements

* The [Ergast Developer API](https://api.jolpi.ca/ergast) for providing the valuable Formula 1 data.
* The Spring Boot and Thymeleaf communities for their excellent frameworks and documentation.

## Future Enhancements

* Implement filtering or sorting of the lap time data.
* Add visualizations (e.g., charts) to represent lap time trends.
* Allow users to compare lap times of multiple drivers in the same race.
* Include more detailed race information (e.g., weather conditions, starting grid).
* Implement error handling for API requests and user input.
* Improve the styling and responsiveness of the user interface.
