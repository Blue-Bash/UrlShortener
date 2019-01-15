package urlshortener.demo.domain;

import org.junit.Test;
import urlshortener.demo.utils.StringChecker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class QRItemTest {

    private static final String EXPECTED_QR_CODE = "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC";

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

    @Test
    public void getQRTest() {
        String id = "www.google.es";
        QRItem qr = new QRItem();
        qr.setUri(id);
        qr.convertBase64(500, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    // When any incorrect param is passed, such as a null param, negative param, zero param or smaller than
    // 30 param, the QR is generated with a standar 500px size.
    @Test
    public void nullWidthTest() {
        String id = "www.google.es";
        String width = null;
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);

        qr.setUri(id);
        qr.convertBase64(w, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void nullHeigthTest() {
        String id = "www.google.es";
        String height = null;
        QRItem qr = new QRItem();
        int h = StringChecker.checkString2Int(height);

        qr.setUri(id);
        qr.convertBase64(500, h);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void negativeWidthTest() {
        String id = "www.google.es";
        String width = "-5";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);

        qr.setUri(id);
        qr.convertBase64(w, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void negativeHeightTest() {
        String id = "www.google.es";
        String height = "-34";
        QRItem qr = new QRItem();
        int h = StringChecker.checkString2Int(height);

        qr.setUri(id);
        qr.convertBase64(500, h);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void zeroWidthTest() {
        String id = "www.google.es";
        String width = "0";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);

        qr.setUri(id);
        qr.convertBase64(w, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void zeroHeightTest() {
        String id = "www.google.es";
        String height = "0";
        QRItem qr = new QRItem();
        int h = StringChecker.checkString2Int(height);

        qr.setUri(id);
        qr.convertBase64(500, h);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void smallerThan30WidthTest() {
        String id = "www.google.es";
        String width = "27";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);

        qr.setUri(id);
        qr.convertBase64(w, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void smallerThan30heightTest() {
        String id = "www.google.es";
        String height = "27";
        QRItem qr = new QRItem();
        int h = StringChecker.checkString2Int(height);

        qr.setUri(id);
        qr.convertBase64(500, h);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void greaterThan2048WidthTest() {
        String id = "www.google.es";
        String width = "2049";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);

        qr.setUri(id);
        qr.convertBase64(w, 500);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }

    @Test
    public void greaterThan2048heightTest() {
        String id = "www.google.es";
        String height = "2049";
        QRItem qr = new QRItem();
        int h = StringChecker.checkString2Int(height);

        qr.setUri(id);
        qr.convertBase64(500, h);

        assertEquals(EXPECTED_QR_CODE, qr.getQr());
    }
}
