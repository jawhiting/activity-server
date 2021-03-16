package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public class JsonLdObjectImpl implements JsonLdObject {

    private final Map<String, Object> data;
    private final String contextBase;

    public JsonLdObjectImpl(Map<String, Object> flattened, String contextBase) {
        // Pass in the node below graph
        data = flattened;
        this.contextBase = contextBase;
    }

    public String getContextBase() {
        return contextBase;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Optional<URI> getUri(String key) {
        return ASUtil.getURI(data, contextBase + key);
    }

    public Optional<String> getString(String key) {
        return ASUtil.getString(data, contextBase + key);
    }

    public Optional<Integer> getInt(String key) {
        return ASUtil.getInt(data, contextBase + key);
    }

    public Optional<? extends JsonLdObject> getObject(String key) {
        return ASUtil.getObject(data, contextBase + key, contextBase);
    }

    @Override
    public Optional<URI> getId() {
        return ASUtil.getURI(data, "@id");
    }

    @Override
    public URI getType() {
        return ASUtil.getURI(data, "@type").orElseThrow();
    }
}

