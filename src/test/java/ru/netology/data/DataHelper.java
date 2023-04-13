package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class DataHelper {
    private static Faker faker = new Faker();

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;

    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String randomLogin() {
        return faker.name().username();
    }

    private static String randomPass() {
        return faker.internet().password();
    }

    public static AuthInfo randomUser() {
        return new AuthInfo(randomLogin(), randomPass());
    }


    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("#####"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;

    }
}