package com.github.kwipawe;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features", plugin = {"pretty", "html:out.html"}, tags = "@shop")
public class Test {
}