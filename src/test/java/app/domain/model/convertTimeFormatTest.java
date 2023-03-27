package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class convertTimeFormatTest {

    @Test
    void convert() {
        //Checks if it is converted correctly
        String testConvert = ConvertTimeFormat.convert("9:12");
        String expected = "09:12";
        Assertions.assertEquals(expected, testConvert);

        //Checks if method does not convert time strings that are written in thw wrong format
        String testDontConvert = ConvertTimeFormat.convert("9|12");;
        Assertions.assertNull(testDontConvert);
    }
}