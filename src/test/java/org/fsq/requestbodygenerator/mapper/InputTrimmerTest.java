package org.fsq.requestbodygenerator.mapper;

import org.fsq.requestbodygenerator.data.Types;
import org.fsq.requestbodygenerator.exception.PojoDataInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InputTrimmerTest {

    @Autowired
    private InputTrimmer trimmer;

    @Test
    public void testGivenNullOrBlankInputThrowsIllegalArgumentException() {
        String in = null;

        PojoDataInputException exception = assertThrows(PojoDataInputException.class, () -> {
            trimmer.trimAndPrepareInput(in);
        });

        assertEquals("Input cannot be null or blank",exception.getMessage());
    }

    @Test
    public void testGivenSimpleInputCanMapCorrectlyIntoPairString() {
        String in = "private int a;";

        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals("int",items.get(0).getType());

    }

    @Test
    public void testSimpleMultiLineInputCanMapIntoPairStrings() {
        String in = "private int a;" + System.lineSeparator() +
                    "private    int b;";

        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals("int",items.get(0).getType());
        assertEquals("b",items.get(1).getFieldName());
    }

    @Test
    public void testWorksWithNoAccessModifier() {
        String in = "int a;";

        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals("int",items.get(0).getType());
        assertEquals("a",items.get(0).getFieldName());
    }

    @Test
    public void testWorksWithMixedAccessModifier() {
        String in = "int a;" + System.lineSeparator() +
                    "private int b;" + System.lineSeparator() +
                    "String c;" + System.lineSeparator();


        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals("int",items.get(0).getType());
        assertEquals("a",items.get(0).getFieldName());
        assertEquals("int",items.get(1).getType());
        assertEquals("String", items.get(2).getType());
    }

    @Test
    public void testIgnoresAnnotations() {
        String in = "@JsonProperty('some')" + System.lineSeparator() +
                    "private int b;";

        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals(1,items.size());
        assertEquals("int",items.get(0).getType());
    }

    @Test
    public void testValidLineIsIgnoredIfAnnotationAtStart() {
        String in = "@JsonProperty('some') private int a;" + System.lineSeparator() +
                    "private String b;";

        List<TypeAndNamePair> items = trimmer.trimAndPrepareInput(in);

        assertNotNull(items);
        assertEquals(1,items.size());
        assertEquals("String",items.get(0).getType());
    }

    @Test
    public void testThrowsExceptionForStringOnly() {
        String in = "String";

        PojoDataInputException exception = assertThrows(PojoDataInputException.class, () -> {
            trimmer.trimAndPrepareInput(in);
        });

        assertEquals("Each line must contain at least a Type declaration and variable name",exception.getMessage());
    }

}
