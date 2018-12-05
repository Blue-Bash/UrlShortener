package urlshortener.demo.repository.impl;

import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.URIRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {

    @Override
    public List<URIItem> checkURI() {
        return new ArrayList<>();
    }

    @Override
    public URIItem obtainURI(String id) {
        URIItem uri = new URIItem();                //Se recupera la URI asociada a al parámetro "id"
        uri.setRedirection("https://google.com");
        return uri;
    }

    @Override
    public void saveURI(URIItem uri) {
        //This function will save the "uri" object in the DB
    }

    @Override
    public void updateURI(URIUpdate uri) {
        //This function will update the "hashpass" uri name
    }


}
