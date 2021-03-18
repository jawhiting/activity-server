package com.drinkscabinet.activitystreams;

import java.net.URI;
import java.util.Optional;

public interface JsonLdObject {
    Optional<String> getId();
    URI getType();
}
