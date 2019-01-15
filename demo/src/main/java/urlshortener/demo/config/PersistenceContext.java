package urlshortener.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JacksonConfiguration.class, SwaggerDocumentationConfig.class})
public class PersistenceContext {

}
