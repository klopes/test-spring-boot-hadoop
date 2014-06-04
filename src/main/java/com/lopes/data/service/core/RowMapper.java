package com.lopes.data.service.core;

import org.apache.hadoop.hbase.client.Result;

/**
 * Created by klopes on 6/3/2014.
 */
public interface RowMapper<T> {
    T mapRow(Result resultSet, int i);
}
