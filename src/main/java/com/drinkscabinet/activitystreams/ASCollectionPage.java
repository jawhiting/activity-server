package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASCollectionPage extends ASCollection {
    Optional<JsonLdObject> getPartOf();
    Optional<JsonLdObject> getNext();
    Optional<JsonLdObject> getPrev();
}
