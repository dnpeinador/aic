package com.prismamp.archetype.configuration.opensearch;

import com.prismamp.archetype.configuration.property.OpensearchProperties;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpensearchConfig {

  private final OpensearchProperties properties;

  public OpensearchConfig(OpensearchProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnProperty(prefix = "opensearch", name = "enabled", havingValue = "true")
  public RestHighLevelClient opensearchClient()
      throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

    final SSLContext sslcontext =
        SSLContextBuilder.create().loadTrustMaterial(null, (chains, authType) -> true).build();

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
        AuthScope.ANY,
        new UsernamePasswordCredentials(properties.getUser(), properties.getPassword()));
    HttpHost httpHost =
        new HttpHost(properties.getHost(), properties.getPort(), properties.getSchema());
    RestClientBuilder restClientBuilder =
        RestClient.builder(httpHost)
            .setCompressionEnabled(true)
            .setHttpClientConfigCallback(
                httpClientBuilder ->
                    httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)
                        .setSSLContext(sslcontext)
                        .setDefaultRequestConfig(
                            RequestConfig.custom()
                                .setConnectTimeout(properties.getConnectTimeout())
                                .setSocketTimeout(properties.getSocketTimeout())
                                .build()));
    return new RestHighLevelClient(restClientBuilder);
  }
}
