package com.arimacomm.fotaServer;

import org.springframework.data.repository.CrudRepository;

import com.arimacomm.fotaServer.Product;

public interface ProductRepository  extends CrudRepository<Product, Integer> {

}
