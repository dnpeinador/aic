package com.prismamp.archetype.configuration;

import static org.zalando.logbook.core.Conditions.exclude;
import static org.zalando.logbook.core.Conditions.requestTo;
import static org.zalando.logbook.core.HeaderFilters.eachHeader;
import static org.zalando.logbook.core.QueryFilters.replaceQuery;
import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;

import com.prismamp.archetype.configuration.opensearch.ResponseOpensearchRepository;
import com.prismamp.archetype.configuration.property.ApplicationProperties;
import com.prismamp.archetype.configuration.property.LogbookProperties;
import com.prismamp.archetype.writter.OpenSearchWritter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.LogbookCreator;
import org.zalando.logbook.Sink;
import org.zalando.logbook.core.CompositeSink;
import org.zalando.logbook.core.DefaultHttpLogFormatter;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration
public class LogbookConfig {

  private static final String OBFUSCATE_PATTERN = "XXX";

  @Autowired(required = false)
  private ResponseOpensearchRepository repository;

  @Autowired private ApplicationProperties applicationProperties;
  @Autowired private LogbookProperties logbookProperties;

  @Value("${logbook.exclude}")
  private List<String> endpointsToExclude;

  @Value("${logbook.format.style}")
  private String style;

  @Value("${opensearch.enabled}")
  private boolean opensearchEnabled;

  @Bean
  public Logbook logbook() {

    List<Sink> sinks = new ArrayList<>();

    Sink defaultSink =
        new DefaultSink(
            "http".equalsIgnoreCase(style)
                ? new DefaultHttpLogFormatter()
                : new JsonHttpLogFormatter(),
            new DefaultHttpLogWriter());

    sinks.add(defaultSink);

    if (opensearchEnabled) {
      Sink openSearchSink =
          new DefaultSink(
              new JsonHttpLogFormatter(), new OpenSearchWritter(repository, applicationProperties));
      sinks.add(openSearchSink);
    }

    CompositeSink compositeSink = new CompositeSink(sinks);

    LogbookCreator.Builder logbook = Logbook.builder().sink(compositeSink);

    if (endpointsToExclude.size() > 0) {
      logbook.condition(
          exclude(endpointsToExclude.stream().map(e -> requestTo(e)).collect(Collectors.toList())));
    }

    if (logbookProperties.getJsonBodyFields().size() > 0) {
      logbookProperties
          .getJsonBodyFields()
          .forEach(
              attribute ->
                  logbook.bodyFilter(
                      jsonPath(String.format("%s%s", "$.", attribute)).replace(OBFUSCATE_PATTERN)));
    }

    if (logbookProperties.getHeaders().size() > 0) {
      logbookProperties
          .getHeaders()
          .forEach(
              header ->
                  logbook.headerFilter(
                      eachHeader((a, b) -> a.equalsIgnoreCase(header) ? OBFUSCATE_PATTERN : b)));
    }

    if (logbookProperties.getParameters().size() > 0) {
      logbookProperties
          .getParameters()
          .forEach(parameter -> logbook.queryFilter(replaceQuery(parameter, OBFUSCATE_PATTERN)));
    }
    return logbook.build();
  }
}
