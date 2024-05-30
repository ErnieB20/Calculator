# Cucumber Java Test Automation Framework

This is a test automation framework using Cucumber and Java, built with Maven. It allows you to write tests in a natural language format that can be understood by non-technical stakeholders, while also providing powerful testing capabilities.

## Prerequisites

- Java 8 or higher
- Maven
- An IDE such as IntelliJ IDEA or Eclipse
  
  ## Warning
  - some of the methods may not be supported by jdk 1.8 or 11 so installing, at least jdk 17 is recomended

## Getting Started

1. Clone the repository to your local machine.
2. Open the project in your IDE.
3. Resolve the Maven dependencies by importing the project as a Maven project.

## Writing Tests

Tests are written in the `src/test/resources` directory. Each `.feature` file corresponds to a specific feature under test. Each feature contains multiple scenarios, and each scenario contains multiple steps.

Here's an example of a feature file:

```gherkin
Feature: Calculator
  Scenario: Addition
    Given I have entered 50 into the calculator
    And I have entered 70 into the calculator
    When I press add
    Then the result should be 120 on the screen
