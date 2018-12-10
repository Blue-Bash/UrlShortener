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
    void setServerLoad(BigDecimal load);
    void incrementGeneratedQr();

    URIStats getURIStats(String hash);

    /**
     * Edit URI Stats
     */
    void setLastAccess(String lastAccess, Long time);

    void addURIStats(String hash);
    void removeURIStats(String hash);

}