package org.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features   = "src/test/resources/features/api",
        glue       = "org.automation.api.steps",
        tags       = "@api",
        plugin     = {
                "pretty",
                "html:target/reports/api-report.html",
                "json:target/reports/api-report.json"
        },
        monochrome = true
)
public class ApiTestRunner {
}