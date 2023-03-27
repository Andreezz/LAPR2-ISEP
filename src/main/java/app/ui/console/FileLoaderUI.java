package app.ui.console;

import app.controller.FileLoaderController;
import app.ui.console.utils.Utils;

/**
 * UI of the UserStory: As, an administrator, load a set of SNS users from a CSV file
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class FileLoaderUI implements Runnable {

    private FileLoaderController ctrl;

    public FileLoaderUI() {ctrl = new FileLoaderController();}


    /** asks the actor for the file's path, he will be kept on a loop until he confirms the file's path
     * if the file is invalid a message will be printed warning the actor that the file was not loaded.If it is valid a success message is printed informing where the actor can find the passwords
     */
    @Override
    public void run() {
        boolean end = false;
        /*System.out.println("SNS List: ");
        ctrl.printListTest();*/
        do{
            String filePath = Utils.readLineFromConsole("Type the path of the file that you want to load (it must be a CSV file):");
            if(confirmFilePath(filePath)) {
                ctrl.createSNSFileLoader(filePath);
                if (ctrl.loadSNSFile()) {
                    System.out.println("The file was loaded successfully!");
                    System.out.println("The SNS users passwords are on the following file: UserPasswords.txt");
                }
                else{
                    System.out.println("file invalid, it was not loaded!");
                }
                end = true;
            }
        }while (!end);
    }

    /** confirms the file's path
     * @return true if the file's path is confirmed, otherwise, returns false
     */
    public boolean confirmFilePath(String filePath){
        System.out.println("Do you wish to load this file?");
        System.out.println(filePath);
        return Utils.confirm("Answer here with a yes or a no:");
    }
}
