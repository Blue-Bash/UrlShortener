package urlshortener.demo.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ErrorItemTest {

    @Test
    public void testErrorItem(){
        ErrorItem base = new ErrorItem().errorInfo("ABC");

        assertEquals("ABC", base.getErrorInfo());
        base.setErrorInfo("ABCD");
        assertEquals("ABCD", base.getErrorInfo());
    }

    @Test
    public void testErrorItemEquals(){
        ErrorItem base = new ErrorItem().errorInfo("abc");
        ErrorItem base2 = new ErrorItem().errorInfo("abc");
        ErrorItem base3 = new ErrorItem().errorInfo("abcd");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
    }

    @Test
    public void testErrorItemHashcode(){
        ErrorItem base = new ErrorItem().errorInfo("abc");

        assertEquals(96385, base.hashCode());
    }

    @Test
    public void testErrorItemToString(){
        ErrorItem base = new ErrorItem().errorInfo("abc");
        ErrorItem base2 = new ErrorItem().errorInfo(null);
        assertEquals("class ErrorItem {\n    errorInfo: abc\n}", base.toString());
        assertEquals("class ErrorItem {\n    errorInfo: null\n}", base2.toString());
    }

}
