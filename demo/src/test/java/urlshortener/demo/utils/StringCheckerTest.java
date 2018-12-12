package urlshortener.demo.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCheckerTest {

    @Test
    public void testStringChecker(){
        assertEquals(500, StringChecker.checkString2Int(null));
        assertEquals(500, StringChecker.checkString2Int("0"));
        assertEquals(500, StringChecker.checkString2Int("3000"));
        assertEquals(500, StringChecker.checkString2Int("500"));

    }

}
