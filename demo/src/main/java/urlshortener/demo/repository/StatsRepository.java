package urlshortener.demo.repository;

import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIStats;

public interface StatsRepository {

    Stats getStats();

    URIStats getUriStats(String keyID);

    void incrementAccessStats(String id);

}
