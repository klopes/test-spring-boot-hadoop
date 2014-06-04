package com.lopes.data.service.model.mapper;

import com.lopes.data.service.core.RowMapper;
import com.lopes.data.service.model.beans.RecommendedAsset;
import com.lopes.data.service.model.mapper.util.HBaseUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by klopes on 6/3/2014.
 */
public class HBRecommendedAssetMapper implements RowMapper<RecommendedAsset> {
    @Override
    public RecommendedAsset mapRow(Result resultSet, int i) {
        byte[] family = Bytes.toBytes("predictions");

        RecommendedAsset recommendedAsset = new RecommendedAsset();
        recommendedAsset.setThumbnail_url(HBaseUtils.getValuesAsString(resultSet, family, "thumbnail_url"));
        recommendedAsset.setBidEndDt(HBaseUtils.getValuesAsString(resultSet,family, "bidenddt"));
        recommendedAsset.setBidStartDt(HBaseUtils.getValuesAsString(resultSet, family, "bidstartdt"));
        recommendedAsset.setGlobalPropertyId(HBaseUtils.getValuesAsLong(resultSet, family,"globalpropertyid"));
        recommendedAsset.setHaversineDistance(HBaseUtils.getValuesAsFloat(resultSet, family, "haversine_distance"));
        recommendedAsset.setPropertyLatitude(HBaseUtils.getValuesAsFloat(resultSet,family, "property_lat"));
        recommendedAsset.setPropertyLongitude(HBaseUtils.getValuesAsFloat(resultSet,family, "property_lon"));
        recommendedAsset.setPropertyState(HBaseUtils.getValuesAsString(resultSet, family, "property_state"));
        recommendedAsset.setProductType(HBaseUtils.getValuesAsString(resultSet, family, "product_type"));
        recommendedAsset.setPdp_url(HBaseUtils.getValuesAsString(resultSet, family,"pdp_url"));
        recommendedAsset.setPropertyCity(HBaseUtils.getValuesAsString(resultSet, family,"propertycity"));
        recommendedAsset.setThumbnail_url(HBaseUtils.getValuesAsString(resultSet, family,"thumbnail_url"));
        return recommendedAsset;
    }


}
