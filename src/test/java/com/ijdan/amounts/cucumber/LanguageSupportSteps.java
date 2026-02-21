package com.ijdan.amounts.cucumber;

import com.ijdan.amounts.corelogic.ports.NumberToTextConverterPort;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LanguageSupportSteps {

    @Autowired
    private NumberToTextConverterPort numberToTextConverter;

    private String languageCode;
    private String translationResponse;
    private Throwable caughtException;

    @Given("the application recognizes the language {string}")
    public void the_application_recognizes_the_language(String language) {
        this.languageCode = mapLanguageToCode(language);
    }

    @Given("the application does not yet recognize the language {string}")
    public void the_application_does_not_yet_recognize_the_language(String language) {
        this.languageCode = mapLanguageToCode(language);
    }

    @When("the user attempts to process data using the language {string}")
    public void the_user_attempts_to_process_data_using_the_language(String language) {
        caughtException = catchThrowable(() -> {
            translationResponse = numberToTextConverter.transformerLeNombreEnTexte("123", languageCode);
        });
    }

    @Then("the user should receive a successfully translated response")
    public void the_user_should_receive_a_successfully_translated_response() {
        assertThat(caughtException).isNull();
        assertThat(translationResponse).isNotNull().isNotBlank();
    }

    @Then("the user should see the error message {string}")
    public void the_user_should_see_the_error_message(String expectedError) {
        assertThat(caughtException)
                .isNotNull()
                .hasMessageContaining(expectedError);
    }

    private String mapLanguageToCode(String language) {
        // Map English feature file names to system codes commonly used in this
        // application
        switch (language.toUpperCase()) {
            case "ENGLISH":
                return "EN";
            case "FRENCH":
                return "FR";
            case "SPANISH":
                return "ES";
            case "GERMAN":
                return "DE";
            case "ITALIAN":
                return "IT";
            case "PORTUGUESE":
                return "PT";
            default:
                return language.substring(0, 2).toUpperCase();
        }
    }
}
