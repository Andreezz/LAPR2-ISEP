package app.domain.model;

import app.controller.CenterDataController;
import app.controller.FileLoaderController;
import app.domain.shared.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;

/**
 * Main class of the UserStory: As, an administrator, load a set of SNS users from a CSV file
 * Also serves US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class FileLoader {

    private File SNSUserfile;
    private Scanner sc;
    private FileLoaderController ctrl;

    private CenterDataController centerCtrl;
    private List<SNSUser> fileUsers;

    private File centerDataFile;

    private List<CenterData> centerDataList;

    /**
     * Creates: an instance of the class FileLoaderController, a list of SNS Users, a File and a file Scanner
     * @param filePath is the path of the file, this is checked for errors if this is correct an intance of the File is creted with the specified path
     */
    public FileLoader(String filePath) {
        try {
            if (checkFilePath(filePath)) {
                ctrl = new FileLoaderController();
                SNSUserfile = new File(filePath);
                fileUsers = new ArrayList<>();
                sc = new Scanner(SNSUserfile);
            } else {
                System.out.println("File format is wrong!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public FileLoader(String filePath, boolean centerConstructor){
        try {
            if (checkFilePath(filePath)) {
                ctrl = new FileLoaderController();
                centerCtrl = new CenterDataController();
                centerDataFile = new File(filePath);
                centerDataList = new ArrayList<>();
                sc = new Scanner(centerDataFile);
            } else {
                throw new IllegalArgumentException("File is in the wrong format!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Determines the file type of the CSV file and loads the file, adding them to a list that will be forwarded to the controller
     * @return true if the file is valid, otherwise, returns false
     */
    public boolean loadFile(boolean center) {
        boolean firstLineShouldBeLoaded = false;
        String line, firstLine = null;
        String delimiter = null;
        try {
            if(!center) {
                if (sc.hasNextLine()) {
                    firstLine = sc.nextLine();
                    if (firstLine.contains(",")) {
                        delimiter = ",";
                        firstLineShouldBeLoaded = true;
                    } else {
                        if (firstLine.contains(";")) {
                            delimiter = ";";
                        }
                    }
                }
            }
            else{
                delimiter = ";";
                firstLine = sc.nextLine();
            }
            if(firstLineShouldBeLoaded)
                loadSNSUser(delimiter, firstLine);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if(!center)
                    loadSNSUser(delimiter, line);
                else
                    loadCenterDataLine(delimiter, line);
            }
            sc.close();
            if(!center)
                ctrl.loadSNSUserSet(fileUsers);
            else
                centerCtrl.loadCenterData(centerDataList);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks a file's path
     * @param filePath a String that represents the file's path
     * @return true if the file is a csv type and the file's path is not blank, otherwise, returns false
     */
    public boolean checkFilePath(String filePath) {
        try {
            String check = FilenameUtils.getExtension(filePath);
            if (check.equals("csv")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /** Loads a single SNS user if this is valid by adding it to a list of users
     * @param delimiter is the delimiter used to separate the file's items
     * @param line it is a line of the file
     */
    public void loadSNSUser(String delimiter, String line){
        String[] userAttributes;
        userAttributes = line.split(delimiter);
        //System.out.println("LINE:"+line);
        //System.out.println("DELIMITER:"+delimiter);
        if (userAttributes[0].equals(line)) {
            throw new IllegalArgumentException("File's content is in the wrong format!");
        }
        if (userAttributes.length == Constants.SNS_USER_ATTRIBUTES) {
            try {
                SNSUser user = ctrl.getUser(userAttributes[0], userAttributes[1], userAttributes[2], userAttributes[3], userAttributes[4], userAttributes[5], userAttributes[6], userAttributes[7]);
                if (!ctrl.fileUserExists(user)) {
                    if (ctrl.validateFileUser(user)) {
                        fileUsers.add(user);
                    }
                }
            } catch (Exception e){
                System.out.println("A user was not loaded because it was invalid");
            }
        }
        else
            throw new IllegalArgumentException("All the SNS user attributes must be in each line of the file!\nPlease note that there are 8 attributes!\nAddresses must not contain commas or semicolons!");
    }

    /** Loads a single CenterData instance, if this is valid, by adding it to a list of center data
     * @param delimiter is the delimiter used to separate the file's items
     * @param line it is a line of the file
     */
    public void loadCenterDataLine(String delimiter, String line){
        String [] centerDataAttributes;
        centerDataAttributes = line.split(delimiter);
        /*System.out.println("LINE:"+line);
        System.out.println("DELIMITER:"+delimiter);*/
        if (centerDataAttributes[0].equals(line)) {
            throw new IllegalArgumentException("File's content must be separated by semicolons!");
        }
        if(centerDataAttributes.length == Constants.CENTER_DATA_ATTRIBUTES){
            String[] arr;
            String[] sch;
            String[] leave;
            String[] admin;
            sch = centerDataAttributes[4].split("\\s+");
            arr = centerDataAttributes[5].split("\\s+");
            admin = centerDataAttributes[6].split("\\s+");
            leave = centerDataAttributes[7].split("\\s+");
            /*for(int i = 0; i < 2; i++){
                System.out.println("ARRIVAL :"+arr[i]);
                System.out.println("SCHEDULE:"+sch[i]);
                System.out.println("LEAVE:"+leave[i]);
                System.out.println("ADMIN:"+admin[i]);
            }*/
            CenterData cd = centerCtrl.getCD(ConvertDateFormat.convert(arr[0]), ConvertTimeFormat.convert(arr[1]), ConvertDateFormat.convert(sch[0]), ConvertTimeFormat.convert(sch[1]), ConvertDateFormat.convert(admin[0]), ConvertTimeFormat.convert(admin[1]),
                    ConvertDateFormat.convert(leave[0]), ConvertTimeFormat.convert(leave[1]), centerDataAttributes[0], centerDataAttributes[3], centerDataAttributes[1], centerDataAttributes[2]);
            //checks if the SNS users are already registered
            if(ctrl.checkSNSnumExists(cd.getSNSnum())){
                if(centerCtrl.validateCenterData(cd) && !centerCtrl.centerDataLineExists(cd)){
                    centerDataList.add(cd);
                }
            }
        }
        else throw new IllegalArgumentException("All the center data attributes must be in each line of the file!");
    }
}
