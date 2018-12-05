package urlshortener.demo.repository;

import urlshortener.demo.domain.URIItem;

import java.util.List;

public interface URIRepository extends IRepository<String, URIItem> {
    List<URIItem> checkURI();

    URIItem obtainURI(String id);

    void saveURI(URIItem uri);
}
