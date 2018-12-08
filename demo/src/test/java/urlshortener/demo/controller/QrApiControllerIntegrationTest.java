package urlshortener.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.utils.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrApiControllerIntegrationTest {

    @Autowired
    private QrApi api;

    @Test
    public void getQRTest() throws Exception {
        String id = "www.google.es";
        QRItem qr = new QRItem();
        qr.setUri(id);
        qr.convertBase64(500, 500);
        
        assertEquals(qr.getQr(), "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC");
    }

    // When any incorrect param is passed, such as a null param, negative param, zero param or smaller than
    // 30 param, the QR is generated with a standar 500px size.
    @Test
    public void nullParamTests() throws Exception {
        String id = "www.google.es";
        String width = null;
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);
        
        qr.setUri(id);
        qr.convertBase64(w, 500);
        
        assertEquals(qr.getQr(), "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC");
    }

    @Test
    public void negativeParamTests() throws Exception {
        String id = "www.google.es";
        String width = "-5";
        String height = "-34";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);
        int h = StringChecker.checkString2Int(height);
        
        qr.setUri(id);
        qr.convertBase64(w, h);
        
        assertEquals(qr.getQr(), "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC");
    }

    @Test
    public void zeroParamTests() throws Exception {
        String id = "www.google.es";
        String width = "0";
        String height = "0";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);
        int h = StringChecker.checkString2Int(height);
        
        qr.setUri(id);
        qr.convertBase64(w, h);
        
        assertEquals(qr.getQr(), "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC");
    }

    @Test
    public void smallerThan30ParamTests() throws Exception {
        String id = "www.google.es";
        String width = "27";
        String height = "27";
        QRItem qr = new QRItem();
        int w = StringChecker.checkString2Int(width);
        int h = StringChecker.checkString2Int(height);
        
        qr.setUri(id);
        qr.convertBase64(w, h);
        
        assertEquals(qr.getQr(), "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC");
    }

}
