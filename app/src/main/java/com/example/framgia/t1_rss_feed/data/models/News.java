package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
@Root(name = "rss", strict = false)
public class News {
    @Element(name = "version", required = false)
    private String mVersion;
    @Element(name = "channel")
    private Channel mChannel;

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public Channel getChannel() {
        return mChannel;
    }

    public void setChannel(Channel channel) {
        this.mChannel = channel;
    }
}

