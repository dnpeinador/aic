package com.prismamp.archetype.configuration.opensearch;

import java.io.IOException;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseOpensearchRepository {
  <T> void bulkTransactions(T request, String id) throws IOException;
}
