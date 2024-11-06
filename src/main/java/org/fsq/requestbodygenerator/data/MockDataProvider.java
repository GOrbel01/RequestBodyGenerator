package org.fsq.requestbodygenerator.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide some Mock Data for the JSON response
 * Could be expanded some day to provide more meaningful data. Perhaps with AI based on fieldName
 */
@Component
public class MockDataProvider {
    public Object getData(TypeInfo t) {
        if (!t.isCollectionType()) {
            return getStandardData(t);
        } else {
            return getCollectionData(t);
        }
    }

    public Object getCollectionData(TypeInfo t) {
        List res = new ArrayList();
        res.add(getStandardData(t));
        res.add(getStandardData(t));
        return res;
    }

    public Object getStandardData(TypeInfo t) {
        if (t.getType().isNumeric()) {
            return 1;
        } else if (t.getType() == Types.STRING) {
            return "testStr";
        } else if (t.getType() == Types.BOOLEAN) {
            return "false";
        }
        return null;
    }
}
