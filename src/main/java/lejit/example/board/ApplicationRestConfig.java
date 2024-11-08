package lejit.example.board;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
public class ApplicationRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        cors.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(30000);
        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
    }

    ResponseEntityExceptionHandler f;
}
