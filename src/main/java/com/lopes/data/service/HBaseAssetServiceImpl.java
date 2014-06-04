package com.lopes.data.service;

import com.lopes.data.service.model.beans.Asset;
import com.lopes.data.service.model.beans.Data;
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
@Service(value = "hb_assetService")
public class HBaseAssetServiceImpl implements HBAssetService {

    @Autowired
    @Qualifier(value = "hb_queryhelper")
    HBaseQueryHelper service;

    private final static Logger logger = LoggerFactory.getLogger(HBaseAssetServiceImpl.class);

    @Override
    public Collection<Data> testOutput(String email) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("EMAIL", email);
        Data wrapperBean = new Data();
        wrapperBean.setEmail(email);

        try {
            List<Asset> predictions = new ArrayList(service.getRecordByKey("xxx", "yyy", "email", email, new HBRecommendedAssetMapper()));

            List<UserActivity> activities = new ArrayList(service.getRecordByKey("xxx", "zzz", "email", email, new HBUserActivityMapper()));

            wrapperBean.setAssets(predictions);

            wrapperBean.setActivities(activities);

            return Collections.singletonList(wrapperBean);
        }catch(IOException e){
            logger.error("IOException", e);
            return null;
        }

    }
}
