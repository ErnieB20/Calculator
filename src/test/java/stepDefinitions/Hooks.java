package stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import utils.TestState;

public class Hooks {

    @Before
    public void setUp(){
        TestState.setUpBeforeScenario();
    }

    @After
    public void tearDown(Scenario scenario){
        TestState.tearDownAfterScenario(scenario);
    }
}
