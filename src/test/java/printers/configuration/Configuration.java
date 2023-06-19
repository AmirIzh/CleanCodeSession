package printers.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:app.properties")
@Import(printers.configurations.Configuration.class)
public class Configuration {
}
