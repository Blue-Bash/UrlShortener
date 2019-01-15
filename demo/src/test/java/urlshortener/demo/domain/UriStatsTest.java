package urlshortener.demo.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UriStatsTest {

    @Test
    public void testUriStatsFields(){
        URIStats base = new URIStats();
        base.setUriAccesses(BigDecimal.valueOf(0.0));

        assertEquals(BigDecimal.valueOf(0.0), base.getUriAccesses());
    }
}
