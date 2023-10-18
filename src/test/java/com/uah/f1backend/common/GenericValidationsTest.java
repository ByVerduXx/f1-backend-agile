package com.uah.f1backend.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.uah.f1backend.common.GenericValidations.isValidTwitter;
import static com.uah.f1backend.common.GenericValidations.isValidUrl;

public class GenericValidationsTest {

    @Test
    void isValidUrlTest(){
        Assertions.assertAll(
                "Image URL tests",
                // Casos verdaderos
                () -> Assertions.assertTrue(isValidUrl("https://www.example.com/image.jpg")), // https
                () -> Assertions.assertTrue(isValidUrl("http://example.com/image.png")), // http
                () -> Assertions.assertTrue(isValidUrl("www.example.com/image.gif")), // www
                () -> Assertions.assertTrue(isValidUrl("./image.jpg")), // relative path
                () -> Assertions.assertTrue(isValidUrl("../image.png")), // parent relative path
                // Casos falsos
                () -> Assertions.assertFalse(isValidUrl("http://example.com/imagepng")), // wrong extension
                () -> Assertions.assertFalse(isValidUrl("www.example/image..jpg")), // wrong extension
                () -> Assertions.assertFalse(isValidUrl("./.jpg")),
                () -> Assertions.assertFalse(isValidUrl("http:/malformed.com/image.jpg"))
        );
    }

    @Test
    void isValidTwitterTest(){
        Assertions.assertAll(
                "Twitter accounts tests",
                () -> Assertions.assertTrue(isValidTwitter("@twitter")), // Common Twitter account
                () -> Assertions.assertFalse(isValidTwitter("twitter")), // Needs @
                () -> Assertions.assertTrue(isValidTwitter("@TwiTTer")), // Can be mixed case
                () -> Assertions.assertFalse(isValidTwitter("")), // Can't be empty
                () -> Assertions.assertFalse(isValidTwitter("@")), // Can't be only @
                () -> Assertions.assertTrue(isValidTwitter("@t")), // Can be 1 to 15 characters
                () -> Assertions.assertTrue(isValidTwitter("@123456789012345")),
                () -> Assertions.assertFalse(isValidTwitter("@1234567890123456")) // Can't be more than 15 chars
        );
    }
}
