package app.domain.model;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class sendPasswordNotification {

    private File passFile;
    private FileWriter writePass;

    public static void sendPassword (List<String> names, List<String> emails, List<String> passwords) {
        try{
            File passFile = new File("src\\UserPasswords.txt");
            FileWriter writePass = new FileWriter(passFile);
            for(int i = 0; i < names.size(); i++){
                writePass.write("USER NAME:"+names.get(i)+"\nRegistered email is "+emails.get(i)+"\npassword is "+passwords.get(i)+"\n");
            }
            writePass.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
