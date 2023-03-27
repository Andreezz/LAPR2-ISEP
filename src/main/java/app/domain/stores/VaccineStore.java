package app.domain.stores;

import app.domain.model.NewVaccine;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class VaccineStore {
    private List<NewVaccine> NVs;
    private NewVaccine NV;

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    /**
     * creates a list of the class NewVaccine
     */
    public VaccineStore() {
        this.NVs = new ArrayList<NewVaccine>();
    }

    /**
     * generates a new vaccine and its information
     *
     * @return the newly created vaccine
     */

    public NewVaccine createNewVaccine(int id, String name, String manufacturercompany, VaccineType vacctype, List<Integer> ageGroup, List<Integer> timeInterval, List<Float> dosage) {
        return this.NV = new NewVaccine(id, name, manufacturercompany, vacctype, ageGroup, timeInterval, dosage);
    }

    /**
     * Checks for equal vaccines, meaning if the program finds an equal vaccine in the system, it shows
     */
    public boolean NewVaccineCheck(NewVaccine NV) {
        for (NewVaccine e : NVs) {
            if (e.equals(NV))
                throw new IllegalArgumentException("The vaccine you're trying to register already exists");
        }
        return false;
    }

    /**
     * clears the Vaccine list
     */
    public void clearVaccineList() {
        NVs.clear();
    }


    /**
     * Function for the store to get the necessary info for the vaccine storage
     */
    public String getNewVaccineInfo() {
        return this.NV.toString();
    }

    /**
     * Function that sorts the list in alfabetic order
     */
    public static Comparator<NewVaccine> CompareAlfabeticOrder = new Comparator<NewVaccine>() {
        @Override
        public int compare(NewVaccine o1, NewVaccine o2) {
            String nome1 = o1.getName().toUpperCase();
            String nome2 = o2.getName().toUpperCase();
            return nome1.compareTo(nome2);
        }
    };
    /**
     * Function that sorts the list by type
     */
    public static Comparator<NewVaccine> CompareTypes = new Comparator<NewVaccine>() {
        @Override
        public int compare(NewVaccine o1, NewVaccine o2) {
            String type1 = o1.getVacctype().getType().toUpperCase();
            String type2 = o2.getVacctype().getType().toUpperCase();
            return type1.compareTo(type2);
        }
    };

    /**
     * Ckecks if the list has content in it and returns based on how many elements the list has ,if the list has more then 1 it sorts the list
     * @return string
     */
    public String ListOutput() {
        if (NVs.size() == 0) {
            System.out.println("List is empty");

        } else {
            if (NVs.size() == 1) {
                System.out.println("" + NVs.get(0));
            } else {
                NVs.sort(CompareAlfabeticOrder);
                NVs.sort(CompareTypes);
                // System.out.println("alfabeticamente e por type:");
                //return "Vaccine's name :"+NV.getName() + "\n" + "Vaccine's Type:" + NV.getVacctype().getType() + "\n";


            }
        }

        return null;
    }

    /**
     * @return a String containing the information of all vaccine information inside the list by type and alfabetic order
     */

    public String getVaccineList() {
        int[] count = new int[6];
        ListOutput();
        StringBuilder NVsList = new StringBuilder();
        NVsList.append("Vaccines: \n\n");
        for (NewVaccine e : NVs) {
            if (Objects.equals(e.getVacctype().getType(), Constants.TECH_1) && count[0] == 0) {
                NVsList.append(Constants.TECH_1);

                count[0]++;
            } else if (Objects.equals(e.getVacctype().getType(), Constants.TECH_2) && count[1] == 0) {
                NVsList.append(Constants.TECH_2);

                count[1]++;

            } else if (Objects.equals(e.getVacctype().getType(), Constants.TECH_3) && count[2] == 0) {
                NVsList.append(Constants.TECH_3);

                count[2]++;

            } else if (Objects.equals(e.getVacctype().getType(), Constants.TECH_4) && count[3] == 0) {
                NVsList.append(Constants.TECH_4);

                count[3]++;

            } else if (Objects.equals(e.getVacctype().getType(), Constants.TECH_5) && count[4] == 0) {
                NVsList.append(Constants.TECH_5);

                count[4]++;

            } else if (Objects.equals(e.getVacctype().getType(), Constants.TECH_6) && count[5] == 0) {
                NVsList.append(Constants.TECH_6);

                count[5]++;

            }


            NVsList.append("\n");
            NVsList.append(e.ListByTypeAndAlfabetical());
            NVsList.append("\n");
        }
        return String.valueOf(NVsList);
    }

    public List<NewVaccine> getList() {
        return this.NVs;
    }


    /**
     * Function to save the newly registered vaccine in the system
     *
     * @param NV
     */
    public void saveNewVaccine(NewVaccine NV) {
        NVs.add(NV);
        saveList();
    }

    public NewVaccine getInstanceByName(String name){
        for(NewVaccine v : NVs){
            if(v.getName().equals(name))
                return v;
        }
        return null;
    }

    /** saves the arraylist in a file, using serialization.This contains all the vaccines that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.VACCINE_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(NVs);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Loads to the local list in the store, the information that was stored in the file, using serialization.
     */
    public void loadToLocalList(){
        try {
            this.inFile = new FileInputStream(Constants.VACCINE_FILE);
            this.input = new ObjectInputStream(inFile);
            NVs = (List<NewVaccine>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

