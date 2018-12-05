package urlshortener.demo.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class URIUpdateTest {

    @Test
    public void testOK(){
        URIUpdate uriUpdate1 = new URIUpdate();
        URIUpdate uriUpdate2 = uriUpdate1.newName("abc");
        URIUpdate uriUpdate3 = (URIUpdate) uriUpdate2.hashpass("abcd");

        assertEquals(uriUpdate3, uriUpdate1);
        assertEquals(uriUpdate3, uriUpdate2);
    }

}