package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

public class JsonLdObjectImpl implements JsonLdObject {

    private final JsonWrapper data;
    private final String contextBase;

    public JsonLdObjectImpl(JsonWrapper data, String contextBase) {
        // Pass in the node below graph
        this.data = data;
        this.contextBase = contextBase;
    }

    public String getContextBase() {
        return contextBase;
    }

    public JsonWrapper getData() {
        return data;
    }

    public Optional<URI> getUri(String key) {
        return data.getURI(contextBase + key);
    }

    public Optional<String> getString(String key) {
        return data.getString(contextBase + key);
    }

    public Map<String, String> getStringMap(String key, String mapKey, String mapValue) {
        return data.getStringMap(contextBase + key, mapKey, mapValue);
    }

    public Optional<OffsetDateTime> getDateTime(String key) {
        return data.getDateTime(contextBase + key);
    }

    public Optional<Integer> getInt(String key) {
        return data.getInt(contextBase + key);
    }

    public Optional<Resource> getResource(String key) {
        return data.getResource(contextBase + key);
    }

    @Override
    public Optional<String> getId() {
        return data.getString("@id");
    }

    @Override
    public URI getType() {
        return data.getURI("@type").orElseThrow();
    }
}

