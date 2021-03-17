package com.drinkscabinet.activitystreams;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ASObject extends JsonLdObject {
    Optional<JsonLdObject> getAttachment();
    Optional<JsonLdObject> getAttributedTo();
    Optional<JsonLdObject> getAudience();
    Optional<String> getContent();
    Map<String, String> getContentMap();
    Optional<String> getContext();
    Optional<String> getName();
    Map<String, String> getNameMap();
    Optional<OffsetDateTime> getEndTime();
    Optional<OffsetDateTime> getStartTime();
    Optional<JsonLdObject> getGenerator();
    List<JsonLdObject> getIcon();
    List<JsonLdObject> getImage();
    List<JsonLdObject> getInReplyTo();
    List<JsonLdObject> getLocation();
    List<JsonLdObject> getPreview();
    Optional<OffsetDateTime> getPublished();
    Optional<OffsetDateTime> getUpdated();
    Optional<ASCollection> getReplies();
    Optional<String> getSummary();
    Map<String, String> getSummaryMap();
    List<Resource> getTags();
    List<Resource> getUrls();
    List<Resource> getTo();
}
