package org.fsq.requestbodygenerator.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    /**
     * Split a String by a matching substring
     * Unlike default split, if the split character was '-' then
     * if three '-' characters occur consecutively, removes all as part of the split instead of just one '-'
     * @param in Input String to split
     * @param match Substring to search for in the in String
     * @return New array with each String per split
     */
    public static String[] splitAll(String in, String match) {
        if (in.length() < match.length()+2) {
            throw new IllegalArgumentException("String Length must be at least search string length + 2 or more");
        }
        if (match.length() > in.length()) {
            throw new IllegalArgumentException("String to search cannot be longer than the input string");
        }

        List<String> result = new ArrayList<>();

        int length = match.length();
        int index = 0;
        int nextIndex = 0;
        int start = 0;
        int end;

        boolean flagFound = false;
        while (nextIndex < in.length()-length && index+length < in.length()) {
            nextIndex = index+1;
            if (in.substring(index,index+length).equals(match)) {
                index += length;
                if (index + length > in.length()) {
                    start = index;
                    flagFound = true;
                }
                else if (!in.substring(index,index+length).equals(match)) {
                    start = index;
                    flagFound = true;
                }
            } else {
                if (in.substring(nextIndex,nextIndex+length).equals(match)) {
                    end = nextIndex;
                    result.add(in.substring(start,end));
                    flagFound = false;
                }
                index++;
            }
        }

        if (flagFound) { //Handle any text at the end of the string with no further regex matches
            result.add(in.substring(start));
        }

        return result.toArray(new String[0]);
    }

}
