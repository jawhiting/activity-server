package com.drinkscabinet.activitystreams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASObjectTest {

    @Test
    public void testReplies() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(replies);
        // find the summary node
        JsonWrapper main = wrapper.getNodes()
                .stream().filter(w -> w.getId().equals("http://www.test.example/notes/1")).findFirst().get();
        ASObject object = new ASObjectImpl(main);

        assertEquals("A simple note", object.getSummary().get());
        assertEquals("https://www.w3.org/ns/activitystreams#Note", object.getType().toString());

        Resource r = object.getReplies().get();
        assertEquals(Resource.ResourceType.OBJECT, r.getResourceType());
    }


    private static final String replies = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"summary\": \"A simple note\",\n" +
            "  \"type\": \"Note\",\n" +
            "  \"id\": \"http://www.test.example/notes/1\",\n" +
            "  \"content\": \"I am fine.\",\n" +
            "  \"replies\": {\n" +
            "    \"type\": \"Collection\",\n" +
            "    \"totalItems\": 1,\n" +
            "    \"items\": [\n" +
            "      {\n" +
            "        \"summary\": \"A response to the note\",\n" +
            "        \"type\": \"Note\",\n" +
            "        \"content\": \"I am glad to hear it.\",\n" +
            "        \"inReplyTo\": \"http://www.test.example/notes/1\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
}
