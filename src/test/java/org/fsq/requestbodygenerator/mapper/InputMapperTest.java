package org.fsq.requestbodygenerator.mapper;

import org.fsq.requestbodygenerator.data.Collections;
import org.fsq.requestbodygenerator.data.Types;
import org.fsq.requestbodygenerator.model.Entries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InputMapperTest {
    @Autowired
    private InputMapper inputMapper;

    private List<TypeAndNamePair> dataInput;

    @BeforeEach
    public void setup() {
        dataInput = new ArrayList<>();
        dataInput.add(new TypeAndNamePair("String","testStr"));
        dataInput.add(new TypeAndNamePair("int","testNum"));
        dataInput.add(new TypeAndNamePair("Object","testOb"));
    }

    @Test
    public void testGivenInputSuccessfullyProducesOutput() {
        Entries result = inputMapper.mapInputToEntries(dataInput);

        assertNotNull(result);
        assertEquals(Types.STRING,result.get(0).getTypeInfo().getType());
        assertEquals(Types.INT,result.get(1).getTypeInfo().getType());
        assertEquals(Types.COMPLEX,result.get(2).getTypeInfo().getType());
        assertEquals("testStr",result.get(0).getFieldName());
    }


    @Test
    public void givenListTypeProducesDeterminesTheCorrectDataType() {
        dataInput.add(new TypeAndNamePair("List<Integer>","intList"));

        Entries result = inputMapper.mapInputToEntries(dataInput);
        assertNotNull(result);
        assertEquals(Collections.LIST,result.get(3).getTypeInfo().getCollections());
        assertEquals(Types.INT,result.get(3).getTypeInfo().getType());
    }

    @Test
    public void givenNestedListProducesListType() {
        dataInput.add(new TypeAndNamePair("List<List<List<List<Integer>>>>","nestedIntList"));

        Entries result = inputMapper.mapInputToEntries(dataInput);
        assertNotNull(result);
        assertEquals(Collections.LIST,result.get(3).getTypeInfo().getCollections());
        assertEquals(Types.INT,result.get(3).getTypeInfo().getType());
    }
}
