package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepDefinitions {

    Integer firstNumber;
    Integer secondNumber;
    Integer sum;

    @Given("two numbers {int} and {int}")
    public void getTwoNumbers(Integer int1, Integer int2) {
            firstNumber = int1;
            secondNumber = int2;
    }

    @When("the numbers are added")
    public void addTwoNumber() {
        sum = Integer.valueOf(firstNumber.intValue()+secondNumber.intValue());
    }

    @Then("the result is {int}")
    public void addResult(Integer result){
        Assert.assertEquals(sum.intValue(),result.intValue());
    }

    @Given("User Says {string}")
    public void userSays(String word) {
        System.out.println(word);
    }

    @When("the system is processing {string}")
    public void systemThinks(String word){
        System.out.println(word);
    }
    @Then("the system should say {string}")
    public void systemSays(String word){
        System.out.println(word);
    }

}
