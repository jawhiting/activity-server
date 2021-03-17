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

public class JsonWrapper {

    private String document;
    private List<Map<String, Object>> flattened;
    private Map<String, Object> data;
    private Map<String, Map<String, Object>> objects = new HashMap<>();


    public JsonWrapper(String document) throws IOException {
        this.document = document;
        Object jsonObject = JsonUtils.fromString(document);
        // Create a context JSON map containing prefixes and definitions
        JsonLdOptions options = new JsonLdOptions();
        flattened = (List)JsonLdProcessor.flatten(jsonObject, options);
        // The list elements are all the objects, starting with the main one.
        data = flattened.get(0);
        // Now index all the objects in case we need to access them by id later
        for (Map<String, Object> stringObjectMap : flattened) {
            // get the id of this object
            String id = (String) stringObjectMap.get("@id");
            objects.put(id, stringObjectMap);
        }
    }

    public String toString() {
        try {
            return JsonUtils.toPrettyString(flattened);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private JsonWrapper(String document, Map<String, Object> data, List<Map<String, Object>> flattened, Map<String, Map<String, Object>> objects) {
        this.document = document;
        this.flattened = flattened;
        this.data = data;
        this.objects = objects;
    }

    private JsonWrapper getSubObject(String id) {
        return new JsonWrapper(document, objects.get(id), flattened, objects);
    }

    private static <T> Optional<T> getData(Object o, Function<Object, T> converter, String mapKey) {
        if( o == null ) return Optional.empty();
        if( o instanceof List ) return getData(((List<?>) o).get(0), converter, mapKey);
        if( o instanceof Set ) return getData(((Set<?>) o).iterator().next(), converter, mapKey);
        if( o instanceof Map ) return getData(((Map<String, Object>) o).get(mapKey), converter, mapKey);
        else return Optional.ofNullable(converter.apply(o));
    }

    public Optional<URI> getURI(String key) {
        return getData(data.get(key), o -> URI.create((String)o), "@id");
    }

    public Optional<String> getString(String key) {
        return getData(data.get(key), o -> (String)o, "@value");
    }

    public Optional<Integer> getInt(String key) {
        return getData(data.get(key), o -> {
            if( o instanceof Integer ) return (Integer)o;
            if( o instanceof String ) return Integer.parseInt((String)o);
            return null;
        }, "@value");
    }

    public <T> List<T> getList(String key, Function<Object, T> converter, String mapKey) {
        Object o = data.get(key);
        if( o == null ) return List.of();
        if( o instanceof Iterable ) {
            return StreamSupport.stream(((Iterable<?>) o).spliterator(), false).map(x -> getData(x, converter, mapKey).get()).collect(Collectors.toList());
        }
        return List.of();
    }

    public Optional<Resource> getResource(String key) {
        Object o = data.get(key);
        if ( o == null ) return Optional.empty();
        return interpretResource((List<Map<String, Object>>) o);
    }

    private Optional<Resource> interpretResource(List<Map<String, Object>> list) {
        if( list.size() == 1 ) {
            return interpretResource(list.get(0));
        }
        else {
            // List resource
            List<Resource> items = new LinkedList<>();
            for (Map<String, Object> stringObjectMap : list) {
                Optional<? extends Resource> resource = interpretResource(stringObjectMap);
                resource.ifPresent(items::add);
            }
            if( items.isEmpty() ) {
                return Optional.empty();
            }
            else {
                return Optional.of(new ResourceImpl(items));
            }
        }
    }

    private Optional<Resource> interpretResource(Map<String, Object> data) {
        // object resource
        Object id = data.get("@id");
        if( id == null ) return Optional.empty();
        if( id instanceof String ) {
            if( ((String) id).startsWith("_")) {
                // reference to flattened object
                return Optional.of(new ResourceImpl(getSubObject((String) id)));
            }
            else {
                return Optional.of(new ResourceImpl(URI.create((String) id)));
            }
        }
        else {
            // shouldn't happen
            throw new RuntimeException("@id field is not a string: " + id.getClass() + " value: " + id);
        }
    }

    public static void main(String[] args) throws IOException {
        JsonWrapper wrapper = new JsonWrapper(url1);
        System.out.println(wrapper);
        Resource r1 = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        System.out.println(r1);
        wrapper = new JsonWrapper(url2);
        System.out.println(wrapper);
        Resource r2 = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        System.out.println(r2);
        wrapper = new JsonWrapper(url3);
        System.out.println(wrapper);
        Resource r3 = wrapper.getResource("https://www.w3.org/ns/activitystreams#url").get();
        System.out.println(r3);
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
