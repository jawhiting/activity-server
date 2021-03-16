package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ASLinkImpl extends JsonLdObjectImpl implements ASLink {

    public ASLinkImpl(Map<String, Object> flattened) {
        super(flattened, "https://www.w3.org/ns/activitystreams#");
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
    public Optional<? extends JsonLdObject> getPreview() {
        return getObject("preview");
    }

    @Override
    public Set<String> getRel() {
        return Set.copyOf(ASUtil.getList(getData().get(getContextBase() + "rel"), Object::toString, "@value"));
    }
}
