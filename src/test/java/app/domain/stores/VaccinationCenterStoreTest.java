package app.domain.stores;

import app.controller.VaccinationCenterController;
import app.controller.VaccineTypeController;
import app.domain.model.*;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class VaccinationCenterStoreTest {

     private HealthCareCenter vaccinationCenterA = new HealthCareCenter( "Vacinasdoze", "a@a.pt",
             "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_1,
             5.5, 10, new Schedule("14:00", "20:00"));

     private MassVaccinationCenter vaccinationCenterB = new MassVaccinationCenter("Vacinasdoze", "a@a.pt",
             "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_2,
             5.5, 10, new Schedule("14:00", "20:00"));


     @Test
    void createVaccinationCenter() {
        VaccinationCenterStore store = new VaccinationCenterStore();

        String actual= String.valueOf(vaccinationCenterA);
       String actual2= String.valueOf(vaccinationCenterB);

        //value should be the same
        String expected = String.valueOf(store.createVaccinationCenter("Vacinasdoze", "a@a.pt",
                "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_1,
                5.5, 10, new Schedule("14:00", "20:00")));
       String expected2 = String.valueOf(store.createVaccinationCenter("Vacinasdoze", "a@a.pt",
               "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_2,
               5.5, 10, new Schedule("14:00", "20:00")));

       Assertions.assertEquals(actual, expected);
       Assertions.assertEquals(actual2, expected2);


    }

    @Test
    void ValidateVaccinationCenter() {
        VaccinationCenterStore store = new VaccinationCenterStore();

        //value should be true because values are valid
        Assertions.assertTrue(store.ValidateVaccinationCenter(vaccinationCenterA));
        Assertions.assertTrue(store.ValidateVaccinationCenter(vaccinationCenterB));
    }



    @Test
    void getVc() {
        VaccinationCenterStore store = new VaccinationCenterStore();

        String actual= String.valueOf(vaccinationCenterA);
        String actual2= String.valueOf(vaccinationCenterB);

        store.createVaccinationCenter("Vacinasdoze", "a@a.pt",
                "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_1,
                5.5, 10, new Schedule("14:00", "20:00"));
        store.saveVaccinationCenter();
        //value should be the same
        String expected = String.valueOf(store.getVc());
        Assertions.assertEquals(actual, expected);

        store.createVaccinationCenter("Vacinasdoze", "a@a.pt",
                "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_2,
                5.5, 10, new Schedule("14:00", "20:00"));
        store.saveVaccinationCenter();
        //value should be the same
        String expected2 = String.valueOf(store.getVc());
        Assertions.assertEquals(actual2, expected2);




    }
}
