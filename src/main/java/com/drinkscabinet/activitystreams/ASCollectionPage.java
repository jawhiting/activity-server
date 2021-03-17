package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASCollectionPage extends ASCollection {
    Optional<Resource> getPartOf();
    Optional<JsonLdObject> getNext();
    Optional<JsonLdObject> getPrev();
}
