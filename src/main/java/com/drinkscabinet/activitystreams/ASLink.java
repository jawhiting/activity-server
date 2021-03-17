package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

public interface ASLink extends JsonLdObject {
    Optional<URI> getHref();
    Optional<String> getHreflang();
    Optional<String> getMediaType();
    Optional<String> getName();
    Optional<Integer> getHeight();
    Optional<Integer> getWidth();
    Optional<Resource> getPreview();
    Set<String> getRel();
}
