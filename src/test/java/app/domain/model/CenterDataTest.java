package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterDataTest {

    @Test
    void validateSNSnumber() {
        String invalidSNS = "123";
        String validSNS = "123456789";
        CenterData cd1 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05",invalidSNS, "21C16-05", "Spikevax", "first");
        CenterData cd2 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05",validSNS, "21C16-05", "Spikevax", "first");

        Assertions.assertFalse(cd1.validateSNSnumber());
        Assertions.assertTrue(cd2.validateSNSnumber());
    }

    @Test
    void validateCenterData() {
        CenterData cdValid = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", "21C16-05", "Spikevax", "first");
        Assertions.assertTrue(cdValid.validateCenterData());

        CenterData cdInvalid = new CenterData("100/5/2020", "10:05","10/05/2020", "10:05","100/5/2020", "10:05",
                "10/05/2020", "10:05","1234567", "21C16-05", "Spikevax", "first");
        Assertions.assertFalse(cdInvalid.validateCenterData());
    }

    @Test
    void validateLotNumber() {
        String validLotNumber = "12A12-88";
        String invalidLotNumber ="!1234-AA";
        CenterData cd1 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", validLotNumber, "Spikevax", "first");
        CenterData cd2 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", invalidLotNumber, "Spikevax", "first");
        Assertions.assertTrue(cd1.validateLotNumber());
        Assertions.assertFalse(cd2.validateLotNumber());
    }

}