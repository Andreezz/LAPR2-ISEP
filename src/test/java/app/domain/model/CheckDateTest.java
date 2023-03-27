package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckDateTest {

    @Test
    void checkDate() {
        String testCorrect = "03/10/2022";

        Assertions.assertTrue(CheckDate.checkDate(testCorrect));

        String testWrongFormat = "5/30/2022";

        Assertions.assertFalse(CheckDate.checkDate(testWrongFormat));

        String testWrongDate = "50/35/2022";

        Assertions.assertFalse(CheckDate.checkDate(testWrongDate));
    }
}