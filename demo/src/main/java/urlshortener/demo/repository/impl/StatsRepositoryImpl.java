package urlshortener.demo.repository.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.SystemStats;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.StatsRepository;

import java.math.BigDecimal;

import static urlshortener.demo.domain.SystemStats.SYSTEM_STATS_ID;

@Repository
public class StatsRepositoryImpl extends AbstractRepository<String, Stats> implements StatsRepository {

    public SystemStats getSystemStats() {
        if (!contains(SYSTEM_STATS_ID)) {
            add(new SystemStats());
        }
        return (SystemStats) get(SYSTEM_STATS_ID);
    }

    public void incrementRedirectedUris() {
        SystemStats stats = getSystemStats();
        stats.setRedirectedUris(stats.getRedirectedUris()+1);
        update(stats.getId(),stats);
    }

    public void setServerLoad(BigDecimal load) {
        SystemStats stats = getSystemStats();
        stats.setServerLoad(load);
        update(stats.getId(),stats);
    }

    public void incrementGeneratedQr() {
        SystemStats stats = getSystemStats();
        stats.setGeneratedQr(stats.getGeneratedQr()+1);
        update(stats.getId(),stats);
    }

    public URIStats getURIStats(String hash) {
        if (contains(hash)) {
            return (URIStats) get(hash);
        }
        return null;
    }

    public void setLastAccess(String hash, Long time) {
        URIStats stats = getURIStats(hash);
        stats.setLastAccesses(time);
        update(stats.getId(),stats);
    }

    public void addURIStats(String hash) {
        add(new URIStats(hash));
    }

    public void removeURIStats(String hash) {
        remove(hash);
    }


}
