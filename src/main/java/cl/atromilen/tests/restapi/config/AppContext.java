package cl.atromilen.tests.restapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alvarotromilen on 4/15/20.
 */
@Configuration
@ComponentScan(basePackages = {
        "cl.atromilen.tests.restapi.rest",
        "cl.atromilen.tests.restapi.service"
})
public class AppContext {
}
