package com.arimacomm.fotaServer;

import org.springframework.data.repository.CrudRepository;
import com.arimacomm.fotaServer.Version;

public interface VersionRepository extends CrudRepository<Version, Integer> {

	Iterable<Version> findByProductId(Integer productId);

}
