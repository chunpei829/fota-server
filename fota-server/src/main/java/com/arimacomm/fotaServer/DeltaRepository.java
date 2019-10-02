package com.arimacomm.fotaServer;

import org.springframework.data.repository.CrudRepository;
import com.arimacomm.fotaServer.Delta;

public interface DeltaRepository  extends CrudRepository<Delta, Integer> {

	Iterable<Delta> findByVersionId(Integer versionId);

}
