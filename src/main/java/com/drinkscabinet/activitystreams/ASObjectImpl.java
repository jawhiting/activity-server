package com.drinkscabinet.activitystreams;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

public class ASObjectImpl extends JsonLdObjectImpl implements ASObject {

    public ASObjectImpl(JsonWrapper data) {
        super(data, "https://www.w3.org/ns/activitystreams#");
    }

    @Override
    public Optional<Resource> getAttachment() {
        return getResource("attachment");
    }

    @Override
    public Optional<Resource> getAttributedTo() {
        return getResource("attributedTo");
    }

    @Override
    public Optional<Resource> getAudience() {
        return getResource("audience");
    }

    @Override
    public Optional<String> getContent() {
        return getString("content");
    }

    @Override
    public Map<String, String> getContentMap() {
        return getStringMap("content", "@language", "@value");
    }

    @Override
    public Optional<String> getContext() {
        return getString("context");
    }

    @Override
    public Optional<String> getName() {
        return getString("name");
    }

    @Override
    public Map<String, String> getNameMap() {
        return getStringMap("name", "@language", "@value");
    }

    @Override
    public Optional<OffsetDateTime> getEndTime() {
        return getDateTime("endTime");
    }

    @Override
    public Optional<OffsetDateTime> getStartTime() {
        return getDateTime("startTime");
    }

    @Override
    public Optional<Resource> getGenerator() {
        return getResource("generator");
    }

    @Override
    public Optional<Resource> getIcon() {
        return getResource("icon");
    }

    @Override
    public Optional<Resource> getImage() {
        return getResource("image");
    }

    @Override
    public Optional<Resource> getInReplyTo() {
        return getResource("inReplyTo");
    }

    @Override
    public Optional<Resource> getLocation() {
        return getResource("location");
    }

    @Override
    public Optional<Resource> getPreview() {
        return getResource("preview");
    }

    @Override
    public Optional<OffsetDateTime> getPublished() {
        return getDateTime("published");
    }

    @Override
    public Optional<OffsetDateTime> getUpdated() {
        return getDateTime("updated");
    }

    @Override
    public Optional<Resource> getReplies() {
        return getResource("replies");
    }

    @Override
    public Optional<String> getSummary() {
        return getString("summary");
    }

    @Override
    public Map<String, String> getSummaryMap() {
        return getStringMap("summary", "@language", "@value");
    }

    @Override
    public Optional<Resource> getTags() {
        return getResource("tags");
    }

    @Override
    public Optional<Resource> getUrls() {
        return getResource("urls");
    }

    @Override
    public Optional<Resource> getTo() {
        return getResource("to");
    }
}
