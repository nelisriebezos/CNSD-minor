package com.nelis.cnsd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        plugin = {"pretty", "html:target/cucumber"}
)
public class CustomerCucumberTest {
}
