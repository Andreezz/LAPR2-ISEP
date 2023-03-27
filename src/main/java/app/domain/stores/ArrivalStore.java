package app.domain.stores;

import app.domain.model.Arrival;
import app.domain.model.Hours;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Store of the UserStory: Register arrival of SNS user
 *
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class ArrivalStore {

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;
    private Set<Arrival> store = new HashSet<>();

    private String date;

    public ArrivalStore() {
    }

    public boolean addArrival(Arrival arrival) {
        return arrival != null && !this.exists(arrival) ? this.store.add(arrival) : false;
    }

    public Arrival createArrival(SNSUser snsNum, VaccinationCenter centerName, String date, String time) {
        return new Arrival(snsNum, centerName, date, time);
    }

    public boolean validateArrival(Arrival arrival) {
        try {
            return arrival.checkTimeFormat(arrival.getTime()) && !checkDuplicates(arrival);
        } catch (Exception e) {
            System.err.println("Error 400 : Arrival data failed validation!");
        }
        throw new IllegalArgumentException("ducks");
    }

    public boolean checkDuplicates(Arrival arrival) {
        for (Arrival i : store) {
            if (arrival.hasSNSnumber(i.getUser()) && arrival.hasDate(i.getDate())) {
                return true;
            }
        }
        return false;
    }

    public boolean saveArrival(Arrival arrival) {
        if (validateArrival(arrival)) {
            addArrival(arrival);
            saveList();
            return true;
        }
        return false;
    }

    public boolean exists(Arrival arrival) {
        return this.store.contains(arrival);
    }

    /**
     * @param vc Vaccination Centre the Nurse logged in works at
     * @return list of Arrivals in the defined vaccination centre
     */
    public ArrivalStore filterByVaccCentre(String vc) {
        ArrivalStore list = new ArrivalStore();
        for (Arrival arr : store) {
            if (Objects.equals(arr.getVaccCenter().getName(), vc)) {
                list.addArrival(arr);
            }
        }
        return list;
    }

    /**
     * @return the list ordered by time of arrival
     */
    public ArrivalStore orderByArrival() {
        List<Arrival> list = new ArrayList<>(this.store);
        list.sort(Comparator.comparing(Arrival::getTime));
        ArrivalStore ret = new ArrivalStore();
        for (Arrival arr : list) {
            if (Objects.equals(arr.getDate(), String.valueOf(LocalDate.now()))) {
                ret.addArrival(arr);
            }
        }
        return ret;
    }

    /**
     * @return if the ArrivalStore if empty or not
     */
    public boolean isEmpty() {
        return this.store.size() == 0;
    }

    /**
     * Prints the list
     */
    public void print() {
        int index = 0;
        List<Arrival> list = new ArrayList<>(this.store);
        for (Arrival a : list) {
            System.out.println(index + ". " + a.toString());
            index++;
        }
    }

    /**
     * saves the arraylist in a file, using serialization.This contains all the arrivals that were registered.
     */
    public void saveList() {
        try {
            this.outFile = new FileOutputStream(Constants.ARRIVAL_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(store);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads to the local list in the store, the information that was stored in the file, using serialization.
     */
    public void loadToLocalList() {
        try {
            this.inFile = new FileInputStream(Constants.ARRIVAL_FILE);
            this.input = new ObjectInputStream(inFile);
            store = (Set<Arrival>) input.readObject();
            this.input.close();
            this.inFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUserIDByIndex(int index) {
        List<Arrival> list = new ArrayList<>(store);
        return list.get(index).getUser().getSNSNumber();
    }

    public void removeFromWaitList(String user) {
        List<Arrival> list = new ArrayList<>(store);
        for (Arrival arr : list) {
            if (Objects.equals(arr.getUser().getSNSNumber(), user))
                store.remove(arr);
        }
    }

    public Set<Arrival> getAll() {
        return this.store;
    }

    public static int getYear(String date) {
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        return date1.getYear();
    }


    public static int getMonths(String date) {
        LocalDate date1 = LocalDate.parse(date,DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        return date1.getMonthValue();
    }


    public static int getDay(String date) {

        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern ("yyyy-MM-dd"));

        return date1.getDayOfMonth();
    }


    public void countArrivals(app.domain.model.Date date) throws IOException {
        Map<String, Long> vaccinated = new HashMap<>();
        for (Arrival a : store) {
            //app.domain.model.Date date1 = new app.domain.model.Date(19,06,2022);
           String date2 = a.getDate();
          /*  String[] datepart = date2.split("-");
            int days =Integer.parseInt(datepart[2]) ;
            int month =Integer.parseInt(datepart[1]);
            int year =Integer.parseInt(datepart[0]);*/
            int days = Character.getNumericValue(date2.charAt(8)+date2.charAt(9));
            int month = Character.getNumericValue(date2.charAt(5)+date2.charAt(6));
            int year = Character.getNumericValue(date2.charAt(0)+date2.charAt(1)+date2.charAt(2)+date2.charAt(3));

            app.domain.model.Date date1 = new app.domain.model.Date(days,month,year);
            if (date1.getDay() != date.getDay() &&
                    date1.getMonth() != date.getMonth() &&
                    date1.getYear() != date.getYear())
                continue;
            String center = a.getVaccCenter().getName();
            long count = vaccinated.containsKey(center) ? vaccinated.get(center) : 0;
            vaccinated.put(center, count + 1);

        }

        PrintWriter writer = null;
        {
            try {
                writer = new PrintWriter(new FileOutputStream(new File("DailyRecord.txt"), true));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the fileName: " + e.getMessage());
            }
            for (Map.Entry<String, Long> centers : vaccinated.entrySet()) {
                Objects.requireNonNull(writer).println(date + ", " + centers.getKey() + ", " + centers.getValue());
            }

            if (writer != null) {
                writer.close();
            }

        }

    }


    public void executeTask(app.domain.model.Date date, Hours time) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = dateFormat.parse(date.toString() + " " + time.toString());


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    countArrivals(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, date1, 1000 * 3600 * 24);

    }


}