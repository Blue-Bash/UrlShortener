package urlshortener.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.SystemStats;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private StatsRepository service;
    private SystemStats item1, item2, item3;

    public StatsRepositoryTest() {
        this.item1 = ((SystemStats) new SystemStats().id("testStats1")).generatedQr(99);
        this.item2 = ((SystemStats) new SystemStats().id("testStats1")).generatedQr(10);
        this.item3 = ((SystemStats) new SystemStats().id("testStats2")).generatedQr(99);
    }

    @Test
    public void cleanUp() {
        super.cleanUp(service);
    }

    @Test
    public void testInsertOK() {
        super.testInsertOK(service, item1);
    }

    @Test
    public void testInsertDuplicateFail() {
        super.testInsertDuplicateFail(service, item1, item2);
    }

    @Test
    public void testGet() {
        super.testGet(service, item1, item2, item3);
    }

    @Test
    public void testRemove() {
        super.testRemove(service, item1, item2, item3);
    }
}
