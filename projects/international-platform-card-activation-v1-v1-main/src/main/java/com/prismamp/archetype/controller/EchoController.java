package com.prismamp.archetype.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${server.servlet.context-path}")
@Tag(name = "Echo", description = "Echo API")
public class EchoController {

  @GetMapping(value = {"/echo", "/echo/{message}"})
  @Operation(summary = "Get echo message")
  public ResponseEntity<String> echoMessage(@PathVariable Optional<String> message) {
    String defaultMessage = "I'm alive!";
    return new ResponseEntity<>(message.orElse(defaultMessage), HttpStatus.OK);
  }
}
