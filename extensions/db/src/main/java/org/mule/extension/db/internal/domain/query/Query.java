/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.extension.db.internal.domain.query;

import static java.util.stream.Collectors.toList;

import java.util.List;

/**
 * Represents an instantiation of a {@link QueryTemplate} with parameter values
 */
public class Query {

  private final QueryTemplate queryTemplate;
  private final List<QueryParamValue> paramValues;

  /**
   * Creates a query from a template and a set of parameter values
   *
   * @param queryTemplate template describing the query
   * @param paramValues   parameter values for the query
   */
  public Query(QueryTemplate queryTemplate, List<QueryParamValue> paramValues) {
    this.paramValues = paramValues;
    this.queryTemplate = queryTemplate;
  }

  /**
   * Creates a query from a template
   *
   * @param queryTemplate template describing the query and parameter values
   */
  public Query(QueryTemplate queryTemplate) {
    this.queryTemplate = queryTemplate;
    paramValues = queryTemplate.getInputParams().stream()
        .map(p -> new QueryParamValue(p.getName(), p.getValue()))
        .collect(toList());
  }

  public QueryTemplate getQueryTemplate() {
    return queryTemplate;
  }

  public List<QueryParamValue> getParamValues() {
    return paramValues;
  }

  public boolean isDynamic() {
    return queryTemplate.isDynamic();
  }
}
