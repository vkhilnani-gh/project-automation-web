package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"definitions/steps", "definitions/hooks"},
        tags = "@sampleTest",
        plugin = {"pretty", "json:build/reports/app/cucumber.json", "html:build/reports/app/cucumber-html.html"}
)
public class TestRunner {
}
