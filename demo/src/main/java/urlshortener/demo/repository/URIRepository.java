package urlshortener.demo.repository;

import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;

import java.util.List;

public interface URIRepository extends IRepository<String, URIItem> {
    List<URIItem> checkURI();

    long getRedirectionAmount(String hash, long timeFromNow);

    void add(URIItem uri);

    URIItem get(String hash);

    void remove(String hash);

    void removeAll();
}
