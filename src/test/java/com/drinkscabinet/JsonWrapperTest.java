package com.drinkscabinet;

import com.drinkscabinet.activitystreams.ASLink;
import com.drinkscabinet.activitystreams.ASLinkImpl;
import com.drinkscabinet.activitystreams.JsonWrapper;
import com.drinkscabinet.activitystreams.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWrapperTest {

    @Test
    public void testType() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(url1);
        assertEquals("https://www.w3.org/ns/activitystreams#Document", wrapper.getType().toString());
    }

    @Test
    public void testDateTime() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(dateTime);
        assertEquals("2014-12-31T23:00-08:00", wrapper.getDateTime("https://www.w3.org/ns/activitystreams#startTime").get().toString());
    }

    @Test
    public void testStringMap() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(contentMap);
        Map<String, String> content = wrapper.getStringMap("https://www.w3.org/ns/activitystreams#content", "@language", "@value");
        assertEquals(3, content.size());
        assertEquals("A <em>simple</em> note", content.get("en"));
    }

    @Test
    public void testUriResource() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(url1);
        assertEquals("http://example.org/4q-sales-forecast.pdf", wrapper.getURI("https://www.w3.org/ns/activitystreams#url").get().toString());
        Resource r = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        assertEquals(Resource.ResourceType.URI, r.getResourceType());
        assertEquals("http://example.org/4q-sales-forecast.pdf", r.getUri().get().toString());
    }

    @Test
    public void testObjectResource() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(url2);
        Resource r = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        assertEquals(Resource.ResourceType.OBJECT, r.getResourceType());
        // Wrap it in a link
        ASLink link = new ASLinkImpl(r.getObject().get());
        assertEquals("http://example.org/4q-sales-forecast.pdf", link.getHref().get().toString());
    }

    @Test
    public void testListResource() throws IOException {
        JsonWrapper wrapper = new JsonWrapper(url3);
        Resource r = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        assertEquals(Resource.ResourceType.LIST, r.getResourceType());
        // Wrap it in a link
        ASLink link = new ASLinkImpl(r.getList().get().get(0).getObject().get());
        assertEquals("http://example.org/4q-sales-forecast.pdf", link.getHref().get().toString());
        assertEquals("https://www.w3.org/ns/activitystreams#Link", link.getType().toString());
        assertEquals("application/pdf", link.getMediaType().get());

        ASLink link2 = new ASLinkImpl(r.getList().get().get(1).getObject().get());
        assertEquals("http://example.org/4q-sales-forecast.html", link2.getHref().get().toString());
        assertEquals("https://www.w3.org/ns/activitystreams#Link", link2.getType().toString());
        assertEquals("text/html", link2.getMediaType().get());
    }

    private static final String url1 = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Document\",\n" +
            "  \"name\": \"4Q Sales Forecast\",\n" +
            "  \"url\": \"http://example.org/4q-sales-forecast.pdf\"\n" +
            "}";

    private static final String url2 = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Document\",\n" +
            "  \"name\": \"4Q Sales Forecast\",\n" +
            "  \"url\": {\n" +
            "    \"type\": \"Link\",\n" +
            "    \"href\": \"http://example.org/4q-sales-forecast.pdf\"\n" +
            "  }\n" +
            "}";

    private static final String url3 = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Document\",\n" +
            "  \"name\": \"4Q Sales Forecast\",\n" +
            "  \"url\": [\n" +
            "    {\n" +
            "      \"type\": \"Link\",\n" +
            "      \"href\": \"http://example.org/4q-sales-forecast.pdf\",\n" +
            "      \"mediaType\": \"application/pdf\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"Link\",\n" +
            "      \"href\": \"http://example.org/4q-sales-forecast.html\",\n" +
            "      \"mediaType\": \"text/html\"\n" +
            "    }\n" +
            "  ]\n" +
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
