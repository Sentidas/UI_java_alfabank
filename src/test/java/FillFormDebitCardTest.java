import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.DataGeneration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты на заполнение ФИО клиента при заказе дебетовой карты")
public class FillFormDebitCardTest {

    DataGeneration data = new DataGeneration();

    @BeforeEach
    void openForm() {
        Configuration.baseUrl = "https://alfabank.ru";
        open("/everyday/debit-cards/alfacard/#form");
        Configuration.holdBrowserOpen = true;
        $("h1").shouldHave(text("Дебетовая \n" +
                "Альфа‑Карта"));
    }

    @AfterEach
    void closePage() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка ввода ФИО в одно поле и разбивка на поля: Фамилия, Имя, Отчество")
    void fillFullName() {

        $("[data-test-id='caption-fullName']").scrollTo().shouldHave(text("Укажите точно как в паспорте"));
        $("[name = 'fullName']").setValue(data.lastName + " " + data.firstName + " " + data.middleName).pressEnter();
        $("[data-test-id='button']").scrollTo().$(byText("Продолжить")).click();

        $("[name=lastName]").shouldHave(value(data.lastName));
        $("[name=firstName]").shouldHave(value(data.firstName));
        $("[name=middleName]").shouldHave(value(data.middleName));
        $("[data-test-id=fio-data-warning]")
                .shouldHave(text("Проверьте, что введённые данные совпадают с паспортными"));
        $("[data-test-id=field-fio-warning-icon]").shouldBe();
    }

    @Test
    @DisplayName("Проверка ввода ФИО при отсутствии отчества")
    void NotExistsMiddleName() {

        $("[data-test-id='caption-fullName']").scrollTo().shouldHave(text("Укажите точно как в паспорте"));
        $("[name = 'fullName']").setValue(data.lastName + " " + data.firstName).pressEnter();
        $("[data-test-id='button']").scrollTo().$(byText("Продолжить")).click();
        $("[data-test-id=fio-data-warning]")
                .shouldHave(text("Проверьте и заполните недостающие поля"));
        $("[data-test-id=field-fio-warning-icon]").shouldBe();
        $("p[data-test-id=captionError-middleName]")
                .shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=cancel-button]").shouldBe();
        $("[data-test-selected=false]").scrollTo().click();

        $("[name=lastName]").shouldHave(value(data.lastName));
        $("[name=firstName]").shouldHave(value(data.firstName));
        $("[name=middleName]").shouldHave(value("-"));
        $("[data-test-id=fio-data-warning]")
                .shouldHave(text("Проверьте, что введённые данные совпадают с паспортными"));
        $("[data-test-id=success-icon-middleName]").shouldBe();
    }

    @Test
    @DisplayName("Проверка ввода ФИО без указания отчества")
    void fullFirstAndLastName() {

        $("[data-test-id='caption-fullName']").scrollTo().shouldHave(text("Укажите точно как в паспорте"));
        $("[name = 'fullName']").setValue(data.lastName + " " + data.firstName).pressEnter();
        $("[data-test-id='button']").scrollTo().$(byText("Продолжить")).click();

        $("[name=lastName]").shouldHave(value(data.lastName));
        $("[name=firstName]").shouldHave(value(data.firstName));
        $("[data-test-id=fio-data-warning]")
                .shouldHave(text("Проверьте и заполните недостающие поля"));
        $("[data-test-id=field-fio-warning-icon]").shouldBe();
        $("p[data-test-id=captionError-middleName]")
                .shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=cancel-button]").shouldBe();
    }

    @Test
    @DisplayName("Проверка поля ФИО без заполнения данными")
    void notFullName() {

        $("[data-test-id='button']").scrollTo().$(byText("Продолжить")).click();
        $("[data-test-id=field-fio-warning-icon]").shouldBe();
        $("[data-test-id=captionError-fullName]")
                .shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=error-icon-fullName]").shouldBe();
    }

    @Test
    @DisplayName("Проверка ввода ФИО без указания имени и отчества")
    void fillFullNameEn() {
        $("[data-test-id='caption-fullName']").scrollTo().shouldHave(text("Укажите точно как в паспорте"));
        $("[name = 'fullName']").setValue(data.lastName).pressEnter();
        $("[data-test-id='button']").$(byText("Продолжить")).scrollTo().click();

        $("[name=lastName]").shouldHave(value(data.lastName));
        $("[data-test-id=fio-data-warning]")
                .shouldHave(text("Проверьте и заполните недостающие поля"));
        $("[data-test-id=field-fio-warning-icon]").shouldBe();
        $("p[data-test-id=captionError-firstName]")
                .shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=error-icon-firstName]").shouldBe();
    }
}

