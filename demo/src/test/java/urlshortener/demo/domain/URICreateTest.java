package urlshortener.demo.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class URICreateTest {

    @Test
    public void testURICreateUri(){
        URICreate base = new URICreate().uri("abc");

        assertEquals("abc", base.getUri());
        base.setUri("abcd");
        assertEquals("abcd", base.getUri());
    }

    @Test
    public void testURICreateEquals(){
        URICreate base = new URICreate().uri("abc");
        URICreate base2 = new URICreate().uri("abc");
        URICreate base3 = new URICreate().uri("abcd");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
    }

    @Test
    public void testURICreateCheckHashcode(){
        URICreate base = new URICreate().uri("abc");

        assertEquals(2987935, base.hashCode());
    }

    @Test
    public void testURICreateCheckToString(){
        URICreate base = new URICreate().uri("abc");
        assertEquals("class URICreate {\n    uri: abc\n    name: \n}", base.toString());

        base = new URICreate().uri(null);
        assertEquals("class URICreate {\n    uri: null\n    name: \n}", base.toString());

    }

}
