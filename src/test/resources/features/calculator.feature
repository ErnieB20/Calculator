
@calculator
Feature: Test Calculator

  @smoke
  Scenario: Validate Calculator Panel
    Given I navigate to "prodEnv"
    When I am on Calculator
    And I wait for 2 seconds
    Then the CalculatorPanel should be visible
    And the following elements should be displayed:
      | ButtonClear          |
      | ButtonDivision       |
      | ButtonMultiplication |
      | ButtonSubtraction    |
      | ButtonAddition       |
      | ButtonDecimal        |
      | ButtonEquals         |
      | ButtonZero           |
      | ButtonOne            |
      | ButtonTwo            |
      | ButtonThree          |
      | ButtonFour           |
      | ButtonFive           |
      | ButtonSix            |
      | ButtonSeven          |
      | ButtonEight          |
      | ButtonNine           |

  @calc_prod_env
  Scenario Outline: Validate Calculator Functions prodEnv
    Given I navigate to "prodEnv"
    When I am on Calculator
    And I wait for 2 seconds
    And I click on the <digit1>
    And I click on the <operator>
    And I click on the <digit2>
    And I click on the ButtonEquals
    And I wait for 1 second
    Then the DisplayPanel field should be equal to the following "<outCome>"

    Examples:
      | outCome | digit1      | digit2      | operator             |
      | 3       | ButtonTwo   | ButtonOne   | ButtonAddition       |
      | 1       | ButtonThree | ButtonTwo   | ButtonSubtraction    |
      | 40      | ButtonFive  | ButtonEight | ButtonMultiplication |
      | 4.5     | ButtonNine  | ButtonTwo   | ButtonDivision       |
      | 35      | ButtonSeven | ButtonFive  | ButtonMultiplication |

  @calc_stage_env @bug # last three operations will fail in stage env due to apparent inaccuracy of outcome when we apply multiplication and division
  Scenario Outline: Validate Calculator functions in stage env
    Given I navigate to "stateEnv"
    When I am on Calculator
    And I wait for 2 seconds
    And I click on the <digit1>
    And I click on the <operator>
    And I click on the <digit2>
    And I click on the ButtonEquals
    And I wait for 1 second
    Then the DisplayPanel field should be equal to the following "<outCome>"

    Examples:
      | outCome | digit1      | digit2      | operator             |
      | 3       | ButtonTwo   | ButtonOne   | ButtonAddition       |
      | 1       | ButtonThree | ButtonTwo   | ButtonSubtraction    |
      | 40      | ButtonFive  | ButtonEight | ButtonMultiplication |
      | 4.5     | ButtonNine  | ButtonTwo   | ButtonDivision       |
      | 35      | ButtonSeven | ButtonFive  | ButtonMultiplication |

