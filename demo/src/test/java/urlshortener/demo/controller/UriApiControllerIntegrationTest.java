package urlshortener.demo.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.URIRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UriApiControllerIntegrationTest {

    @Autowired
    private UriApi api;

    @Autowired
    private URIRepository service;

    @After
    public void cleanUp(){
        service.removeAll();
    }

    @Test
    public void changeURITest() {
        URICreate body = new URICreate();
        String name = "name_example";
        ResponseEntity<URIItem> responseEntity = api.changeURI(body, name);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createURITest() {
        URICreate body = new URICreate();
        body.setUri("https://google.es");
        ResponseEntity<URIItem> responseEntity = api.createURI(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteURITest() {
        String id = "id_example";
        ResponseEntity<Void> responseEntity = api.deleteURI(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getURITestError() {
        String id = "id_example";
        try {
            api.getURI(id);
            fail();
        }catch(UnknownEntityException ignored){ }
    }

    @Test
    public void getURITestOK() {
        String id = "id_example";
        service.add((URIItem) new URIItem().id(id).redirection("http://google.es").hashpass("abc"));
        try {
            ResponseEntity<Void> responseEntity = api.getURI(id);
            assertEquals(HttpStatus.TEMPORARY_REDIRECT, responseEntity.getStatusCode());
        }catch(UnknownEntityException ignored){ fail(); }
    }

}
