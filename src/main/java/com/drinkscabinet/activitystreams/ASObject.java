package com.drinkscabinet.activitystreams;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

public interface ASObject extends JsonLdObject {
    Optional<Resource> getAttachment();
    Optional<Resource> getAttributedTo();
    Optional<Resource> getAudience();
    Optional<String> getContent();
    Map<String, String> getContentMap();
    Optional<String> getContext();
    Optional<String> getName();
    Map<String, String> getNameMap();
    Optional<OffsetDateTime> getEndTime();
    Optional<OffsetDateTime> getStartTime();
    Optional<Resource> getGenerator();
    Optional<Resource> getIcon();
    Optional<Resource> getImage();
    Optional<Resource> getInReplyTo();
    Optional<Resource> getLocation();
    Optional<Resource> getPreview();
    Optional<OffsetDateTime> getPublished();
    Optional<OffsetDateTime> getUpdated();
    Optional<Resource> getReplies();
    Optional<String> getSummary();
    Map<String, String> getSummaryMap();
    Optional<Resource> getTags();
    Optional<Resource> getUrls();
    Optional<Resource> getTo();
}
