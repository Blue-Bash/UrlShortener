package urlshortener.demo.repository;

import urlshortener.demo.domain.URIItem;

import java.util.Date;
import java.util.Map;

public interface URIRepository extends IRepository<String, URIItem> {
    Map<String, URIItem> getAllURIS();

    Map<String, Date> getAllFechas();

    void removeFecha(String id);

    void addFecha(String id, Date fecha);

    long getRedirectionAmount(String hash, long timeFromNow);

    void add(URIItem uri);

    URIItem get(String hash);

    void remove(String hash);

    void removeAll();
}
