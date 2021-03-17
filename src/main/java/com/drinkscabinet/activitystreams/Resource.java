package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Link or Object or direct URI
 */
public interface Resource {
    Optional<URI> getUri();
    Optional<JsonWrapper> getObject();
    Optional<List<? extends Resource>> getList();
    ResourceType getResourceType();

    public enum ResourceType {
        URI, OBJECT, LIST
    }
}
