package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void getId() {
        String expected = "00051";
        Assertions.assertEquals(expected, IdGenerator.getId(50));
    }
}