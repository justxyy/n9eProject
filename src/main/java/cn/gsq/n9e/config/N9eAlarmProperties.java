package cn.gsq.n9e.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.config.N9eAlarmProperties
 *
 * @author : gsq
 * @since : 2024-08-13 16:54
 **/
@Getter
@Setter
@Accessors(chain = true)
@ConfigurationProperties(prefix = "galaxy.n9e")
public class N9eAlarmProperties {

    private String url = "http://127.0.0.1:17000";

}
