package org.veritasopher.senizweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.veritasopher.senizweb.config.property.AppServer;

import javax.annotation.Resource;

/**
 * Web MVC Configuration
 * P.S. Configure CORS
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

    @Resource
    private AppServer appServer;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(appServer.getUrl())
                .allowCredentials(true);
    }

}
