 package app.domain.stores;

import app.domain.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
class ArrivalStoreTest {
/*

    @Test
    public void filteringByVaccCentreTest() {
        SNSUserStore user = new SNSUserStore();
        SNSUser u1 = user.createSNS("ana", "female", "21/03/2000", "rua 1", "915555556", "email@hotmqi.com", "111111111", "11111111-1-AA1");
        SNSUser u2 = user.createSNS("anasois", "female", "21/04/1999", "rua 2", "915555455", "email@htmqil.com", "111111112", "11111111-1-AA2");
        SNSUser u8 = user.createSNS("anaoito", "female", "01/08/2002", "rua 9", "915550255", "emal@hotmqi.com", "111111118", "11111511-1-AA6");


        Schedule s = new Schedule("09:00", "19:00");
        VaccinationCenter vc = new MassVaccinationCenter("nome", "maail@mail.com", "914444444", "rua", "www.fff.com", 9144444, "tétano", 10.0, 1, s);
        VaccinationCenter vc2 = new MassVaccinationCenter("nammme", "mail@mail.com", "914444744", "rua 2", "www.fffkk.com", 9144454, "tétano", 10.0, 1, s);

        ArrivalStore list = new ArrivalStore();
        Arrival a1 = new Arrival(u1, vc, String.valueOf(LocalDate.now()), "12:00");
        list.addArrival(a1);
        Arrival a2 = new Arrival(u2, vc, String.valueOf(LocalDate.now()), "12:15");
        list.addArrival(a2);

        Arrival a8 = new Arrival(u8, vc2, String.valueOf(LocalDate.now()), "11:15");
        list.addArrival(a8);

        ArrivalStore actual = list.filterByVaccCentre(vc2.getName());
        ArrivalStore expected = new ArrivalStore();
        expected.addArrival(a8);

        Assertions.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void orderByArrivalTest() {
        SNSUserStore user = new SNSUserStore();
        SNSUser u1 = user.createSNS("ana", "female", "21/03/2000", "rua 1", "915555556", "email@hotmqi.com", "111111111", "11111111-1-AA1");
        SNSUser u2 = user.createSNS("anasois", "female", "21/04/1999", "rua 2", "915555455", "email@htmqil.com", "111111112", "11111111-1-AA2");
        SNSUser u3 = user.createSNS("anaee", "female", "11/03/1996", "rua 3", "915535555", "email@hoqil.com", "111111113", "11111111-1-AA3");
        SNSUser u4 = user.createSNS("anaquart", "female", "21/01/1988", "rua4 ", "915595555", "emai@hotmqil.com", "111111114", "11111111-1-AA4");
        SNSUser u5 = user.createSNS("anacinvo", "female", "20/03/2001", "rua 5", "915553555", "eail@hotmqil.com", "111111115", "11111111-1-AA5");
        SNSUser u6 = user.createSNS("anaseis", "female", "01/07/2002", "rua 6", "915555255", "emal@hotmqil.com", "111111116", "11111111-1-AA6");
        SNSUser u8 = user.createSNS("anaoito", "female", "01/08/2002", "rua 9", "915550255", "emal@hotmqi.com", "111111118", "11111511-1-AA6");


        Schedule s = new Schedule("09:00", "19:00");
        VaccinationCenter vc = new MassVaccinationCenter("nome", "maail@mail.com", "914444444", "rua", "www.fff.com", 9144444, "tétano", 10.0, 1, s);

        ArrivalStore list = new ArrivalStore();
        Arrival a1 = new Arrival(u1, vc, String.valueOf(LocalDate.now()), "12:00");
        list.addArrival(a1);
        Arrival a2 = new Arrival(u2, vc, "2022/05/19", "12:00");
        list.addArrival(a2);
        Arrival a3 = new Arrival(u3, vc, String.valueOf(LocalDate.now()), "12:15");
        list.addArrival(a3);
        Arrival a4 = new Arrival(u4, vc, String.valueOf(LocalDate.now()), "11:00");
        list.addArrival(a4);
        Arrival a5 = new Arrival(u5, vc, String.valueOf(LocalDate.now()), "13:00");
        list.addArrival(a5);
        Arrival a6 = new Arrival(u6, vc, String.valueOf(LocalDate.now()), "11:15");
        list.addArrival(a6);
        Arrival a8 = new Arrival(u8, vc, String.valueOf(LocalDate.now()), "11:15");
        list.addArrival(a8);

        ArrivalStore actual = list.orderByArrival();

        ArrivalStore expected = new ArrivalStore();
        expected.addArrival(a4);
        expected.addArrival(a6);
        expected.addArrival(a8);
        expected.addArrival(a1);
        expected.addArrival(a3);
        expected.addArrival(a5);

        Assertions.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void isEmptyTest() {
        ArrivalStore store = new ArrivalStore();
        Assertions.assertTrue(store.isEmpty());
    }

    @Test
    public void printTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SNSUser u1 = new SNSUser("ana", "female", "21/03/2000", "rua 1", "915555556", "email@hotmqi.com", "111111111", "11111111-1-AA1");
        SNSUser u2 = new SNSUser("anasois", "female", "21/04/1999", "rua 2", "915555455", "email@htmqil.com", "111111112", "11111111-1-AA2");

        Schedule s = new Schedule("09:00", "19:00");
        VaccinationCenter vc = new MassVaccinationCenter("nome", "maail@mail.com", "914444444", "rua", "www.fff.com", 9144444, "tétano", 10.0, 1, s);

        ArrivalStore list = new ArrivalStore();
        Arrival a1 = new Arrival(u1, vc, String.valueOf(LocalDate.now()), "12:00");
        list.addArrival(a1);
        Arrival a2 = new Arrival(u2, vc, "2022/05/19", "12:00");
        list.addArrival(a2);

        list.print();

        String expected = "Date = 2022/05/19 | time= 12:00 | user= 111111112 | vaccCenter= nome\nDate = 2022-05-29 | time= 12:00 | user= 111111111 | vaccCenter= nome\n";
        Assertions.assertEquals(expected, outContent.toString());
    }

 */
}