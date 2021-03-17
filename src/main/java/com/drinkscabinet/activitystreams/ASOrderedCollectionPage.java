package com.drinkscabinet.activitystreams;

import java.util.Optional;

public interface ASOrderedCollectionPage extends ASOrderedCollection, ASCollectionPage  {
    Optional<Integer> getStartIndex();
}
