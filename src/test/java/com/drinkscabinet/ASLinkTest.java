package com.drinkscabinet;

import com.drinkscabinet.activitystreams.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ASLinkTest {

    private static final String linkJson = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Link\",\n" +
            "  \"href\": \"http://example.org/abc\",\n" +
            "  \"hreflang\": \"en\",\n" +
            "  \"mediaType\": \"text/html\",\n" +
            "  \"name\": \"Preview\",\n" +
            "  \"height\": 100,\n" +
            "  \"width\": 101," +
            "  \"preview\": {\n" +
            "    \"type\": \"Video\",\n" +
            "    \"name\": \"Trailer\",\n" +
            "    \"duration\": \"PT1M\",\n" +
            "    \"url\": {\n" +
            "      \"href\": \"http://example.org/trailer.mkv\",\n" +
            "      \"mediaType\": \"video/mkv\"\n" +
            "    }\n" +
            "  }," +
            "  \"rel\": [\"canonical\", \"preview\"]\n" +
            "}";

    @Test
    public void testParse() throws IOException {
        ASLink link = new ASLinkImpl(new JsonWrapper(linkJson));

        assertEquals("https://www.w3.org/ns/activitystreams#Link", link.getType().toString());
        assertEquals("http://example.org/abc", link.getHref().get().toString());
        assertEquals("en", link.getHreflang().get());
        assertEquals(100, link.getHeight().get());
        assertEquals(101, link.getWidth().get());
        assertEquals("text/html", link.getMediaType().get());
        assertEquals("Preview", link.getName().get());
        assertEquals("en", link.getHreflang().get());
        assertThat(link.getRel(), containsInAnyOrder("canonical", "preview"));
        Resource preview = link.getPreview().get();
        assertEquals(Resource.ResourceType.OBJECT, preview.getResourceType());
        JsonLdObjectImpl previewObject = new JsonLdObjectImpl(preview.getObject().get(), "https://www.w3.org/ns/activitystreams#" );
        assertEquals("https://www.w3.org/ns/activitystreams#Video", previewObject.getType().toString());
    }
}
