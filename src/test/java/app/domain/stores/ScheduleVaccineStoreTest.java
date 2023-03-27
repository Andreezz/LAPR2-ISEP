package app.domain.stores;

import app.domain.model.ScheduleVaccine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVaccineStoreTest {
    private ScheduleVaccine SchedulevaccineA = new ScheduleVaccine("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");

    @Test
    void create() {
        ScheduleVaccineStore sv = new ScheduleVaccineStore();
        String actual= String.valueOf(SchedulevaccineA);
        String expected = String.valueOf(sv.create("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas"));
        Assertions.assertEquals(actual, expected);
    }
    @Test
    void getSchedulevaccine(){
        ScheduleVaccineStore sv = new ScheduleVaccineStore();
        ScheduleVaccine SchedulevaccineA = new ScheduleVaccine("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");
        String expected1 = String.valueOf(SchedulevaccineA);
        sv.create("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");
        sv.saveNewScheduleVaccine(SchedulevaccineA);
        String expected = String.valueOf(sv.getSchedulevaccine());
        Assertions.assertEquals(expected, expected1);
    }

}