package urlshortener.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.repository.AbstractRepository;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.StatsRepository;

@Repository
public class QRRepositoryImpl extends AbstractRepository<String, QRItem> implements QRRepository {
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public void add(QRItem uri) throws CannotAddEntityException {
        statsRepository.incrementGeneratedQr();
        super.add(uri);
    }
}
