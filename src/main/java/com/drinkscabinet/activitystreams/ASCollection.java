package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASCollection extends ASObject {
    Optional<Integer> getTotalItems();
    Optional<Resource> getCurrent();
    Optional<Resource> getFirst();
    Optional<Resource> getLast();
    Optional<Resource> getItems();
}
