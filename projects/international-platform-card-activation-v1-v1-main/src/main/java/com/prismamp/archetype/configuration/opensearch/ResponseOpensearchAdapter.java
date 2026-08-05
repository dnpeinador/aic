package com.prismamp.archetype.configuration.opensearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismamp.archetype.configuration.property.OpensearchProperties;
import java.io.IOException;
import java.util.Optional;
import org.opensearch.action.bulk.BulkItemResponse;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.IndicesClient;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "opensearch", name = "enabled", havingValue = "true")
public class ResponseOpensearchAdapter implements ResponseOpensearchRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResponseOpensearchAdapter.class);
  private final RestHighLevelClient opensearchClient;
  private final ObjectMapper objectMapper;
  private final String fullIndex;

  @Override
  public <T> void bulkTransactions(T request, String id) throws IOException {
    doBulk(buildFullTransactionsBulk(request, fullIndex, id));
  }

  public ResponseOpensearchAdapter(
      RestHighLevelClient opensearchClient,
      ObjectMapper objectMapper,
      OpensearchProperties properties)
      throws IOException {
    this.opensearchClient = opensearchClient;
    this.objectMapper = objectMapper;
    this.fullIndex = properties.getIndex();
    verifyIndex(fullIndex);
  }

  private <T> BulkRequest buildFullTransactionsBulk(T request, String index, String id) {
    LOGGER.debug("Request {}, on index: {}", request, index);
    return buildTransactionsBulk(request, index, id);
  }

  private <T> BulkRequest buildTransactionsBulk(T request, String index, String id) {
    BulkRequest bulkRequest = new BulkRequest();

    Optional<String> jsonRequest = toJsonString(request);
    bulkRequest.add(new IndexRequest(index).id(id).source(jsonRequest.get(), XContentType.JSON));

    return bulkRequest;
  }

  private void doBulk(BulkRequest bulkRequest) throws IOException {
    BulkResponse bulkResponse = opensearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    checkFailuresResponse(bulkResponse);
  }

  private void checkFailuresResponse(BulkResponse bulkResponse) {
    for (BulkItemResponse bulkItemResponse : bulkResponse) {
      if (bulkItemResponse.isFailed()) {
        LOGGER.error(
            "Load error id {}, message {}",
            bulkItemResponse.getId(),
            bulkItemResponse.getFailureMessage());
      } else {
        LOGGER.info("Load success id: {}", bulkItemResponse.getId());
      }
    }
  }

  private void verifyIndex(String index) throws IOException {
    IndicesClient indicesClient = opensearchClient.indices();
    if (!indicesClient.exists(new GetIndexRequest(index), RequestOptions.DEFAULT)) {
      CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
      createIndexRequest.waitForActiveShards();
      indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
    }
  }

  private Optional<String> toJsonString(Object request) {
    try {
      return Optional.of(objectMapper.writeValueAsString(request));
    } catch (JsonProcessingException e) {
      LOGGER.error("Error on transaction mapping", e);
    }
    return Optional.empty();
  }
}
