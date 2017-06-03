package com.xsalefter.elasticsdata.web.api.util;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class WebAPIUtil {

    public static HttpHeaders generateSearchPaginationHttpHeaders(String query, Page<?> page, String baseUrl) 
    throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + "&query=" + query  + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + "&query=" + query + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + "&query=" + query + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + "&query=" + query + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String generateUri(String baseUrl, int page, int size) throws URISyntaxException {
        return UriComponentsBuilder
            .fromUriString(baseUrl)
            .queryParam("page", page)
            .queryParam("size", size)
        .toUriString();
    }
}