package urlshortener.demo.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class URIUpdateTests {

    @Test
    public void testNewName(){
        URIUpdate base = new URIUpdate().newName("abc");

        assertEquals("abc", base.getNewName());
        base.setNewName("abcd");
        assertEquals("abcd", base.getNewName());
    }

    @Test
    public void testHashpass(){
        URIUpdate base = (URIUpdate) new URIUpdate().hashpass("abc");

        assertEquals("abc", base.getHashpass());
        base.setHashpass("abcd");
        assertEquals("abcd", base.getHashpass());
    }

    @Test
    public void testURIUpdateEquals(){
        URIUpdate base = new URIUpdate().newName("abc");
        URIUpdate base2 = new URIUpdate().newName("abc");
        URIUpdate base3 = new URIUpdate().newName("abcd");
        URIUpdate base4 = (URIUpdate) new URIUpdate().newName("abc").hashpass("abcd");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
        assertNotEquals(base, base4);
    }

    @Test
    public void testToString(){
        URIUpdate uriUpdate1 = new URIUpdate().newName("abc");
        assertEquals("class URIUpdate {\n    newName: abc\n    hashpass: null\n}", uriUpdate1.toString());
    }

    @Test
    public void testHashCode(){
        URIUpdate uriUpdate1 = new URIUpdate().newName("abc");
        assertEquals(2987935, uriUpdate1.hashCode());
    }

}
