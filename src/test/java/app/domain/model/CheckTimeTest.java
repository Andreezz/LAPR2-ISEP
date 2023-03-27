package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTimeTest {

    @Test
    void checkTime() {

        String testCorrect = "09:05";

        Assertions.assertTrue(CheckTime.checkTime(testCorrect));

        String testWrongFormat = "9:10";

        Assertions.assertFalse(CheckTime.checkTime(testWrongFormat));

        String testWrongTime = "30:80";

        Assertions.assertFalse(CheckTime.checkTime(testWrongTime));
    }
}