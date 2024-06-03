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
  Scenario Outline: Validate Calculator Numpad Operations in prodEnv
    Given I navigate to "prodEnv"
    When I am on Calculator
    And I wait for 1 second
    And I click on the <digit1>
    And I click on the <operator>
    And I click on the <digit2>
    And I click on the ButtonEquals
    And I wait for 1 second
    Then the DisplayPanel field content should be equal to the following "<outCome>"

    Examples:
      | outCome | digit1      | digit2      | operator             |
      | 3       | ButtonTwo   | ButtonOne   | ButtonAddition       |
      | 1       | ButtonThree | ButtonTwo   | ButtonSubtraction    |
      | 40      | ButtonFive  | ButtonEight | ButtonMultiplication |
      | 4.5     | ButtonNine  | ButtonTwo   | ButtonDivision       |
      | 35      | ButtonSeven | ButtonFive  | ButtonMultiplication |


  @calc_stage_env @bug # last three operations will fail in stage env due to apparent inaccuracy of outcome when we apply multiplication and division
  Scenario Outline: Validate Calculator functions in stage env
    Given I navigate to "stageEnv"
    When I am on Calculator
    And I wait for 2 seconds
    And I click on the <digit1>
    And I click on the <operator>
    And I click on the <digit2>
    And I click on the ButtonEquals
    And I wait for 1 second
    Then the DisplayPanel field content should be equal to the following "<outCome>"

    Examples:
      | outCome | digit1      | digit2      | operator             |
      | 3       | ButtonTwo   | ButtonOne   | ButtonAddition       |
      | 1       | ButtonThree | ButtonTwo   | ButtonSubtraction    |
      | 40      | ButtonFive  | ButtonEight | ButtonMultiplication |
      | 4.5     | ButtonNine  | ButtonTwo   | ButtonDivision       |
      | 35      | ButtonSeven | ButtonFive  | ButtonMultiplication |


  @calc_all_env @bug # the ButtonFive click iteration will fail due to the wrong reflection of output or value in stageEnv
  Scenario Outline: Validate Numpad values are reflected accurately When corresponding keys are pressed in <env>
    Given I navigate to "<env>"
    When I am on Calculator
    And I click on the <digit>
    And I wait for 1 second
    Then the DisplayPanel field content should be equal to the following "<outCome>"

    Examples:
      | env      | outCome | digit       |
      | prodEnv  | 0       | ButtonZero  |
      | prodEnv  | 1       | ButtonOne   |
      | prodEnv  | 2       | ButtonTwo   |
      | prodEnv  | 3       | ButtonThree |
      | prodEnv  | 4       | ButtonFour  |
      | prodEnv  | 5       | ButtonFive  |
      | prodEnv  | 6       | ButtonSix   |
      | prodEnv  | 7       | ButtonSeven |
      | prodEnv  | 8       | ButtonEight |
      | prodEnv  | 9       | ButtonNine  |
      | stageEnv | 0       | ButtonZero  |
      | stageEnv | 1       | ButtonOne   |
      | stageEnv | 2       | ButtonTwo   |
      | stageEnv | 3       | ButtonThree |
      | stageEnv | 4       | ButtonFour  |
      | stageEnv | 5       | ButtonFive  |
      | stageEnv | 6       | ButtonSix   |
      | stageEnv | 7       | ButtonSeven |
      | stageEnv | 8       | ButtonEight |
      | stageEnv | 9       | ButtonNine  |

