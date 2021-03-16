package com.drinkscabinet;

import com.apicatalog.jsonld.JsonLdError;
import com.apicatalog.jsonld.JsonLdOptions;
import com.apicatalog.jsonld.api.FlatteningApi;
import com.apicatalog.jsonld.document.Document;
import com.apicatalog.jsonld.document.JsonDocument;
import jakarta.json.JsonStructure;

import java.io.IOException;
import java.io.StringReader;

public class ActivityReadTitanium {

    private static final String testActivity = "{\"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            " \"type\": \"Note\",\n" +
            " \"to\": [\"https://chatty.example/ben/\"],\n" +
            " \"attributedTo\": \"https://social.example/alyssa/\",\n" +
            " \"content\": \"Say, did you finish reading that book I lent you?\"}";

    private static final String test2 = "{\n" +
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

    private static final String test11 = "" +
            "{\n" +
            "  \"@context\": \"https://json-ld.org/contexts/person.jsonld\",\n" +
            "  \"name\": \"Manu Sporny\",\n" +
            "  \"homepage\": \"http://manu.sporny.org/\",\n" +
            "  \"image\": \"http://manu.sporny.org/images/manu.png\"\n" +
            "}";

    private static final String minimal = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"summary\": \"Martin created an image\",\n" +
            "  \"type\": \"Create\",\n" +
            "  \"actor\": \"http://www.test.example/martin\",\n" +
            "  \"object\": \"http://example.org/foo.jpg\"\n" +
            "}";

    public static void main(String[] args) throws IOException, JsonLdError {

        // GET /ordinary-json-document.json HTTP/1.1
        //Host: example.com
        //Accept: application/ld+json;profile=http://www.w3.org/ns/json-ld#expanded

        // Local document
        Document document = JsonDocument.of(new StringReader(minimal));

        JsonLdOptions options = new JsonLdOptions(MyHttpLoader.defaultInstance());
        JsonStructure structure = new FlatteningApi(document).options(options).get();
        System.out.println(structure);
//        JsonStructure jsonStructure = JsonLd.flatten(document).get();
//        System.out.println(jsonStructure);


    }

}
