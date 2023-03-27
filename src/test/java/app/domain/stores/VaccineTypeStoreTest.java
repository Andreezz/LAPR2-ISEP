package app.domain.stores;

import app.controller.VaccineTypeController;
import app.domain.model.Employee;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeStoreTest {

    @Test
    void createVaccineType() {
        VaccineTypeStore store = new VaccineTypeStore();
        VaccineType vaccineTypeA =new VaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        String expected1= String.valueOf(vaccineTypeA);
        //value should be the same
       String expected = String.valueOf(store.createVaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1));
        Assertions.assertEquals(expected1, expected);

    }

    @Test
    void validateVaccineType() {
        VaccineTypeStore store = new VaccineTypeStore();
        VaccineType vaccineTypeA =new VaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        //value should be true because values are valid
        Assertions.assertTrue(store.ValidateVaccineType(vaccineTypeA));
    }



    @Test
    void getVt() {
        VaccineTypeStore store = new VaccineTypeStore();
        VaccineType vaccineTypeA =new VaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        String expected1= String.valueOf(vaccineTypeA);
        store.createVaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        store.saveVaccineType();
        //value should be the same
        String expected = String.valueOf(store.getVt());
        Assertions.assertEquals(expected1, expected);


    }
}