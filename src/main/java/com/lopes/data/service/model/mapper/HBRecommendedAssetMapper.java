package com.lopes.data.service.model.mapper;

import com.lopes.data.service.core.RowMapper;
import com.lopes.data.service.model.beans.Asset;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by klopes on 6/3/2014.
 */
public class HBRecommendedAssetMapper implements RowMapper<Asset> {
    @Override
    public Asset mapRow(Result resultSet, int i) {
        Asset asset = new Asset();
        asset.setUrl("whatever");
        return asset;
    }


}
