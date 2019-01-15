package urlshortener.demo.exceptions;

import org.junit.Test;
import urlshortener.demo.exception.NotFoundException;

import static org.junit.Assert.assertEquals;

public class ExceptionTest {

    @Test
    public void testNotFoundException(){
        String MSG = "Test message";
        NotFoundException ex = new NotFoundException(100, MSG);

        assertEquals(MSG, ex.getMessage());
    }

}
