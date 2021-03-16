package com.drinkscabinet;

import com.drinkscabinet.activitystreams.ASLink;
import com.drinkscabinet.activitystreams.ASLinkImpl;
import com.drinkscabinet.activitystreams.ASUtil;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Set;

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
            "  \"rel\": [\"canonical\", \"preview\"]\n" +
            "}";

    @Test
    public void testParse() throws IOException {
        ASLink link = new ASLinkImpl(ASUtil.flatten(linkJson));

        assertTrue(link.getId().isEmpty());

        assertEquals("https://www.w3.org/ns/activitystreams#Link", link.getType().toString());
        assertEquals("http://example.org/abc", link.getHref().get().toString());
        assertEquals("en", link.getHreflang().get());
        assertEquals(100, link.getHeight().get());
        assertEquals(101, link.getWidth().get());
        assertEquals("text/html", link.getMediaType().get());
        assertEquals("Preview", link.getName().get());
        assertEquals("en", link.getHreflang().get());
        assertThat(link.getRel(), containsInAnyOrder("canonical", "preview"));
    }
}
