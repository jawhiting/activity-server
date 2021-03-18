package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASActivity extends ASObject {
    Optional<Resource> getActor();
    Optional<ASObject> getObject();
    Optional<Resource> getTarget();
    Optional<Resource> getResult();
    Optional<Resource> getOrigin();
    Optional<Resource> getInstrument();
}
