package com.lopes.data.service;

import com.lopes.data.service.model.beans.BeckData;
import com.lopes.data.service.model.beans.RecommendedAsset;
import com.lopes.data.service.model.beans.UserActivity;
import com.lopes.data.service.model.mapper.HBRecommendedAssetMapper;
import com.lopes.data.service.model.mapper.HBUserActivityMapper;
import com.lopes.data.service.util.HBaseQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by klopes on 6/2/2014.
 */
@Service(value = "hb_assetPredictionService")
public class HBaseAssetPredictionServiceImpl implements HBAssetPredictionService {

    @Autowired
    @Qualifier(value = "hb_queryhelper")
    HBaseQueryHelper service;

    private final static Logger logger = LoggerFactory.getLogger(HBaseAssetPredictionServiceImpl.class);

    @Override
    public Collection<BeckData> beckPredictionsForEmail(String email) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("EMAIL", email);
        BeckData wrapperBean = new BeckData();
        wrapperBean.setEmail(email);

        try {
            List<RecommendedAsset> predictions = new ArrayList(service.getRecordByKey("beck_predictions", "predictions", "email", email, new HBRecommendedAssetMapper()));

            List<UserActivity> activities = new ArrayList(service.getRecordByKey("beck_useractions", "useractions", "email", email, new HBUserActivityMapper()));

            wrapperBean.setRecommendedAssets(predictions);

            wrapperBean.setActivities(activities);

            return Collections.singletonList(wrapperBean);
        }catch(IOException e){
            logger.error("IOException", e);
            return null;
        }

    }
}
