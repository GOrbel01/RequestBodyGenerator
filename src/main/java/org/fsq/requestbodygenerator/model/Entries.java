package org.fsq.requestbodygenerator.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Entries {
    private List<Entry> fieldList;

    public Entries() {
        this.fieldList = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        fieldList.add(entry);
    }

    public Entry get(int index) {
        return fieldList.get(index);
    }

    public int getSize() {
        return fieldList.size();
    }
}
