package com.castlelecs.shorter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

public class URIShortenerTests {

    @Test
    public void convertionFromLongToShortURI() {
        String longURI = "https://luarocks.org/modules/castlelecs";

        URIShortener sut = new URIShortener();

        URI result = sut.convert(longURI);

        assertTrue(result != null);
    }

    @Test
    public void convertionWithBaseWithoutLastSeparator() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String base = "example";
        URIShortener sut = new URIShortener(base);

        String result[] = sut.convert(longURI).toString().split("/");

        assertTrue(result.length == 2);
    }
}
