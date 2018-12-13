package urlshortener.demo.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class QRItemTest {

    @Test
    public void checkQRQR(){
        QRItem qrItem = new QRItem().qr("qr");

        assertEquals("qr", qrItem.getQr());
        qrItem.setQr("qr2");
        assertEquals("qr2", qrItem.getQr());
    }

    @Test
    public void checkQRUri(){
        QRItem qrItem = new QRItem().uri("http://unizar.com");

        assertEquals("http://unizar.com", qrItem.getUri());
        assertEquals("http://unizar.com", qrItem.getId());
        qrItem.setUri("http://unizar.es");
        assertEquals("http://unizar.es", qrItem.getUri());
    }

    @Test
    public void testQREquals(){
        QRItem base = new QRItem().uri("abc");
        QRItem base2 = new QRItem().uri("abc");
        QRItem base3 = new QRItem().uri("abcd");
        QRItem base4 = new QRItem().uri("abcd").qr("otherQR");

        assertEquals(base, base);
        assertNotEquals(base, null);
        assertNotEquals(base, "abc");
        assertEquals(base, base2);
        assertNotEquals(base, base3);
        assertNotEquals(base, base4);
    }

    @Test
    public void testQRHashcode(){
        QRItem base = new QRItem().uri("abc");

        assertEquals(2987935, base.hashCode());
    }

    @Test
    public void testQRtoString(){
        QRItem base = new QRItem().uri("abc");
        assertEquals("class QRItem {\n    uri: abc\n    qr: null\n}", base.toString());
    }
}
