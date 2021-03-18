package com.drinkscabinet.activitystreams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASActivityTest {

    @Test
    public void testActivity() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(activity);
        ASActivity activity = new ASActivityImpl(wrapper);
        assertEquals("https://www.w3.org/ns/activitystreams#Activity", activity.getType().toString());
        assertEquals("Sally did something to a note", activity.getSummary().get());
        Optional<Resource> actor = activity.getActor();
        ASObject actorObject = new ASObjectImpl(actor.get().getObject().get());
        assertEquals("Sally", actorObject.getName().get());
        ASObject object = activity.getObject().get();
        assertEquals("A Note", object.getName().get());
    }

    private static final String activity = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Activity\",\n" +
            "  \"summary\": \"Sally did something to a note\",\n" +
            "  \"actor\": {\n" +
            "    \"type\": \"Person\",\n" +
            "    \"name\": \"Sally\"\n" +
            "  },\n" +
            "  \"object\": {\n" +
            "    \"type\": \"Note\",\n" +
            "    \"name\": \"A Note\"\n" +
            "  }\n" +
            "}";
}
