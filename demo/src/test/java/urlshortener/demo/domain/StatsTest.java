package urlshortener.demo.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StatsTest {

    @Test
    public void testStatsFields(){
        Stats base = new Stats();
        base.setUrisAccessed(BigDecimal.valueOf(0.0));
        base.setUrisRemoved(BigDecimal.valueOf(1.0));
        base.setUrisCreated(BigDecimal.valueOf(2.0));
        base.setUrisNow(BigDecimal.valueOf(3.0));
        base.setServerUsage(BigDecimal.valueOf(4.0));
        base.setMemoryUsage(5);
        base.setCpuUsage(BigDecimal.valueOf(6.0));
        base.setQrAccessed(BigDecimal.valueOf(7.0));
        base.setQrRemoved(BigDecimal.valueOf(8.0));
        base.setQrCreated(BigDecimal.valueOf(9.0));
        base.setQrNow(BigDecimal.valueOf(10.0));

        assertEquals(BigDecimal.valueOf(0.0), base.getUrisAccessed());
        assertEquals(BigDecimal.valueOf(1.0), base.getUrisRemoved());
        assertEquals(BigDecimal.valueOf(2.0), base.getUrisCreated());
        assertEquals(BigDecimal.valueOf(3.0), base.getUrisNow());
        assertEquals(BigDecimal.valueOf(4.0), base.getServerUsage());
        assertEquals((Integer)5, base.getMemoryUsage());
        assertEquals(BigDecimal.valueOf(6.0), base.getCpuUsage());
        assertEquals(BigDecimal.valueOf(7.0), base.getQrAccessed());
        assertEquals(BigDecimal.valueOf(8.0), base.getQrRemoved());
        assertEquals(BigDecimal.valueOf(9.0), base.getQrCreated());
        assertEquals(BigDecimal.valueOf(10.0), base.getQrNow());
    }
}
