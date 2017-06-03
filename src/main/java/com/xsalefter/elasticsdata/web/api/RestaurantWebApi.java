package com.xsalefter.elasticsdata.web.api;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xsalefter.elasticsdata.model.Restaurant;
import com.xsalefter.elasticsdata.repository.RestaurantRepository;
import com.xsalefter.elasticsdata.web.api.util.WebAPIUtil;


@RestController
@RequestMapping("/api")
public class RestaurantWebApi {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantWebApi.class);
    private static final String JSON_MSG = "{\"message\":\"Hello World!\"}";

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantWebApi(RestaurantRepository repository) {
        this.restaurantRepository = repository;
    }

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getMessages() {
        LOG.info("-> Requesting helloworld resource.");
        return new ResponseEntity<>(JSON_MSG, HttpStatus.OK);
    } 

    @RequestMapping(value = "/restaurants")
    public ResponseEntity<?> findByRestaurantId(
            @RequestParam(name = "restaurantId") String restaurantId,
            Pageable pageable) {
        if (null == restaurantId || restaurantId.isEmpty()) {
            LOG.error("-> restaurantId is null or empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOG.info("--> finding restaurants with id: {}", restaurantId);
        final Iterable<Restaurant> result = this.restaurantRepository.search(queryStringQuery(restaurantId));

        return result.iterator().hasNext() ? 
            new ResponseEntity<>(result, HttpStatus.OK) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/_search/restaurants")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String query, Pageable pageable) 
    throws URISyntaxException {
        LOG.info("-> REST request to search for a page of restaurants for query {}", query);
        Page<Restaurant> page = restaurantRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = WebAPIUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/restaurants");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
