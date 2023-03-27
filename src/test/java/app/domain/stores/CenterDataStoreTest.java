package app.domain.stores;

import app.domain.model.CenterData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterDataStoreTest {

    private CenterDataStore storeTest;

    @Test
    void validate() {
        storeTest = new CenterDataStore();
        CenterData cdValid = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", "21C16-05", "Spikevax", "first");
        Assertions.assertTrue(storeTest.validate(cdValid));

        CenterData cdInvalid = new CenterData("100/5/2020", "10:05","10/05/2020", "10:05","100/5/2020", "10:05",
                "10/05/2020", "10:05","1234567", "21C16-05", "Spikevax", "first");
        Assertions.assertFalse(storeTest.validate(cdInvalid));
    }

    @Test
    void centerDataExists() {
        storeTest = new CenterDataStore();
        CenterData cd1 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", "21C16-05", "Spikevax", "first");
        CenterData cd2 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", "21C16-05", "Spikevax", "first");
        CenterData cd3 = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456788", "21C16-05", "Spikevax", "first");

        storeTest.add(cd1);
        Assertions.assertTrue(storeTest.CenterDataExists(cd2));
        Assertions.assertFalse(storeTest.CenterDataExists(cd3));
    }
}