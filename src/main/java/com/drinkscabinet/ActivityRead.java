package com.drinkscabinet;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivityRead {

    private static final String testActivity = "{\"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            " \"type\": \"Note\",\n" +
            " \"to\": [\"https://chatty.example/ben/\"],\n" +
            " \"attributedTo\": \"https://social.example/alyssa/\",\n" +
            " \"content\": \"Say, did you finish reading that book I lent you?\"}";

    private static final String testActor = "{\n" +
            "      \"@context\": [\"https://www.w3.org/ns/activitystreams\",\n" +
            "                   {\"@language\": \"ja\"}],\n" +
            "      \"type\": \"Person\",\n" +
            "      \"id\": \"https://kenzoishii.example.com/\",\n" +
            "      \"following\": \"https://kenzoishii.example.com/following.json\",\n" +
            "      \"followers\": \"https://kenzoishii.example.com/followers.json\",\n" +
            "      \"liked\": \"https://kenzoishii.example.com/liked.json\",\n" +
            "      \"inbox\": \"https://kenzoishii.example.com/inbox.json\",\n" +
            "      \"outbox\": \"https://kenzoishii.example.com/feed.json\",\n" +
            "      \"preferredUsername\": \"kenzoishii\",\n" +
            "      \"name\": \"石井健蔵\",\n" +
            "      \"summary\": \"この方はただの例です\",\n" +
            "      \"icon\": [\n" +
            "        \"https://kenzoishii.example.com/image/165987aklre4\"\n" +
            "      ]\n" +
            "    }";

    private static final String testContextObject = "{\n" +
            "  \"@context\": {\n" +
            "     \"@vocab\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "     \"ext\": \"https://canine-extension.example/terms/\",\n" +
            "     \"@language\": \"en\"\n" +
            "  },\n" +
            "  \"summary\": \"A note\",\n" +
            "  \"type\": \"Note\",\n" +
            "  \"content\": \"My dog has fleas.\",\n" +
            "  \"ext:nose\": 0,\n" +
            "  \"ext:smell\": \"terrible\"\n" +
            "}";

    private static final String link = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Link\",\n" +
            "  \"href\": \"http://example.org/abc\",\n" +
            "  \"hreflang\": \"en\",\n" +
            "  \"mediaType\": \"text/html\",\n" +
            "  \"name\": \"Preview\",\n" +
            "  \"rel\": [\"canonical\", \"preview\"]\n" +
            "}";

    private static final String preview = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Video\",\n" +
            "  \"name\": \"Cool New Movie\",\n" +
            "  \"duration\": \"PT2H30M\",\n" +
            "  \"preview\": {\n" +
            "    \"type\": \"Video\",\n" +
            "    \"name\": \"Trailer\",\n" +
            "    \"duration\": \"PT1M\",\n" +
            "    \"url\": {\n" +
            "      \"href\": \"http://example.org/trailer.mkv\",\n" +
            "      \"mediaType\": \"video/mkv\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static void main(String[] args) throws IOException {
        Object jsonObject = JsonUtils.fromString(preview);
        // Create a context JSON map containing prefixes and definitions
        Map context = new HashMap();
        JsonLdOptions options = new JsonLdOptions();
// Customise options...
// Call whichever JSONLD function you want! (e.g. compact)
//        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
//// Print out the result (or don't, it's your call!)
//        System.out.println(JsonUtils.toPrettyString(compact));
//
//        Object expand = JsonLdProcessor.expand(jsonObject, options);
//        System.out.println(JsonUtils.toPrettyString(expand));

        Object flatten = JsonLdProcessor.expand(jsonObject, options);
        System.out.println(JsonUtils.toPrettyString(flatten));
    }
}
