package com.drinkscabinet.activitystreams;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ResourceImpl implements Resource {

    private Optional<URI> uri = Optional.empty();
    private Optional<JsonWrapper> wrapper = Optional.empty();
    private Optional<List<? extends Resource>> list = Optional.empty();
    private ResourceType resourceType;

    public ResourceImpl(URI uri) {
        resourceType = ResourceType.URI;
        this.uri = Optional.ofNullable(uri);
    }

    public ResourceImpl(JsonWrapper object) {
        resourceType = ResourceType.OBJECT;
        this.wrapper = Optional.ofNullable(object);
    }

    public ResourceImpl(Collection<? extends Resource> resources) {
        resourceType = ResourceType.LIST;
        this.list = Optional.of(new ArrayList<>(resources));
    }

    @Override
    public String toString() {
        return "ResourceImpl{" +
                "resourceType=" + resourceType +
                ", wrapper=" + wrapper +
                ", list=" + list +
                ", uri=" + uri +
                '}';
    }

    @Override
    public Optional<URI> getUri() {
        return uri;
    }

    @Override
    public Optional<JsonWrapper> getObject() {
        return wrapper;
    }

    @Override
    public Optional<List<? extends Resource>> getList() {
        return list;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
