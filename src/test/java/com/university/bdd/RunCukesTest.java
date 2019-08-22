package com.university.bdd;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",
                 glue = {"com.university.bdd.steps", "com.university.bdd.hooks"},
                 features = "src/test/resources/bdd/features")
public class RunCukesTest {
}
