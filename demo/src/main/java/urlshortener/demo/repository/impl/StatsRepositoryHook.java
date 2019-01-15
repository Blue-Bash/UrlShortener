package urlshortener.demo.repository.impl;

import io.micrometer.core.instrument.MeterRegistry;
import urlshortener.demo.domain.BaseEntity;
import urlshortener.demo.repository.IRepository;

public class StatsRepositoryHook<K, V extends BaseEntity<K>> implements IRepository<K, V> {

    private final IRepository<K, V> repo;
    private final MeterRegistry metrics;
    private final String key;

    public StatsRepositoryHook(IRepository<K, V> repo, MeterRegistry metrics, String key) {
        this.repo = repo;
        this.metrics = metrics;
        this.key = key;
    }


    @Override
    public void add(V value) {
        if(repo != null) repo.add(value);
        metrics.counter(key + ".created").increment();
        metrics.counter(key + ".now").increment();
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void remove(K key) {
        if(repo != null) repo.remove(key);
        metrics.counter(this.key + ".deleted").increment();
        metrics.counter(this.key + ".now").increment(-1.0);
    }

    @Override
    public void removeAll() {
        metrics.counter(key + ".deleted").increment(getCount());
        metrics.counter(key + ".now").increment(getCount());
        if(repo != null) repo.removeAll();
    }

    @Override
    public long getNextID() {
        return repo != null ? repo.getNextID() : -1;
    }

    @Override
    public boolean contains(K key) {
        return repo != null && repo.contains(key);
    }

    @Override
    public int getCount() {
        return repo != null ? repo.getCount() : 0;
    }
}
