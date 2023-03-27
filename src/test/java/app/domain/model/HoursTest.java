package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HoursTest {
    @Test
    void getActualTime() {
        Hours aux=Hours.getActualTime();
        System.out.println(aux);
        Assertions.assertNotNull(aux);
    }

    @Test
    void getListTimes () {
        List<Hours> test= Hours.sumIntervalTime(new Hours(8,00), new Hours(20,00), 30);
        System.out.println(test.size());
        for(int i=0; i<test.size(); i++) {
            System.out.println(test.get(i));

        }
    }

    @Test
    void compareToBigger() {
        Hours otherTime = new Hours(10, 30);
        Hours time = new Hours(9, 30);
        int expected = -1;

        Assertions.assertEquals(expected, time.compareTo(otherTime));

    }

    @Test
    void compareToSmaller() {
        Hours otherTime = new Hours(10, 30);
        Hours time = new Hours(9, 30);
        int expected = 1;

        Assertions.assertEquals(expected, otherTime.compareTo(time));
    }

    @Test
    void compareToEqual() {
        Hours otherTime = new Hours(10, 30);
        Hours time = new Hours(10, 30);
        int expected = 0;

        Assertions.assertEquals(expected, otherTime.compareTo(time));

    }

    @Test
    void isLaterTime() {
        Hours otherTime = new Hours(10, 30);
        Hours time = new Hours(9, 30);

        Assertions.assertFalse(time.isLaterTime(otherTime));


    }

    @Test
    void toMinutes() {
    }

}