package com.lopes.data.service.model.mapper.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by klopes on 6/3/2014.
 */
public class HBaseUtils {

    public final static  String getValuesAsString(Result resultSet, byte[] family, String columnName){
        if(resultSet.getValue(family, Bytes.toBytes(columnName)) == null) return null;
        return new String(resultSet.getValue(family, Bytes.toBytes(columnName)));
    }

    public final static Long getValuesAsLong(Result resultSet, byte[] family, String columnName){
        String s = getValuesAsString(resultSet,family, columnName);
        if(StringUtils.isEmpty(s))return Long.MIN_VALUE;
        if(!NumberUtils.isNumber(s))return  Long.MIN_VALUE;
        return Long.valueOf(s);
    }

    public final static Float getValuesAsFloat(Result resultSet, byte[] family, String columnName){
        String s = getValuesAsString(resultSet,family, columnName);
        if(StringUtils.isEmpty(s))return Float.MIN_VALUE;
        if(!NumberUtils.isNumber(s))return Float.MIN_VALUE;
        return Float.valueOf(s);
    }
}
