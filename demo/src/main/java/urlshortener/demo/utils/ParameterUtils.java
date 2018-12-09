package urlshortener.demo.utils;

import org.springframework.http.HttpStatus;
import urlshortener.demo.exception.InvalidRequestParametersException;

public class ParameterUtils {

    public static void checkParameter(String parameter){
        if(parameter == null || parameter.trim().isEmpty()){
            throw new InvalidRequestParametersException(HttpStatus.BAD_REQUEST.value(), "Invalid request parameter " + parameter);
        }
    }

}
