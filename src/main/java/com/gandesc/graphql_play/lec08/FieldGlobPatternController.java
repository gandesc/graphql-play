package com.gandesc.graphql_play.lec08;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class FieldGlobPatternController {

  @QueryMapping
  public Object level1(DataFetchingFieldSelectionSet selectionSet) {
    log.info("selection set contains l2 {}", selectionSet.contains("level2"));
    log.info("selection set contains l3 {}", selectionSet.contains("level2/level3"));
    log.info("selection set contains l3 {}", selectionSet.contains("**/level3"));
    log.info("selection set contains l5 {}", selectionSet.contains("level2/**/level5"));

    return null;
  }
}
