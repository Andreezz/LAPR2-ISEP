package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    void getCurrentDate() {
        Date actual= Date.getCurrentDate();
        System.out.println(actual.toString());
        Assertions.assertNotNull(actual);
    }

    @Test
    void compareToSmallerDay (){
        Date date1 = new Date(10, 9, 2000);
        Date date2 = new Date(11, 9, 2000);
        int expected = -1;

        Assertions.assertEquals(expected, date1.compareTo(date2));
    }
    @Test
    void compareToGreaterDay (){
        Date date1 = new Date(10, 9, 2000);
        Date date2 = new Date(11, 9, 2000);
        int expected = 1;

        Assertions.assertEquals(expected, date2.compareTo(date1));
    }
    @Test
    void compareToEqual (){
        Date date1 = new Date(11, 9, 2000);
        Date date2 = new Date(11, 9, 2000);
        int expected = 0;

        Assertions.assertEquals(expected, date1.compareTo(date2));
    }
    @Test
    void compareToSmallerMonth (){
        Date date1 = new Date(11, 8, 2000);
        Date date2 = new Date(11, 9, 2000);
        int expected = -1;

        Assertions.assertEquals(expected, date1.compareTo(date2));
    }
    @Test
    void compareToSmallerYear (){
        Date date1 = new Date(10, 9, 2000);
        Date date2 = new Date(11, 9, 2001);
        int expected = -1;

        Assertions.assertEquals(expected, date1.compareTo(date2));
    }
    @Test
    void compareToGreaterYear (){
        Date date1 = new Date(10, 9, 2000);
        Date date2 = new Date(11, 9, 2001);
        int expected = 1;

        Assertions.assertEquals(expected, date2.compareTo(date1));
    }

}