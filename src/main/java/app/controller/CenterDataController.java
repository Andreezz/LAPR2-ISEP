package app.controller;

import app.domain.model.*;
import app.domain.stores.CenterDataStore;
import app.properties.PropertiesCache;
import java.util.List;

public class CenterDataController {

    private Company comp;
    private FileLoader loader;
    private CenterDataStore store;

    private PropertiesCache configFile;

    public CenterDataController(){
        this.comp = App.getInstance().getCompany();
        this.store = comp.getCenterDataStore();
        this.configFile = comp.getConfigFile();
    }

    public void createCenterDataFileLoader(String path){this.loader = new FileLoader(path, true);}

    /** @return an instance of the CenterData class
     */
    public CenterData getCD (String arrDate, String arrTime, String schDate, String schTime, String adminDate, String adminTime, String leaveDate, String leaveTime, String SNSnum, String lot, String vacName, String currentDose){
        return this.store.create(arrDate, arrTime, schDate, schTime, adminDate, adminTime, leaveDate, leaveTime, SNSnum, lot, vacName, currentDose);
    }

    /** Checks if an equal center data line was already registered.
     * @param cd to be checked.
     * @return true if the line exists in the list, otherwise, returns false.
     */
    public boolean centerDataLineExists(CenterData cd){return this.store.CenterDataExists(cd);}

    /** Checks if a CenterData instance is valid
     * @param cd to be checked
     * @return true if valid, otherwise, returns false.
     */
    public boolean validateCenterData(CenterData cd){return this.store.validate(cd);}

    /** Loads a CenterData CSV file
     * @return the result of the method of the same name from the class FileLoader
     */
    public boolean loadCenterDataFile(){return loader.loadFile(true);}

    public void loadCenterData (List<CenterData> centerDataList){
        for(CenterData cd : centerDataList){
            if(!this.store.CenterDataExists(cd))
                this.store.add(cd);
        }
        this.store.saveList();
    }

    /** sorts the list in the CenterDataStore using the SelectionSort algorithm
     */
    public void selectionSort(int order, int type){
        boolean arrival, ascending;
        arrival = type == 1;
        ascending = order == 1;
        //System.out.println("Arrival:"+arrival);
        //System.out.println("Ascending:"+ascending);
        SelectionSort.sort(store.getAll(), arrival, ascending);
    }

    /** sorts the list in the CenterDataStore using the PancakeSort algorithm
     */
    public void pancakeSort(int order, int type){
        boolean arrival, ascending;
        arrival = type == 1;
        ascending = order == 1;
        PancakeSort.sort(store.getAll(), arrival, ascending);
    }

    /** @return a string that contains all the elements of the CenterData list, sorted as specified by the configFile
     */
    public String getSortedList(int order, int type){
        String algorithm = configFile.getProperty("algorithm17");
        if(algorithm.equals("S")) {
            //System.out.println("Selection");
            selectionSort(order, type);
        }
        if (algorithm.equals("P")) {
            //System.out.println("Pancake");
            pancakeSort(order, type);
        }
        StringBuilder sortedList = new StringBuilder();
        for(CenterData cd : store.getAll()){
            sortedList.append("SNS User's name:"+comp.getSNSUserStore().getBySNSNum(cd.getSNSnum()).getName()+"\n");
            sortedList.append("Vaccine's Description:"+comp.getVaccineStore().getInstanceByName(cd.getVacName()).getVacctype().getTech()+"\n\n");
            sortedList.append(cd.toString());
        }
        return String.valueOf(sortedList);
    }

    public List<CenterData> getList(){
        return this.store.getAll();
    }

    public boolean isListEmpty(){return this.store.isEmpty();}
}
