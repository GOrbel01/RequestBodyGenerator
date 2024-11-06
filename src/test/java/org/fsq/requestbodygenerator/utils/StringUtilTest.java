package org.fsq.requestbodygenerator.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilTest {

    @Test
    public void givenStringWithMultiSpacesConvertsSuccessfullyIntoSplitArrayIgnoringExcessOfRegex() {
        String in = " private    int    b;";

        String[] res = StringUtil.splitAll(in," ");

        assertEquals("private",res[0]);
        assertEquals("int",res[1]);
        assertEquals("b;",res[2]);
    }

    @Test
    public void givenNoMatchAtStartStillWorks() {
        String in = "priv##ate#int# ## b#;##";
        String[] res = StringUtil.splitAll(in,"##");

        assertEquals("priv",res[0]);
        assertEquals("ate#int# ",res[1]);
    }

    @Test
    public void givenOneLongMatchStillWorks() {
        String in = "priv%%%%%%%%%%%%%%%%%%%ate";
        String[] res = StringUtil.splitAll(in,"%%%%%%%%%%%%%%%%%%%");

        assertEquals("priv",res[0]);
        assertEquals("ate",res[1]);
    }


    @Test
    public void givenRegexLengthThreeStillWorks() {
        String in = "priv###ate";
        String[] res = StringUtil.splitAll(in,"###");

        assertEquals("priv",res[0]);
        assertEquals("ate",res[1]);
    }

    @Test
    public void givenStringWithManyOfCharactersSplitsAsExpected() {
        String in = "##priv##ate#int# ## b#;##";

        String[] res = StringUtil.splitAll(in,"##");

        assertEquals("priv",res[0]);
        assertEquals("ate#int# ",res[1]);
        assertEquals(" b#;",res[2]);

        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            System.out.println(res[3]);
        });

        assertEquals(ArrayIndexOutOfBoundsException.class,exception.getClass());

    }
}
