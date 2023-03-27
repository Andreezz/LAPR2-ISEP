package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExportStatisticsTest {
    private final String PATH_FILE1 = "src\\Full_vacinate.csv";
    private final String PATH_FILE2 = "src\\Full_vacinate.csv";

    @Test
    void loadValidSNSFile() {

        FileLoader validLoader1 = new FileLoader(PATH_FILE1);
        FileLoader validLoader2 = new FileLoader(PATH_FILE2);

        //checks if it is possible to load a valid file
        assertTrue(validLoader1.loadFile(false));
        //checks if it is possible to load a valid file
        assertTrue(validLoader2.loadFile(false));

    }


    @Test
    void checkFilePath() {
        FileLoader validLoader1 = new FileLoader(PATH_FILE1);
        FileLoader validLoader2 = new FileLoader(PATH_FILE1);
        //checks if a valid path is accepted
        assertTrue(validLoader2.checkFilePath(PATH_FILE1));
        //checks if a valid path is accepted
        assertTrue(validLoader2.checkFilePath(PATH_FILE1));

        //checks if an invalid path is accepted
        assertFalse(validLoader2.checkFilePath("src\\Full_vacinate.pdf"));
    }
}