package urlshortener.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = {"urlshortener.demo" })
@Import({JacksonConfiguration.class, SwaggerDocumentationConfig.class})
public class PersistenceContext {

	@Autowired
    protected JdbcTemplate jdbc;
	
}
