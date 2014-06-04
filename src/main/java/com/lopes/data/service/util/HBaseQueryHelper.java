package com.lopes.data.service.util;

import com.lopes.data.service.core.RowMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.hadoop.hbase.client.HBaseAdmin.checkHBaseAvailable;

/**
 * Created by klopes on 6/3/2014.
 */
@Component(value = "hb_queryhelper")
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



    public void createTable(String tableName, String... columnFamilies) throws IOException {
        if (StringUtils.isEmpty(tableName)) {
            throw new IOException("A table name needs to be specified ");
        }
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            logger.error("table: " + tableName + " already exists!");
        } else {
            if (columnFamilies == null || columnFamilies.length == 0) {
                throw new IOException("At least one column family needs to be specified for " + tableName);
            }
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for (int i = 0; i < columnFamilies.length; i++) {
                tableDesc.addFamily(new HColumnDescriptor(columnFamilies[i]));
            }
            admin.createTable(tableDesc);

        }
    }

    public void deleteTable(String tableName) throws Exception {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(tableName)) {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
                logger.debug("delete table " + tableName + " ok.");
            } else {
                logger.error("table " + tableName + " does not exist.");
            }


        } catch (MasterNotRunningException e) {
            logger.error("", e);
        } catch (ZooKeeperConnectionException e) {
            logger.error("", e);
        }
    }

    public void addRecord(String tableName, String rowKey,
                          String family, String qualifier, String value) throws Exception {
        if (StringUtils.isEmpty(tableName)) {
            throw new IOException("A table name needs to be specified ");
        }

        try {
            HTable table = new HTable(conf, tableName);
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(tableName)) {
                Put put = new Put(Bytes.toBytes(rowKey));
                put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                        Bytes.toBytes(value));
                table.put(put);
                logger.debug("insert recored " + rowKey + " to table "
                        + tableName + " ok.");
            } else {
                logger.error("table " + tableName + " does not exist.");
            }

        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public void deleteRecord(String tableName, String rowKey)
            throws IOException {
        HTable table = new HTable(conf, tableName);
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            List<Delete> list = new ArrayList<Delete>();
            Delete del = new Delete(rowKey.getBytes());
            list.add(del);
            table.delete(list);
            logger.debug("del recored " + rowKey + " ok.");
        }

    }

    public <T> Collection<T> getRecordByKey(String tableName, String columnFamily, String column, String expectedValue, RowMapper<T> rowMapper)
            throws IOException {
        HTable table = new HTable(conf, tableName);
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            if (StringUtils.isEmpty(expectedValue)) throw new RuntimeException("The expected value cannot be null");

            Collection<T> result = new ArrayList<T>();
            SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(column), CompareOp.EQUAL, Bytes.toBytes(expectedValue));
            Scan s = new Scan();
            s.setFilter(filter);
            ResultScanner ss = table.getScanner(s);
            int count = 0;
            for (Result r : ss) {
                logger.debug(r.toString());
                result.add(rowMapper.mapRow(r, count++));
            }
            return result;

        } else {
            logger.error("table " + tableName + " does not exist.");
            return null;
        }


    }

    public <T> Collection<T> getAllRecord(String tableName, RowMapper<T> rowMapper) {

        try {
            HTable table = new HTable(conf, tableName);
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(tableName)) {
                Collection<T> result = new ArrayList<T>();
                Scan s = new Scan();
                ResultScanner ss = table.getScanner(s);
                int count = 0;
                for (Result r : ss) {
                    result.add(rowMapper.mapRow(r, count++));
                }
                return result;
            } else {
                logger.error("table " + tableName + " does not exist.");
                return null;
            }

        } catch (IOException e) {
            logger.error("", e);
            return null;
        }

    }
}
