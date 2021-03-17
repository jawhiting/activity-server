package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Optional;

/**
 * Link or Object or direct URI
 */
public interface Resource {
    Optional<URI> getUri();
    Optional<ASLink> getLink();
    Optional<ASObject> getObject();
}
