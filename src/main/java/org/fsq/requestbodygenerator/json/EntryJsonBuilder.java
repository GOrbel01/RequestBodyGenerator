package org.fsq.requestbodygenerator.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fsq.requestbodygenerator.data.MockDataProvider;
import org.fsq.requestbodygenerator.model.Entries;
import org.fsq.requestbodygenerator.model.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryJsonBuilder {

    private MockDataProvider mockDataProvider;

    public EntryJsonBuilder(MockDataProvider mockDataProvider) {
        this.mockDataProvider = mockDataProvider;
    }

    public ObjectNode buildJsonFromEntries(Entries entries) {
        ObjectMapper om = new ObjectMapper();
        ObjectNode rootNode = om.createObjectNode();
        for (int i = 0; i < entries.getSize(); i++) {
            Entry e = entries.get(i);
            if (e.getTypeInfo().isComplexType()) {
                ObjectNode childNode2 = om.createObjectNode();
                rootNode.set(e.getFieldName(),childNode2);
            } else {
                rootNode.putPOJO(e.getFieldName(), mockDataProvider.getData(e.getTypeInfo()));
            }
        }
        return rootNode;
    }
}
