package com.architech.user_registration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.architech.user_registration.steps", plugin = "pretty")
@ActiveProfiles("test")
public class CucumberIntegrationTest {
}
