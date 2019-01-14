package urlshortener.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.CheckAlive;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Component
public class checkSchedule {
    private final int CHECK_RATE = 86400000;

    private static final Logger log = LoggerFactory.getLogger(checkSchedule.class);

    private final URIRepository uriService;

    @org.springframework.beans.factory.annotation.Autowired
    public checkSchedule(URIRepository uriService) {
        this.uriService = uriService;
    }

    @Scheduled(fixedRate = CHECK_RATE)
    public void check() throws IOException, InterruptedException {
        CheckAlive c = new CheckAlive();

        Map<String, URIItem> uris = uriService.getAllURIS();
        Collection<URIItem> soloUris = uris.values();
        Map<String, Date> fechas = uriService.getAllFechas();

        log.info("Comienza el checkeo de las URI...");
        for (URIItem i: soloUris){
            log.info("COMPROBANDO URI " + i.getRedirection());
            if (c.makeRequest(i.getRedirection()) == 200){
                log.info("La URI " + i.getRedirection() + " está viva.");
                //OK
                //Para esa URI, se registra la fecha actual como útima fecha en la que estuvo viva
                uriService.removeFecha(i.getId());
                uriService.addFecha(i.getId(), new Date());
            }
            else {
                log.info("La URI " + i.getRedirection() + " no responde.");
                //Cualquier otra cosa aparte de un código 200 significará que la URI está muerta
                //Se obtiene la última vez que la URI estuvo viva
                //  -Si la diferencia entre la fecha actual y la fecha recuperada es >= K, entonces la URI se borra
                long actual = System.currentTimeMillis();
                long fechaUri = fechas.get(i.getId()).getTime();
                long diff = actual - fechaUri;
                long days = diff / (604800000);
                if (days >= 7.0){
                    uriService.remove(i.getId());
                }
            }
        }
    }

}

