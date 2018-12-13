package urlshortener.demo.controller;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.URIRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CheckApiControllerIntegrationTest {

    @Autowired
    private CheckApi api;

    @Autowired
    private URIRepository repo;

    @Test(expected = urlshortener.demo.exception.UnknownEntityException.class)
    public void checkURITest_not_existing_URI() {
        ResponseEntity<Void> responseEntity = api.checkURI("id_example");
    }

    @Test
    public void checkURITest_not_null_existing_URI(){
        URIItem uri = new URIItem();
        uri.setId("1");
        uri.setHashpass("1");
        uri.setRedirection("https://www.google.es");
        repo.add(uri);

        URIItem newUri = repo.get("1");

        assertEquals(uri.getRedirection(), newUri.getRedirection());
        ResponseEntity<Void> responseEntity = api.checkURI("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected = urlshortener.demo.exception.UnknownEntityException.class)
    public void checkURITest_null_URI_throws_UnknownEntityException() {
        ResponseEntity<Void> responseEntity = api.checkURI(null);
    }

    @Test(expected = urlshortener.demo.exception.UnknownEntityException.class)
    public void checkURITest_empty_URI_throws_UnknownEntityException() {
        ResponseEntity<Void> responseEntity = api.checkURI("");
    }


}
