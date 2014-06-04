package com.lopes.data.service.model.mapper;

import com.lopes.data.service.core.RowMapper;
import com.lopes.data.service.model.beans.UserActivity;
import com.lopes.data.service.model.mapper.util.HBaseUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by klopes on 6/3/2014.
 */
public class HBUserActivityMapper implements RowMapper<UserActivity> {
    @Override
    public UserActivity mapRow(Result resultSet, int i) {
        byte[] family = Bytes.toBytes("useractions");

        UserActivity userActivity = new UserActivity();
        float aValue = -1;
        if((aValue = HBaseUtils.getValuesAsFloat(resultSet, family, "lat")) != Float.MIN_VALUE )
            userActivity.setLatitude(aValue);
        if((aValue = HBaseUtils.getValuesAsFloat(resultSet, family, "lng")) != Float.MIN_VALUE )
            userActivity.setLongitude(aValue);
        userActivity.setSource(HBaseUtils.getValuesAsString(resultSet, family, "source"));
        userActivity.setStateCode(HBaseUtils.getValuesAsString(resultSet, family, "state"));

        return userActivity;
    }


}
