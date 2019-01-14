package urlshortener.demo.repository.impl;

import org.springframework.http.HttpStatus;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.IRepository;
import urlshortener.demo.repository.URIRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {

    private final IRepository<String, URIItem> repo;
    private Map<String, URIData> stats = new HashMap<>();

    public URIRepositoryImpl(IRepository<String, URIItem> repo) {
        this.repo = repo;
    }

    @Override
    public long getRedirectionAmount(String hash, long timeFromNow) {
        if(repo != null) repo.get(hash);
        URIData statsData = this.stats.get(hash);
        if(statsData == null) throw new UnknownEntityException(HttpStatus.BAD_REQUEST.value(), "Unknown URI " + hash);

        return statsData.getAccesssesAfter(System.currentTimeMillis() - timeFromNow);
    }

    @Override
    public void add(URIItem uri) {
        if(repo != null) repo.add(uri);
        stats.putIfAbsent(uri.getId(), new URIData());
        super.add(uri);
    }

    @Override
    public URIItem get(String hash) {
        if(repo != null) repo.get(hash);
        stats.putIfAbsent(hash, new URIData());
        stats.get(hash).addAccess();

        return super.get(hash);
    }

    @Override
    public void remove(String hash) {
        if(repo != null) repo.remove(hash);
        stats.remove(hash);
        super.remove(hash);
    }

    @Override
    public void removeAll() {
        if(repo != null) repo.removeAll();
        stats.clear();
        super.removeAll();
    }

    //THIS ARE NOT STATS, ONLY URI DATA USED TO BLOCK DDoS
    private static class URIData {
        private List<Long> lastAccesses = new ArrayList<>();

        private void addAccess(){
            this.lastAccesses.add(System.currentTimeMillis());
        }

        private long getAccesssesAfter(long time){
            return lastAccesses.stream().filter(t -> t > time).count();
        }
    }
}
