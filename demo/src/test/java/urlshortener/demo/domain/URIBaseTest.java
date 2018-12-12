package urlshortener.demo.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class URIBaseTest {

    @Test
    public void testURIBaseHashpass(){
        URIBase base = new URIBase().hashpass("abc");

        assertEquals("abc", base.getHashpass());
        base.setHashpass("abcd");
        assertEquals("abcd", base.getHashpass());
    }

    @Test
    public void testURIBaseEquals(){
        URIBase base = new URIBase().hashpass("abc");
        URIBase base2 = new URIBase().hashpass("abc");
        URIBase base3 = new URIBase().hashpass("abcd");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
    }

    @Test
    public void testURIBaseCheckHashcode(){
        URIBase base = new URIBase().hashpass("abc");

        assertEquals(96385, base.hashCode());
    }

    @Test
    public void testURIBaseCheckHashpass(){
        URIBase base = new URIBase().hashpass("abc");

        assertFalse(base.checkHashPass(null));
        assertFalse(base.checkHashPass("abcd"));
        assertTrue(base.checkHashPass("abc"));
    }
}
