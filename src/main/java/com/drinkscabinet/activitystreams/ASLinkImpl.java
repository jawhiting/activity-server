package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ASLinkImpl extends JsonLdObjectImpl implements ASLink {

    public ASLinkImpl(JsonWrapper data) {
        super(data, "https://www.w3.org/ns/activitystreams#");
    }

    @Override
    public Optional<URI> getHref() {
        return getUri("href");
    }

    @Override
    public Optional<String> getHreflang() {
        return getString("hreflang");
    }

    @Override
    public Optional<String> getMediaType() {
        return getString("mediaType");
    }

    @Override
    public Optional<String> getName() {
        return getString("name");
    }

    @Override
    public Optional<Integer> getHeight() {
        return getInt("height");
    }

    @Override
    public Optional<Integer> getWidth() {
        return getInt("width");
    }

    @Override
    public Optional<Resource> getPreview() {
        return getResource("preview");
    }

    @Override
    public Set<String> getRel() {
        return Set.copyOf(getData().getList(getContextBase() + "rel", Object::toString, "@value"));
    }
}
