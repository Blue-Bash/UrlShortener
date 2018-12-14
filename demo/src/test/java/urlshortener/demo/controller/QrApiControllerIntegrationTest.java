package urlshortener.demo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrApiControllerIntegrationTest {

    private static final String EXPECTED_QR_CODE = "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC";
    @Autowired
    private QrApi api;

    @Autowired
    private URIRepository uriRepository;

    @Autowired
    private QRRepository qrRepository;

    private QRItem qr;
    private URIItem uri;

    @Before
    public void setup(){
        String id = "google";
        String redirection = "www.google.es";
        uri = new URIItem().id(id).redirection(redirection);
        qr = new QRItem();
        qr.setUri(id);
        qr.convertBase64(500, 500);

        uriRepository.add(uri);
        qrRepository.add(qr);
    }

    @After
    public void cleanup(){
        qrRepository.remove(qr.getId());
        uriRepository.remove(uri.getId());

    }

    @Test
    public void test500pxWidthAndHeight(){
        ResponseEntity<QRItem> response = api.getQR("google");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(qr, response.getBody());
    }

}
