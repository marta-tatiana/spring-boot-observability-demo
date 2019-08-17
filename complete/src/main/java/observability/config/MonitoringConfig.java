package observability.config;

import io.micrometer.signalfx.SignalFxConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MonitoringConfig implements SignalFxConfig {

    @Value("${SF_ACCESS_TOKEN}")
    private String accessToken;

    @Value("${SF_URI}")
    private String uri;

    @Override
    public String accessToken() {
        return accessToken;
    }

    @Override
    public String uri() {
        return uri;
    }

    @Override
    public String get(String s) {
        return null;
    }

}
