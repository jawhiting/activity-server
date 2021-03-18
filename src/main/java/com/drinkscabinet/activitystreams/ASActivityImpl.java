package com.drinkscabinet.activitystreams;

import java.util.Optional;

public class ASActivityImpl extends ASObjectImpl implements ASActivity {

    public ASActivityImpl(JsonWrapper data) {
        super(data);
    }

    @Override
    public Optional<Resource> getActor() {
        return getResource("actor");
    }

    @Override
    public Optional<ASObject> getObject() {
        Optional<Resource> resource = getResource("object");
        if (resource.isPresent()) {
            if (resource.get().getResourceType() == Resource.ResourceType.OBJECT) {
                return Optional.of(new ASObjectImpl(resource.get().getObject().get()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Resource> getTarget() {
        return getResource("target");
    }

    @Override
    public Optional<Resource> getResult() {
        return getResource("result");
    }

    @Override
    public Optional<Resource> getOrigin() {
        return getResource("origin");
    }

    @Override
    public Optional<Resource> getInstrument() {
        return getResource("instrument");
    }
}
