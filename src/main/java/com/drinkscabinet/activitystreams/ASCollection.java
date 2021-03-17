package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASCollection extends ASObject {
    Optional<Integer> getTotalItems();

}
