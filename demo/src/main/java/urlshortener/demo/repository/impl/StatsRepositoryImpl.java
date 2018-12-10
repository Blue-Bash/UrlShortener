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
            super.add(new SystemStats());
        }
        return (SystemStats) super.get(SYSTEM_STATS_ID);
    }

    public void incrementRedirectedUris() {
        SystemStats stats = getSystemStats();
        stats.setRedirectedUris(stats.getRedirectedUris()+1);
        super.update(stats.getId(),stats);
    }

    public void decrementRedirectedUris() {
        SystemStats stats = getSystemStats();
        stats.setRedirectedUris(stats.getRedirectedUris()-1);
        super.update(stats.getId(),stats);
    }

    public void setServerLoad(BigDecimal load) {
        SystemStats stats = getSystemStats();
        stats.setServerLoad(load);
        super.update(stats.getId(),stats);
    }

    public void incrementGeneratedQr() {
        SystemStats stats = getSystemStats();
        stats.setGeneratedQr(stats.getGeneratedQr()+1);
        super.update(stats.getId(),stats);
    }

    public void incrementRedirections() {
        SystemStats stats = getSystemStats();
        stats.setRedirections(stats.getRedirections()+1);
        super.update(stats.getId(),stats);
    }

    public URIStats getURIStats(String hash) {
        if (contains(hash)) {
            return (URIStats) get(hash);
        }
        return null;
    }

    public void addAccess(String hash, long time) {
        URIStats stats = getURIStats(hash);
        stats.addAccess(time);
        super.update(stats.getId(),stats);
    }

    public long getAccesssesAfter(String hash, long time) {
        URIStats stats = getURIStats(hash);
        return stats.getAccesssesAfter(time);
    }

    public void addURIStats(String hash) {
        super.add(new URIStats(hash));
    }

    public void removeURIStats(String hash) {
        super.remove(hash);
    }

    public void removeAllURIStats() {
        // TODO: Enhance this implementation (?)
        SystemStats systemStats = getSystemStats();
        super.removeAll();
        super.add(systemStats);
    }

}
