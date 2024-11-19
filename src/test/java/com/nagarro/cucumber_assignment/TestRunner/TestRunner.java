package com.nagarro.cucumber_assignment.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", 
        glue = { 
                "com.nagarro.cucumber_assignment.hooks",
                "com.nagarro.cucumber_assignment.pages", 
                "com.nagarro.cucumber_assignment.stepDefinitions",
                "com.nagarro.cucumber_assignment.utilities" 
        }, 
        tags = "@HomePage or @SearchPage or @LoginPage", 
        plugin = {
                "pretty", 
                "html:target/cucumber-reports.html", 
                "json:target/cucumber-reports/cucumber.json" 
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
