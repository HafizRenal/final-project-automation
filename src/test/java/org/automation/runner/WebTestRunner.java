package org.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features   = "src/test/resources/features/web",
        glue       = "org.automation.web.steps",
        tags       = "@web",
        plugin     = {
                "pretty",
                "html:target/reports/web-report.html",
                "json:target/reports/web-report.json"
        },
        monochrome = true
)
public class WebTestRunner {
}