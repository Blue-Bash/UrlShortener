package urlshortener.demo.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class URIItemTest {
    @Test
    public void testURIItemID(){
        URIItem base = new URIItem().id("abc");

        assertEquals("abc", base.getId());
        base.setId("abcd");
        assertEquals("abcd", base.getId());
    }

    @Test
    public void testURIItemRedirection(){
        URIItem base = new URIItem().redirection("abc");

        assertEquals("abc", base.getRedirection());
        base.setRedirection("abcd");
        assertEquals("abcd", base.getRedirection());
    }

    @Test
    public void testURIItemEquals(){
        URIItem base = new URIItem().id("abc");
        URIItem base2 = new URIItem().id("abc");
        URIItem base3 = new URIItem().id("abcd");
        URIItem base4 = new URIItem().id("abc").redirection("abcd");
        URIItem base5 = (URIItem) new URIItem().id("abc").hashpass("abcd");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
        assertNotEquals(base, base4);
        assertNotEquals(base, base5);
    }

    @Test
    public void testURIItemCheckHashcode(){
        URIItem base = new URIItem().id("abc");

        assertEquals(92625985, base.hashCode());
    }

    @Test
    public void testURIItemCheckToString(){
        URIItem base = new URIItem().id("abc");

        assertEquals("class URIItem {\n    id: abc\n    redirection: null\n    hashpass: null\n}", base.toString());
    }
}
