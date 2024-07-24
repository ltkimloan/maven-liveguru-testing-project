# MAVEN LIVE GURU TESTING PROJECT
 A project for building Hybrid Automation Framework based on Java language using TestNG, Selenium Webdriver.

## Concepts Included

* Java OOP
* Parallel test runs with multiple browsers
* Shared state across cucumber step definitions
* Dependency injection
* Page Object Model (POM)
* Common web page interaction methods
* Externalised test configuration
* Commonly used test utility classes
* Allure Report
* Log4j Version 2
* Data Test with Jackson library
* Verify testcase Download Files (Only support for Chrome browser and Firefox browser)
* Verify testcase Upload Files


## Tools

* Maven 3.10.1
* Java 11
* TestNG 7.8.0
* Selenium Webdriver 4.16.1
* Jackson
* Allure 
* Log4j

## Requirements

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Chrome, Edge, Firefox, or Safari
* Java 1.11
* Allure (to generate report)

## Usage

The project is for UI testing (Frontend and Backend). Each of these modules can be run parallel using maven profiles or TestNG runner.

To run UI acceptance tests only by command line, navigate to `maven-liveguru-testing` directory and run:

`mvn clean test`

To run security tests only by IDE & TestNG runner, Open IDE and run file xml:

`RunLiveGuruWeb.xml`

## Reporting

Reports for each module are written into their respective `/target` directories after a successful run.

With IDE, UI acceptance tests result in a HTML report for each feature in `maven-liveguru-testing/test-output/html/index.html`.
In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

Example:
![img.png](image%2Fimg.png)

To generate report, navigate to `maven-liveguru-testing` directory and run: 
`allure serve allure-json`.

Example:
![img_1.png](image%2Fimg_1.png)

To log file will generate in `maven-liveguru-testing/logs`

Example:
![img_2.png](image%2Fimg_2.png)