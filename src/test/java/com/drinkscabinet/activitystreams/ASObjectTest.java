package com.drinkscabinet.activitystreams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

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

    @Test
    public void testDateTime() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(dateTime);
        ASObject object = new ASObjectImpl(wrapper);
        assertEquals("2014-12-31T23:00-08:00", object.getStartTime().get().toString());
    }

    @Test
    public void testStringMap() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(contentMap);
        ASObject object = new ASObjectImpl(wrapper);
        Map<String, String> content = object.getContentMap();
        assertEquals(3, content.size());
        assertEquals("A <em>simple</em> note", content.get("en"));
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

    private static final String dateTime = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Event\",\n" +
            "  \"name\": \"Going-Away Party for Jim\",\n" +
            "  \"startTime\": \"2014-12-31T23:00:00-08:00\",\n" +
            "  \"endTime\": \"2015-01-01T06:00:00-08:00\"\n" +
            "}";

    private static final String contentMap = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"summary\": \"A simple note\",\n" +
            "  \"type\": \"Note\",\n" +
            "  \"contentMap\": {\n" +
            "    \"en\": \"A <em>simple</em> note\",\n" +
            "    \"es\": \"Una nota <em>sencilla</em>\",\n" +
            "    \"zh-Hans\": \"一段<em>简单的</em>笔记\"\n" +
            "  }\n" +
            "}";
}
