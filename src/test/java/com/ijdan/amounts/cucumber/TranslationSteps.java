package com.ijdan.amounts.cucumber;

import com.ijdan.amounts.corelogic.ports.NumberToTextConverterPort;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TranslationSteps {

    @Autowired
    private NumberToTextConverterPort numberToTextConverter;

    private String languageCode;
    private String translationResult;

    @Given("the target translation language is {string}")
    public void the_target_translation_language_is(String language) {
        // Map common language names to system codes
        switch (language.toUpperCase()) {
            case "ENGLISH":
                this.languageCode = "EN";
                break;
            case "FRENCH":
                this.languageCode = "FR";
                break;
            case "SPANISH":
                this.languageCode = "ES";
                break;
            default:
                this.languageCode = language.substring(0, 2).toUpperCase();
        }
    }

    @Given("no target translation language is provided")
    public void no_target_translation_language_is_provided() {
        this.languageCode = null;
    }

    @When("I process the number {string}")
    public void i_process_the_number(String number) {
        translationResult = numberToTextConverter.transformerLeNombreEnTexte(number, languageCode);
    }

    @Then("the translated result should exactly match {string}")
    public void the_translated_result_should_exactly_match(String expectedText) {
        assertThat(translationResult).isEqualTo(expectedText);
    }
}
