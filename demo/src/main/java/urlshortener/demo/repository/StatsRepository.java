package urlshortener.demo.repository;

import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.SystemStats;
import urlshortener.demo.domain.URIStats;

import java.math.BigDecimal;

public interface StatsRepository extends IRepository<String, Stats> {

    SystemStats getSystemStats();

    /**
     * Edit System Stats
     */
    void incrementRedirectedUris();
    void decrementRedirectedUris();
    void setServerLoad(BigDecimal load);
    void incrementGeneratedQr();
    void incrementRedirections();

    URIStats getURIStats(String hash);

    /**
     * Edit URI Stats
     */
    void addAccess(String hash, long time);
    long getAccesssesAfter(String hash, long time);

    void addURIStats(String hash);
    void removeURIStats(String hash);
    void removeAllURIStats();

}