package com.lopes.data.service;


import com.lopes.data.service.model.beans.BeckData;

import java.util.Collection;

/**
 * Created by klopes on 6/3/2014.
 */
public interface HBAssetPredictionService {
    public Collection<BeckData> beckPredictionsForEmail(String email);
}
