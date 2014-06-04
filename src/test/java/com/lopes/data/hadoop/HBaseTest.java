package com.lopes.data.hadoop;

import com.lopes.data.service.HBAssetPredictionService;
import com.lopes.data.service.model.beans.RecommendedAsset;
import com.lopes.data.service.model.beans.UserActivity;
import com.lopes.data.service.model.mapper.HBRecommendedAssetMapper;
import com.lopes.data.service.model.mapper.HBUserActivityMapper;
import com.lopes.data.service.util.HBaseQueryHelper;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by klopes on 6/4/2014.
 */
public class HBaseTest {
    @Autowired
    HBAssetPredictionService hb_assetPredictionService;

    static HBaseQueryHelper service;

    @BeforeClass
    public static void setUp(){
        service = new HBaseQueryHelper();
    }

    @Test
    public void testGetRecordByKey() throws IOException {
        //String tableName, String columnFamily,String column, String expectedValue,  RowMapper<T> rowMapper
        Collection<RecommendedAsset> results = service.getRecordByKey("beck_predictions", "predictions", "email", ".hectorlemus24@yahoo.com", new HBRecommendedAssetMapper() );
        TestCase.assertTrue(results.size() == 2);

        Collection<UserActivity> userActivities = service.getRecordByKey("beck_useractions","useractions","email",".hectorlemus24@yahoo.com",new HBUserActivityMapper());
        TestCase.assertTrue(userActivities.size()==2);
    }

}
