package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class convertDateFormatTest {

    @Test
    void convert() {
        //Checks if it is converted correctly
        String testConvert = ConvertDateFormat.convert("5/30/2022");
        String expected = "30/05/2022";
        Assertions.assertEquals(expected, testConvert);

        //Checks if method does not convert date strings that are written in the wrong format
        String testDontConvert = ConvertDateFormat.convert("5|30|2022");;
        Assertions.assertNull(testDontConvert);
    }
}