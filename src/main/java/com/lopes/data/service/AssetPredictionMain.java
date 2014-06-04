package com.lopes.data.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by klopes on 3/24/14.
 */
@EnableAutoConfiguration
@ComponentScan
public class AssetPredictionMain {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AssetPredictionMain.class, args);

    }

}
