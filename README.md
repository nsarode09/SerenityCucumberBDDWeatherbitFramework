# SerenityCucumberBDDWeatherbitFramework

# Project Description:
* The project helps to test **Weatherbit's API** with the **selenium serenity rest-assured** library.
* Serenity Cucumber BDD framework is used as it has libraries that make writing high-quality automated acceptance tests easy, with robust reporting and living  documentation features.
* The Project is a one-stop as it strongly supports web testing with Selenium and API testing using RestAssured.

# Technology Used:
* Programming Language: Java 8
* Framework: Cucumber Behaviour Driven Development (BDD)
* Build Management Tool: Maven 
* API library - Senerity Rest Assured (version - 3.9.0)
* Reporting: Serenity

# Pre-requisite to run project:
* JAVA_HOME & MAVEN_HOME installed and in set in your Windows environment variable.

  ![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/93816723-44f5-4913-838a-60a36ed3a197)

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/8c091f8c-3bfe-4f7e-a2f2-db9a7aa2bd7e)


# The project directory structure:

The project is a maven project and follows the standard directory structure:

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/e4e25805-050b-4417-991c-8226080acf68)

# pom.xml:

It is an XML file that contains information about the project and configuration details used by Maven to build the project.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/f1f7d9ae-fca6-45c4-b45c-98fc809e78ae)

All serenity, junits and json dependencies are present in this file, along with the serenity-maven-plugin used to generate the serenity report.

# The Sample Scenario:

The scenarios are written in Gherkin language (Given, When, Then, And), which is a plain english language and thus helps non-programmers to understand the scenario.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/1ccc43ae-1de5-40e6-98e8-cf1dc5b93eb4)


# The Glue or Step Definition:

This class helps to convert the cucumber english language text to test scripts. The Cucumber annotations line @Given, @And etc helps the compiler understand the prefined text and execute the command accordingly.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/d0c980af-3d53-4844-b872-a24c70519b2b)

@Steps annotation indicates the step class

# The Step class:

This class contains all the implementation methods.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/7d457ea3-2ebb-4c94-9e19-e5bb6419e66b)

The @Steps annotation tells Serenity to create a new instance of the class and inject any other steps or page objects that this instance might need.

# Actions Class:

The project contains two action classes BaseActions and Utilities.
The BaseAction class contain all standard functions like initializing the driver if required for web automation.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/43e1590a-8639-4459-8498-a5a0467935e8)

The Utilities class contain generic project functions like reading config and reading CSV file.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/5114c3d5-b6bc-47c5-9c1b-68382046611d)

# Executing the Test with Test Runner class:

This class uses the Junit annotation @RunWith() and @CucumberOptions(), which tells JUnit what the test runner class is. It is more like a starting point for Junit to execute the tests.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/54deeda6-7023-4bab-9d15-44e9baa9d6cd)

@RunWith annotation tells JUnit that tests should run using CucumberWithSerenity class present in the 'Cucumber.api.junit' package.
@CucumberOptions annotation tells Cucumber many things like where to look for feature files, where glue is present, run with tag and what reporting system to use.

# Generating the reports:

Serenity reports give aggregate information about all of the tests. It documents how features are tested, and what the API does.
The serenity-maven-plugin generates the Full Serenity reports, which is added in pom.xml.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/a361daae-ac63-45b7-8b6e-0d7f885e0b28)

The report is customized by the properties added in the serenity.properties file.

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/d398c902-bc03-4efe-a3f5-fb466dd4b06d)

# Final Report if run as JUnit Test from Test Runner Class:

![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/0b29808a-78e2-4ad7-80a5-b8d37665ce61)

# Final Report, if run with maven command - mvn clean verify

 Index.html file:

 ![image](https://github.com/nsarode09/SerenityCucumberBDDWeatherbitFramework/assets/139661841/bc1e92f9-3ded-4698-bcfc-2e184efca699)

