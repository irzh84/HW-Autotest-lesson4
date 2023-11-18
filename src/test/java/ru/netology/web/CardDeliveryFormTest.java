package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {
    @Test
    void shouldPositiveTest() {
        open("http://localhost:9999/");
    }
}
