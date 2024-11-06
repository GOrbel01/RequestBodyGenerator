package org.fsq.requestbodygenerator.json;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fsq.requestbodygenerator.data.Collections;
import org.fsq.requestbodygenerator.data.TypeInfo;
import org.fsq.requestbodygenerator.data.Types;
import org.fsq.requestbodygenerator.model.Entries;
import org.fsq.requestbodygenerator.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.fsq.requestbodygenerator.utils.JsonUtil.getPojo;

@SpringBootTest
public class EntryJsonBuilderTest {
    @Autowired
    private EntryJsonBuilder entryJsonBuilder;

    private Entries entries;

    @BeforeEach
    public void setup() {
        entries = new Entries();
        entries.addEntry(new Entry(new TypeInfo(Types.STRING),"someStrField"));
        entries.addEntry(new Entry(new TypeInfo(Types.INT), "someInt"));
        entries.addEntry(new Entry(new TypeInfo(Types.COMPLEX), "someObject"));
        entries.addEntry(new Entry(new TypeInfo(Types.BOOLEAN), "someBoolean"));
    }

    @Test
    public void testGivenSetOfEntriesBuildsTheExpectedJsonResponse() {
        ObjectNode root = entryJsonBuilder.buildJsonFromEntries(entries);
        assertNotNull(root);
        assertEquals("testStr",root.findValue("someStrField").asText());
        assertEquals(1,root.findValue("someInt").asInt());
        assertNotNull(root.findValue("someObject"));
        assertInstanceOf(Object.class, root.findValue("someObject"));
        final List<String> fieldNames = new ArrayList<>();
        root.fieldNames().forEachRemaining(fieldNames::add);
        assertTrue(fieldNames.contains("someStrField"));
        assertTrue(fieldNames.contains("someObject"));
    }

    @Test
    public void testGivenSetOfEntriesIncludingListSuccessfullyCreatesListOfType() throws IOException {
        entries.addEntry(new Entry(new TypeInfo(Collections.LIST,Types.STRING), "someStrListField"));
        entries.addEntry(new Entry(new TypeInfo(Collections.LIST, Types.LONG), "someIntListField"));

        ObjectNode root = entryJsonBuilder.buildJsonFromEntries(entries);
        assertNotNull(root);

        List strRes = (List) getPojo(root,"someStrListField",List.class);
        assertEquals("testStr",strRes.get(0));
        assertEquals("testStr",strRes.get(1));

        List intRes = (List) getPojo(root,"someIntListField",List.class);
        assertEquals(1,intRes.get(0));
        assertEquals(1,intRes.get(1));
    }
}
