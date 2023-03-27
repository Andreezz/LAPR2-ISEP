package app.domain.model;

import app.domain.model.DateTime.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExportStatistics {

    private String filePath;

    private File fileExport;

    private File fileRecords;

    public ExportStatistics(String filePath) {
        this.fileExport = new File(filePath);
        this.fileRecords = new File("DailyRecord.txt");
    }

    public boolean checkFileName(String fileName) {
        File file = new File(fileName);
        boolean created = false;
        try {
            created = file.createNewFile();
            file.delete();
            return created;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean exportFile(String date1, String date2) throws IOException {
        Scanner scanRecords = new Scanner(fileRecords);
        FileWriter export = new FileWriter(fileExport);
        DateTime minDate = new DateTime(date1);
        DateTime maxDate = new DateTime(date2);
        while(scanRecords.hasNextLine()){
            String line = scanRecords.nextLine();
            String[] lineParts = line.split(",");
            String dateExport = lineParts[0];
            DateTime dateCheckInterval = new DateTime(dateExport);
            String VCname = lineParts[1].trim();
            String numExport = lineParts[2].trim();
            if((CompareInstance.compareDateDays.compare(minDate, dateCheckInterval) <= 0) && (CompareInstance.compareDateDays.compare(maxDate, dateCheckInterval) >= 0)){
                export.write(dateExport+";"+numExport+"\n");
            }
        }
        export.close();
        return true;
    }
}




