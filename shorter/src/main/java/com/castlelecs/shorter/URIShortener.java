package com.castlelecs.shorter;

import java.net.URI;
import java.util.UUID;

public class URIShortener {

    private static String DEFAULT_BASE = "https://shortener/";
    private String base;

    public URIShortener() {
        this(DEFAULT_BASE);
    }

    public URIShortener(String base) {
        this.base = base;
    }

    public URI convert(String longStringURI) {
        StringBuilder builder = new StringBuilder(base);

        if (base.charAt(base.length() - 1) != '/') {
            builder.append("/");
        }

        builder.append(UUID.randomUUID().toString().substring(0, 5));

        try {
            return new URI(builder.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
