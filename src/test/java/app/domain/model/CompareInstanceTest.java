package app.domain.model;

import app.domain.model.DateTime.ArrivalDateTime;
import app.domain.model.DateTime.DateTime;
import app.domain.model.DateTime.LeavingDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompareInstanceTest {

    @Test
    void testComparator(){
        //ARRIVAL TEST

        ArrivalDateTime dateTime1 = new ArrivalDateTime("10/10/2020", "10:05");
        ArrivalDateTime dateTime2 = new ArrivalDateTime("10/10/2020", "12:05");

        int test1 = CompareInstance.compareDateTime.compare(dateTime1, dateTime2);

        int test2 = CompareInstance.compareDateTime.compare(dateTime2, dateTime1);

        ArrivalDateTime dateTime3 = new ArrivalDateTime("12/10/2020", "10:05");

        int test3 = CompareInstance.compareDateTime.compare(dateTime3, dateTime1);

        //System.out.println(dateTime1.toString((ArrivalDateTime) dateTime1));
        //System.out.println(dateTime2);
        //System.out.println(dateTime3);


        Assertions.assertTrue(test1 < 0);

        Assertions.assertTrue(test2 > 0);

        Assertions.assertTrue(test3 > 0);

        //LEAVE TEST

        LeavingDateTime dateTime4 = new LeavingDateTime("12/10/2020", "08:05");
        LeavingDateTime dateTime5 = new LeavingDateTime("12/10/2020", "09:05");
        LeavingDateTime dateTime6 = new LeavingDateTime("14/10/2020", "10:05");

        int test4 = CompareInstance.compareDateTime.compare(dateTime4, dateTime5);

        int test5 = CompareInstance.compareDateTime.compare(dateTime5, dateTime4);

        int test6 = CompareInstance.compareDateTime.compare(dateTime6, dateTime4);



        Assertions.assertTrue(test4 < 0);

        Assertions.assertTrue(test5 > 0);

        Assertions.assertTrue(test6 > 0);
    }
}