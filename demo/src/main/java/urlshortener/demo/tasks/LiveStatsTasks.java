package urlshortener.demo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urlshortener.demo.repository.StatsRepository;

@Component
public class LiveStatsTasks {

    private final SimpMessagingTemplate messager;

    private final StatsRepository statsApi;

    @Autowired
    public LiveStatsTasks(SimpMessagingTemplate messager, StatsRepository statsApi) {
        this.messager = messager;
        this.statsApi = statsApi;
    }

    @Scheduled(fixedRate = 1000)
    public void runTask(){
        messager.convertAndSend("/stats/live", statsApi.getStats());
    }

}
