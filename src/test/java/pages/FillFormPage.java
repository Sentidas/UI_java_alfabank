package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FillFormPage {

    private final SelenideElement fullNameInput = $("[name = 'fullName']"),
            buttonSubmit = $("[data-test-id='button']").scrollTo().$(byText("Продолжить")),
            firstNameInput = $("[name=firstName]"),
            lastNameInput = $("[name=lastName]"),
            middleNameInput = $("[name=middleName]"),
            fioDataWarning = $("[data-test-id=fio-data-warning]"),
            successIconLastName = $("[data-test-id=success-icon-lastName]"),
            successIconFirstName = $("[data-test-id=success-icon-firstName]"),
            successIconMiddleName = $("[data-test-id=success-icon-middleName]"),
            errorIconMiddleName = $("[data-test-id=error-icon-middleName]"),
            errorIconLastName = $("[data-test-id=error-icon-lastName]"),
            errorIconFirstName = $("[data-test-id=error-icon-firstName]"),
            errorIconFullName = $("[data-test-id=error-icon-fullName]"),
            errorCaptionMiddleName = $("[data-test-id=captionError-middleName]"),
            errorCaptionFullName = $("[data-test-id=captionError-fullName]"),
            checkBoxWithOutMiddleName = $("[data-test-selected=false]"), errorCaptionFirstName = $("[data-test-id=captionError-firstName]"), errorCaptionLastName = $("[data-test-id=captionError-lastName]");


    public void openPage() {
        open("https://alfabank.ru/everyday/debit-cards/alfacard/#form");
        $("h1").shouldHave(text("Дебетовая \n" +
                "Альфа‑Карта"));
    }

    public FillFormPage setFullFio(String lastName, String firstName, String middleName) {
        fullNameInput.setValue(lastName + " " + firstName + " " + middleName);
        return this;
    }

    public FillFormPage setLastAndFirstName(String lastName, String firstName) {
        fullNameInput.setValue(lastName + " " + firstName);
        return this;
    }

    public FillFormPage setFirstName(String firstName) {
        fullNameInput.setValue(firstName);
        return this;
    }

    public FillFormPage clickSubmit() {
        buttonSubmit.click();
        return this;
    }

    public FillFormPage checkResultLastName(String lastName) {
        lastNameInput.shouldHave(value(lastName));
        return this;
    }

    public FillFormPage checkResultFirstName(String firstName) {
        firstNameInput.shouldHave(value(firstName));
        return this;
    }

    public FillFormPage checkResultMiddleName(String middleName) {
        middleNameInput.shouldHave(value(middleName));
        return this;
    }

    public FillFormPage checkResultDataWarning(String textWarning) {
        fioDataWarning.shouldHave(text(textWarning));
        return this;
    }

    public FillFormPage checkToBeIconLastName() {
        successIconLastName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconFirstName() {
        successIconFirstName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconMiddleName() {
        successIconMiddleName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconErrorMiddleName() {
        errorIconMiddleName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconErrorFirstName() {
        errorIconFirstName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconErrorLastName() {
        errorIconLastName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeIconErrorFullName() {
        errorCaptionFullName.shouldBe();
        return this;
    }

    public FillFormPage checkToBeTextErrorMiddleName(String textError) {
        errorCaptionMiddleName.shouldHave(text(textError));
        return this;
    }

    public FillFormPage checkToBeTextErrorFirstName(String textError) {
        errorCaptionFirstName.shouldHave(text(textError));
        return this;
    }

    public FillFormPage checkToBeTextErrorFullName(String textError) {
        errorCaptionFullName.shouldHave(text(textError));
        return this;
    }


    public FillFormPage selectCheckBoxWithoutMiddleName() {
        checkBoxWithOutMiddleName.click();
        return this;
    }
}
