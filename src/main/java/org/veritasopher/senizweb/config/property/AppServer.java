package org.veritasopher.senizweb.config.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * App Server Property
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@Component
@Getter
public class AppServer {

    @Value("${appserver.url}")
    private String url;
}
