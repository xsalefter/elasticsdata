package com.xsalefter.elasticsdata.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.xsalefter.elasticsdata.model.Restaurant;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
}
