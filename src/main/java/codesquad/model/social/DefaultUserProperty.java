package codesquad.model.social;

import java.util.Collection;
import java.util.Map;

public class DefaultUserProperty implements CustomUserProperty {

    private String name;
    private Map<String, String> userProperties;

    public DefaultUserProperty(String name, Map<String, String> userProperties) {
        this.name = name;
        this.userProperties = userProperties;
    }

    @Override
    public Collection<String> getProperties() {
        return this.userProperties.values();
    }

    @Override
    public String getProperty(String key) {
        return this.userProperties.get(key);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
