package com.lopes.data.service.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.apache.hadoop.hbase.client.HBaseAdmin.checkHBaseAvailable;

/**
 * Created by klopes on 6/3/2014.
 */
@Component
public class HBaseQueryHelper {
    private final static Logger logger = LoggerFactory.getLogger(HBaseQueryHelper.class);
    private static Configuration conf = null;

    public HBaseQueryHelper(){
        Configuration HBASE_CONFIG = new Configuration();
        HBASE_CONFIG.set("hbase.zookeeper.quorum", "192.168.56.101"); //TODO Set the host from property file
        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
        HBASE_CONFIG.set("zookeeper.znode.parent", "/hbase-unsecure");
        try {
            checkHBaseAvailable(HBASE_CONFIG);
            conf = HBaseConfiguration.create(HBASE_CONFIG);
        } catch (MasterNotRunningException e) {
            logger.error("MasterNotRunningException",e);
        } catch (ZooKeeperConnectionException e) {
            logger.error("ZooKeeperConnectionException", e);
        }
    }





}
