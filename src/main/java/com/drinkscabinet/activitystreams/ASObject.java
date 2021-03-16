package com.drinkscabinet.activitystreams;

public interface ASObject extends JsonLdObject {
    String getAttachment();
    String getAttributedTo();
    String getAudience();
    String getContent();
}
