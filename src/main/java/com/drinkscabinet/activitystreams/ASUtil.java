package com.drinkscabinet.activitystreams;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ASUtil {

    public static Map<String, Object> flatten(String document) throws IOException {
        Object jsonObject = JsonUtils.fromString(document);
        // Create a context JSON map containing prefixes and definitions
        Map context = new HashMap();
        JsonLdOptions options = new JsonLdOptions();
        Object expanded = JsonLdProcessor.expand(jsonObject, options);
        Map<String, Object> data = (Map<String, Object>) ((List)expanded).get(0);
        return data;
//        // Expect this to be a Map containing a graph key
//        Map<String, Object> data = (Map<String, Object>) expanded;
//        if (data.containsKey("@graph")) {
//            List graphObj = (List) data.get("@graph");
//            return (Map<String, Object>) graphObj.get(0);
//        }
//        return null;
    }

    public static String prettyPrint(Map<String, Object> data) throws IOException {
        return JsonUtils.toPrettyString(data);
    }

    public static<T>  Optional<T> getData(Object o, Function<Object, T> converter, String mapKey) {
        if( o == null ) return Optional.empty();
        if( o instanceof List ) return getData(((List<?>) o).get(0), converter, mapKey);
        if( o instanceof Set ) return getData(((Set<?>) o).iterator().next(), converter, mapKey);
        if( o instanceof Map ) return getData(((Map<String, Object>) o).get(mapKey), converter, mapKey);
        else return Optional.ofNullable(converter.apply(o));
    }

    public static <T> List<T> getList(Object o, Function<Object, T> converter, String mapKey) {
        if( o == null ) return List.of();
        if( o instanceof Map ) return getList(((Map<?, ?>) o).get(mapKey), converter, mapKey);
        if( o instanceof Iterable ) {
            return StreamSupport.stream(((Iterable<?>) o).spliterator(), false).map(x -> getData(x, converter, mapKey).get()).collect(Collectors.toList());
        }
        return List.of();
    }

    public static Optional<URI> getURI(Map<String, Object> data, String key) {
        return getData(data.get(key), o -> URI.create((String)o), "@id");
    }

    public static Optional<String> getString(Map<String, Object> data, String key) {
        return getData(data.get(key), o -> (String)o, "@value");
    }

    public static Optional<Integer> getInt(Map<String, Object> data, String key) {
        return getData(data.get(key), o -> {
            if( o instanceof Integer ) return (Integer)o;
            if( o instanceof String ) return Integer.parseInt((String)o);
            return null;
        }, "@value");
    }

    public static Optional<? extends JsonLdObject> getObject(Map<String, Object> data, String key, String contextBase) {
        Object o = data.get(key);
        if( o instanceof List ) return Optional.of(new JsonLdObjectImpl((Map<String, Object>) ((List<?>) o).get(0), contextBase));
        if( o instanceof Map ) return Optional.of(new JsonLdObjectImpl((Map<String, Object>)o, contextBase));
        return Optional.empty();
    }

    public static Optional<Resource> getResource(Map<String, Object> data, String key) {

    }

    public static void main(String[] args) throws IOException {
        Map<String, Object> flat = flatten(link);
        System.out.println(ASUtil.prettyPrint(flat));

        ASLinkImpl link = new ASLinkImpl(flat);
        System.out.println(link.getType());
        System.out.println(link.getHref());
        System.out.println(link.getHeight());

        Map<String, Object> flatPreview = flatten(preview);
        System.out.println(ASUtil.prettyPrint(flatPreview));
        Optional<? extends JsonLdObject> object = ASUtil.getObject(flatPreview, "https://www.w3.org/ns/activitystreams#preview", "https://www.w3.org/ns/activitystreams#");
        System.out.println(object);
    }

    private static final String link = "{\n" +
            "  \"@context\": \"https://www.w3.org/ns/activitystreams\",\n" +
            "  \"type\": \"Link\",\n" +
            "  \"href\": \"http://example.org/abc\",\n" +
            "  \"hreflang\": \"en\",\n" +
            "  \"mediaType\": \"text/html\",\n" +
            "  \"name\": \"Preview\",\n" +
            "  \"height\": 100,\n" +
            "  \"width\": 100," +
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

}
