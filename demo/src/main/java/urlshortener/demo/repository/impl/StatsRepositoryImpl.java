package urlshortener.demo.repository.impl;

import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.StatsRepository;

@Repository
public class StatsRepositoryImpl extends AbstractRepository<String, Stats> implements StatsRepository {
}
