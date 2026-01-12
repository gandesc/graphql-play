package com.gandesc.graphql_play.lec15.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
  private final ErrorType errorType;
  private final String message;
  private final Map<String, Object> extensions;
}
