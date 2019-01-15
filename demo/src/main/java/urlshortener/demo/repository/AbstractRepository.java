package urlshortener.demo.repository;

import org.springframework.http.HttpStatus;
import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AbstractRepository<K, V extends BaseEntity<K>> implements IRepository<K, V> {

    private long nextID = 0;
    private Map<K, V> uris = new HashMap<>();
    private Map<K, Date> fechas = new HashMap<>();

    public Map<K, V> getAllUris(){
        return uris;
    }

    public Map<K, Date> getAllFechas() {
        return fechas;
    }

    public void removeFecha(K hash){
        if(!fechas.containsKey(hash)){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot fetch date with uri hash " + hash);
        }
        fechas.remove(hash);
    }

    public void addFecha(K hash, Date fecha){
        if(fechas.containsKey(hash)){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot add date with uri hash " + hash);
        }
        fechas.put(hash, fecha);
    }

    @Override
    public void add(V uri) {
        if(uris.containsKey(uri.getId())){
            throw new CannotAddEntityException(HttpStatus.BAD_REQUEST.value(), "Cannot add uri with hash " + uri.getId());
        }

        uris.put(uri.getId(), uri);
        fechas.put(uri.getId(), new Date());
        nextID++;
    }

    @Override
    public V get(K hash) {
        V item = uris.get(hash);
        if(item == null){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot fetch uri with hash " + hash);
        }
        return item;
    }

    @Override
    public void remove(K hash) {
        if(!uris.containsKey(hash)){
            throw new UnknownEntityException(HttpStatus.NOT_FOUND.value(), "Cannot fetch uri with hash " + hash);
        }
        uris.remove(hash);
        fechas.remove(hash);
    }

    @Override
    public void removeAll() {
        uris.clear();
    }

    @Override
    public long getNextID(){
        return nextID;
    }

    @Override
    public boolean contains(K key){
        return uris.containsKey(key);
    }

    @Override
    public int getCount() {
        return uris.size();
    }
}
