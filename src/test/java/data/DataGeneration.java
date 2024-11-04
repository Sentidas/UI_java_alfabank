package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGeneration {

    Faker faker = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            middleName = faker.name().firstName();
}
