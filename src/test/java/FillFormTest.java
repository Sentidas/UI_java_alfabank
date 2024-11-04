import data.DataGeneration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FillFormPage;

@DisplayName("Тесты на заполнение ФИО клиента при заказе дебетовой карты")
public class FillFormTest extends TestBase {

    DataGeneration data = new DataGeneration();

    @Test
    @DisplayName("Проверка ввода ФИО в одно поле и разбивка на поля: Фамилия, Имя, Отчество")
    void fillFullName() {

        new FillFormPage().setFullFio(data.lastName, data.firstName, data.middleName)
                .clickSubmit()
                .checkResultLastName(data.lastName)
                .checkResultFirstName(data.firstName)
                .checkResultMiddleName(data.middleName)
                .checkResultDataWarning("Проверьте, что введённые данные совпадают с паспортными")
                .checkToBeIconLastName()
                .checkToBeIconFirstName()
                .checkToBeIconMiddleName();
    }

    @Test
    @DisplayName("Проверка ввода ФИО при отсутствии отчества")
    void NotExistsMiddleName() {
        new FillFormPage().setLastAndFirstName(data.lastName, data.firstName)
                .clickSubmit()
                .checkResultDataWarning("Проверьте и заполните недостающие поля")
                .checkToBeTextErrorMiddleName("Поле обязательно для заполнения")
                .checkToBeIconErrorMiddleName()
                .selectCheckBoxWithoutMiddleName()
                .checkResultMiddleName("-")
                .checkToBeIconMiddleName();
    }

    @Test
    @DisplayName("Проверка ввода ФИО без указания отчества")
    void fullFirstAndLastName() {

        new FillFormPage().setLastAndFirstName(data.firstName, data.lastName)
                .clickSubmit()
                .checkResultDataWarning("Проверьте и заполните недостающие поля")
                .checkToBeTextErrorMiddleName("Поле обязательно для заполнения")
                .checkToBeIconErrorMiddleName();
    }

    @Test
    @DisplayName("Проверка поля ФИО без заполнения данными")
    void notFullName() {

        new FillFormPage()
                .clickSubmit()
                .checkResultDataWarning("Проверьте и заполните недостающие поля")
                .checkToBeTextErrorFullName("Поле обязательно для заполнения")
                .checkToBeIconErrorFullName();
    }

    @Test
    @DisplayName("Проверка ввода ФИО без указания имени и отчества")
    void fillFullNameWithOnlyLastName() {

        new FillFormPage()
                .setFirstName(data.lastName)
                .clickSubmit()
                .checkResultDataWarning("Проверьте и заполните недостающие поля")
                .checkToBeTextErrorFirstName("Поле обязательно для заполнения")
                .checkToBeIconErrorFirstName()
                .checkToBeTextErrorMiddleName("Поле обязательно для заполнения")
                .checkToBeIconErrorMiddleName();
    }
}

