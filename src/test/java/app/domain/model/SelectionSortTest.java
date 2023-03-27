package app.domain.model;

import app.domain.model.DateTime.ArrivalDateTime;
import app.domain.model.DateTime.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void sort() {

        // Arrival: 10/05/2020 10:05 Leaving Time: 12/05/2020 12:05
        CenterData cd1 = new CenterData("10/05/2020", "10:05", "10/05/2020", "10:05", "10/05/2020", "10:05",
                "12/05/2020", "12:05", "123456781", "21C16-05", "Spikevax", "first");
        // Arrival: 10/05/2020 12:00 Leaving Time: 12/05/2020 13:05
        CenterData cd2 = new CenterData("10/05/2020", "12:00", "10/05/2020", "12:00", "10/05/2020", "12:00",
                "12/05/2020", "13:05", "123456782", "21C16-05", "Spikevax", "first");
        // Arrival: 11/05/2020 11:05 Leaving Time: 13/05/2020 11:05
        CenterData cd3 = new CenterData("11/05/2020", "11:05", "11/05/2020", "11:05", "11/05/2020", "11:05",
                "13/05/2020", "11:05", "123456783", "21C16-05", "Spikevax", "first");

        ArrayList<CenterData> testArrivalSort = new ArrayList<>();
        testArrivalSort.add(cd2);
        testArrivalSort.add(cd1);
        testArrivalSort.add(cd3);
        //Sorting by ascending arrival time using selection Sort algorithm
        SelectionSort.sort(testArrivalSort, true, true);

        assertEquals("10:05",testArrivalSort.get(0).getArr().getTime());
        assertEquals("12:00",testArrivalSort.get(1).getArr().getTime());
        assertEquals("11:05",testArrivalSort.get(2).getArr().getTime());

        //Sorting by descending arrival time using selection Sort algorithm
        SelectionSort.sort(testArrivalSort, true, false);

        assertEquals("10:05",testArrivalSort.get(2).getArr().getTime());
        assertEquals("12:00",testArrivalSort.get(1).getArr().getTime());
        assertEquals("11:05",testArrivalSort.get(0).getArr().getTime());

        ArrayList<CenterData> testLeaveSort = new ArrayList<>();
        testLeaveSort.add(cd2);
        testLeaveSort.add(cd1);
        testLeaveSort.add(cd3);

        //Sorting by ascending leaving time using selection Sort algorithm
        SelectionSort.sort(testLeaveSort, false, true);
        assertEquals("12:05",testLeaveSort.get(0).getLeave().getTime());
        assertEquals("13:05",testLeaveSort.get(1).getLeave().getTime());
        assertEquals("11:05",testLeaveSort.get(2).getLeave().getTime());

        //Sorting by descending leaving time using selection Sort algorithm
        SelectionSort.sort(testLeaveSort, false, false);
        assertEquals("12:05",testLeaveSort.get(2).getLeave().getTime());
        assertEquals("13:05",testLeaveSort.get(1).getLeave().getTime());
        assertEquals("11:05",testLeaveSort.get(0).getLeave().getTime());

        /*System.out.println("PRINT LIST");
        for(CenterData CD: testArrivalSort){
            System.out.println(CD.toString());
        }*/
    }

}
