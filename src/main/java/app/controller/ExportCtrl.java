package app.controller;

import app.domain.model.ExportStatistics;

import java.io.IOException;

public class ExportCtrl {
    ExportStatistics export;

    public ExportCtrl(){}
    public boolean checkFileName(String fileName) {
        return export.checkFileName(fileName);
    }

    public void createExport(String fileName){
        this.export = new ExportStatistics(nameToPath(fileName));
    }

    public String nameToPath(String fileName){
        String filePath = "src\\"+fileName+".csv";
        //System.out.println(filePath);
        return filePath;
    }

    public boolean export(String date1, String date2) throws IOException {return export.exportFile(date1, date2);}
}
