package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {

    /** Type 1 contains a header and item separation is made using: ;
     * Type 2 doesn't contain a header and item separation is made using: ,
     */
    private final String PATH_TO_VALID_FILE1 = "src\\TestWithValidFile(Type1).csv";
    private final String PATH_TO_INVALID_FILE1 = "src\\TestWithInvalidFile(Type1).csv";
    private final String PATH_TO_VALID_FILE2 = "src\\testWithValidFile(Type2).csv";
    private final String PATH_TO_INVALID_FILE2 = "src\\testWithInvalidFile(Type2).csv";

    private final String PATH_TO_VALID_US17TEST = "src\\US17ValidTest.csv";
    private final String INVALID_PATH = "src\\teste.pdf";

    @Test
    void loadValidSNSFile() {

        FileLoader validFileLoaderType1 = new FileLoader(PATH_TO_VALID_FILE1);
        FileLoader validFileLoaderType2 = new FileLoader(PATH_TO_VALID_FILE2);

        //TYPE1
        //checks if it is possible to load a valid file
        assertTrue(validFileLoaderType1.loadFile(false));

        //TYPE2
        //checks if it is possible to load a valid file
        assertTrue(validFileLoaderType2.loadFile(false));

    }

    @Test
    void loadInvalidSNSFile() {

        FileLoader invalidFileLoaderType1 = new FileLoader(PATH_TO_INVALID_FILE1);
        FileLoader invalidFileLoaderType2 = new FileLoader(PATH_TO_INVALID_FILE2);

        //TYPE1
        //checks if it is possible to load an invalid file
        assertFalse(invalidFileLoaderType1.loadFile(false));

        //TYPE 2
        //checks if it is possible to load an invalid file
        assertFalse(invalidFileLoaderType2.loadFile(false));
    }

    @Test
    void loadValidCenterDataFile(){
        FileLoader validFileLoaderCD = new FileLoader(PATH_TO_VALID_US17TEST, true);

        //checks if it is possible to load a valid file
        assertTrue(validFileLoaderCD.loadFile(true));
    }

    @Test
    void checkFilePath() {
        FileLoader validFileLoaderType1 = new FileLoader(PATH_TO_VALID_FILE1);
        FileLoader validFileLoaderType2 = new FileLoader(PATH_TO_VALID_FILE2);

        //TYPE1
        //checks if a valid path is accepted
        assertTrue(validFileLoaderType2.checkFilePath(PATH_TO_VALID_FILE1));

        //TYPE2
        //checks if a valid path is accepted
        assertTrue(validFileLoaderType2.checkFilePath(PATH_TO_VALID_FILE2));

        //checks if an invalid path is accepted
        assertFalse(validFileLoaderType2.checkFilePath(INVALID_PATH));
    }
}