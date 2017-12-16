package codesquad.model.social;

import java.util.Collection;

public interface CustomUserProperty {

    Collection<String> getProperties();

    String getProperty(String key);

    String getName();

}
