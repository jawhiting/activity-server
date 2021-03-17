package com.drinkscabinet.activitystreams;

import java.net.URI;
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

    public Optional<Integer> getInt(String key) {
        return data.getInt(contextBase + key);
    }

    public Optional<Resource> getResource(String key) {
        return data.getResource(contextBase + key);
    }

    @Override
    public Optional<String> getAtId() {
        return data.getString("@id");
    }

    @Override
    public URI getType() {
        return data.getURI("@type").orElseThrow();
    }
}

