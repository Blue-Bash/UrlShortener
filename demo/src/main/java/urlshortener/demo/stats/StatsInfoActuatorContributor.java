package urlshortener.demo.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import urlshortener.demo.repository.IRepository;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatsInfoActuatorContributor implements InfoContributor {

    private final URIRepository uriRepository;
    private final QRRepository qrRepository;

    @Autowired
    public StatsInfoActuatorContributor(URIRepository uriRepository, QRRepository qrRepository) {
        this.uriRepository = uriRepository;
        this.qrRepository = qrRepository;
    }

    private Map<String, Object> getStatsFor(IRepository repository){
        Map<String, Object> details = new HashMap<>();
        details.put("amount", repository.getCount());
        //userDetails.put("inactive", uriRepository.countByStatus(0));
        return details;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("uri", getStatsFor(uriRepository));
        builder.withDetail("qr", getStatsFor(qrRepository));
    }
}

