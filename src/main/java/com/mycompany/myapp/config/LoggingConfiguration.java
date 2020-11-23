package com.mycompany.myapp.config;

import static io.github.jhipster.config.logging.LoggingUtils.addContextListener;
import static io.github.jhipster.config.logging.LoggingUtils.addJsonConsoleAppender;
import static io.github.jhipster.config.logging.LoggingUtils.addLogstashTcpSocketAppender;
import static io.github.jhipster.config.logging.LoggingUtils.setMetricsMarkerLogbackFilter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.qos.logback.classic.LoggerContext;
import io.github.jhipster.config.JHipsterProperties;

/*
 * Configures the console and Logstash log appenders from the app properties
 */
@Configuration
public class LoggingConfiguration {
    private static final Logger log = LoggerFactory.getLogger(LoggingConfiguration.class);

    public LoggingConfiguration(@Value("${spring.application.name}") String appName,
                                @Value("${server.port}") String serverPort,
                                @Value("${spring.datasource.url}") String springDatasourceUrl,
                                @Value("${spring.datasource.username}") String springDatasourceUsername,
                                @Value("${spring.datasource.password}") String springDatasourcePassword,
                                JHipsterProperties jHipsterProperties,
                                ObjectMapper mapper) throws JsonProcessingException {

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        Map<String, String> map = new HashMap<>();
        map.put("app_name", appName);
        map.put("app_port", serverPort);
        
        //TODO: jd - please remove experimental code - for testing k8s settings of ConfigMap and Secret
        map.put("app_datasource_url", springDatasourceUrl);
        map.put("app_datasource_username", springDatasourceUsername);
        map.put("app_datasource_password", springDatasourcePassword);
//      System.out.println("map: " + map);
        log.info("map: " + map);
        
        String customFields = mapper.writeValueAsString(map);

        JHipsterProperties.Logging loggingProperties = jHipsterProperties.getLogging();
        JHipsterProperties.Logging.Logstash logstashProperties = loggingProperties.getLogstash();

        if (loggingProperties.isUseJsonFormat()) {
            addJsonConsoleAppender(context, customFields);
        }
        if (logstashProperties.isEnabled()) {
            addLogstashTcpSocketAppender(context, customFields, logstashProperties);
        }
        if (loggingProperties.isUseJsonFormat() || logstashProperties.isEnabled()) {
            addContextListener(context, customFields, loggingProperties);
        }
        if (jHipsterProperties.getMetrics().getLogs().isEnabled()) {
            setMetricsMarkerLogbackFilter(context, loggingProperties.isUseJsonFormat());
        }
    }
}
