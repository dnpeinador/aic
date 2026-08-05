package com.prismamp.archetype.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorDetail", description = "An error detail representation")
public record ErrorDetail(@Schema(description = "The error detail message") String message) {}
