package urlshortener.demo.repository;

import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;

import java.util.List;

public interface URIRepository extends IRepository<String, URIItem> {
    List<URIItem> checkURI();

    URIItem obtainURI(String id);

    void saveURI(URIItem uri);
    void updateURI(URIUpdate uri);
    long getRedirectionAmount(String hash, long timeFromNow);
}
