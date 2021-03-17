package com.drinkscabinet;

import com.drinkscabinet.activitystreams.ASLink;
import com.drinkscabinet.activitystreams.ASLinkImpl;
import com.drinkscabinet.activitystreams.JsonWrapper;
import com.drinkscabinet.activitystreams.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWrapperTest {

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
}
