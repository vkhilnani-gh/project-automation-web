package definitions.steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import actions.SeWebFormAction;
import contexts.BaseContext;
import io.cucumber.java8.En;
import pages.SeWebFormPage;


public class SampleTest1Steps implements En {
    private BaseContext baseContext;

    /**
     * POM
     * 1. Page class contains both elements and methods in which case, you create an instance of page class in steps
     * class as given here.
     * Note: To avoid creating an object of page class in every step, try creating webdriver instance in base context
     * so that it is injected with it.
     * OR
     * 2. Page class contains only elements and its methods are in another class in 'action' folder. Action class
     * extends page class. Object of Action class
     */

    //Without Action classes. Page has both - locators and page methods.
    private SeWebFormPage seWebFormPage;

    //With Action class. This extends page class and has page methods.
    // Check if methods with objects from diff page can also be included.
    private SeWebFormAction seWebFormAction;

    /**
     * Cucumber scans your classes with step definitions in them, passes them to PicoContainer for DI,
     *  then asks it to create new instances for every scenario.
     */
    public SampleTest1Steps(BaseContext baseContext) {

        //Feasible because we are using method 2 of initialising web driver which is in base context.

        seWebFormAction = new SeWebFormAction(baseContext.driver);
        System.out.println("In Step definition");

   //Note: To initialise PAGE before steps, try creating webdriver instance in base context
   //Check if Page methods in this case can return the instance of the Page as its shared between steps
   // seWebFormPage = new SeWebFormPage(baseContext.driver);

        Given("^I navigate to google website$", () -> {
            baseContext.driver.navigate().to("https://www.google.com/");
        });
        When("^I type in an item in search bar$", () -> {
            baseContext.driver.findElement(By.id("Gmail"));
        });
        When("^I click on Gmail$", () -> {
            baseContext.driver.findElement(By.xpath(""));
        });

        Given("^I navigate to selenium dev$", () -> {
            baseContext.driver.navigate().to("https://www.selenium.dev/selenium/web/web-form.html");
        });
        Then("^I click on submit button$", () -> {
          //  WebElement textBox = baseContext.driver.findElement(By.name("my-text"));
          //  WebElement submitButton = baseContext.driver.findElement(By.cssSelector("button"));
          // baseContext.driver.findElement(By.cssSelector("button"));

         //Note: To avoid creating an object of page class in every step, try creating webdriver instance in base context
         // so that it is injected with it.
           seWebFormPage = new SeWebFormPage(baseContext.driver);

            // Below code will fail the test stating - java.lang.AssertionError: My text element is not empty
            //assertTrue("My text element is not empty", seWebFormPage.myText.getText().contains("test") );

            assertTrue("My text element is not empty", seWebFormPage.myText.getText().isEmpty());

            seWebFormPage.clickSubmitButton();

        });
        Then("^I click on submit button via action class$", () -> {

//            seWebFormAction = new SeWebFormAction(baseContext.driver);

            assertTrue("Submit button text is not 'Submit'", seWebFormAction.getSubmitButtonText().contains("Submit"));
            seWebFormAction.enterText("Test text")
                    .submitForm(); //Chaining possible due to enterText method returning instance of SeWebFormAction page

        });
   }
}
