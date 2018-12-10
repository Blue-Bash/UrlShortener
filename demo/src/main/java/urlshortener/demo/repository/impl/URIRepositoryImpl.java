package urlshortener.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.StatsRepository;
import urlshortener.demo.repository.URIRepository;

@Repository
public class URIRepositoryImpl extends AbstractRepository<String, URIItem> implements URIRepository {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public long getRedirectionAmount(String hash, long timeFromNow) {
        URIStats statsData = this.statsRepository.getURIStats(hash);
        if(statsData == null) throw new UnknownEntityException(HttpStatus.BAD_REQUEST.value(), "Unknown URI " + hash);

        return statsData.getAccesssesAfter(System.currentTimeMillis() - timeFromNow);
    }

    @Override
    public void add(URIItem uri) throws CannotAddEntityException {
        statsRepository.addURIStats(uri.getId());
        super.add(uri);
    }

    @Override
    public URIItem get(String hash) throws UnknownEntityException {
        if (!statsRepository.contains(hash)) {
            statsRepository.addURIStats(hash);
        }
        statsRepository.getURIStats(hash).addAccess(System.currentTimeMillis());

        return super.get(hash);
    }

    @Override
    public void remove(String hash) throws UnknownEntityException {
        statsRepository.removeURIStats(hash);
        super.remove(hash);
    }

    @Override
    public void removeAll() {
        statsRepository.removeAllURIStats();
        super.removeAll();
    }
}
