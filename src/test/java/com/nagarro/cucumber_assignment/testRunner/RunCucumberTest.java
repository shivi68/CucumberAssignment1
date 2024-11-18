package com.nagarro.cucumber_assignment.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.nagarro.cucumber_assignment",
        tags = "@HomePage or @SearchPage or @LoginPage",
        plugin = {"pretty", "html:target/cucumber-reports.html",
                  "json:target/cucumber-reports/cucumber.json" }
)

public class RunCucumberTest extends AbstractTestNGCucumberTests{

}
