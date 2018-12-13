package urlshortener.demo.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApiResponseMessageTest {

    private ApiResponseMessage responseEmpty, responseError, responseWarn, responseInfo, responseOK, responseBusy;

    @Before
    public void init(){
        responseEmpty = new ApiResponseMessage();
        responseError = new ApiResponseMessage(ApiResponseMessage.ERROR, "ERROR");
        responseWarn = new ApiResponseMessage(ApiResponseMessage.WARNING, "WARN");
        responseInfo = new ApiResponseMessage(ApiResponseMessage.INFO, "INFO");
        responseOK = new ApiResponseMessage(ApiResponseMessage.OK, "OK");
        responseBusy = new ApiResponseMessage(ApiResponseMessage.TOO_BUSY, "BUSY");
    }

    @Test
    public void testCodeAndMessage(){
        assertEquals(0, responseEmpty.getCode());
        assertEquals(ApiResponseMessage.ERROR, responseError.getCode());
        assertEquals(ApiResponseMessage.WARNING, responseWarn.getCode());
        assertEquals(ApiResponseMessage.INFO, responseInfo.getCode());
        assertEquals(ApiResponseMessage.OK, responseOK.getCode());
        assertEquals(ApiResponseMessage.TOO_BUSY, responseBusy.getCode());

        assertEquals("ERROR", responseError.getMessage());
    }

    @Test
    public void testType(){
        assertNull(responseEmpty.getType());
        assertEquals("error", responseError.getType());
        assertEquals("warning", responseWarn.getType());
        assertEquals("info", responseInfo.getType());
        assertEquals("ok", responseOK.getType());
        assertEquals("too busy", responseBusy.getType());
        assertEquals("unknown", new ApiResponseMessage(-1, "ABC").getType());

        assertEquals("ERROR", responseError.getMessage());

    }

    @Test
    public void testSetters(){
        responseEmpty.setCode(responseError.getCode());
        responseEmpty.setMessage(responseError.getMessage());
        responseEmpty.setType(responseError.getType());

        assertEquals(responseError.getCode(), responseEmpty.getCode());
        assertEquals(responseError.getMessage(), responseEmpty.getMessage());
        assertEquals(responseError.getType(), responseEmpty.getType());
    }
}
