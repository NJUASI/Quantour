package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.DataSourceInfoDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.DataSourceInfo;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/10.
 */
public class DataSourceInfoDataHelperImplTest {

    private DataSourceInfoDataHelper dataSourceInfoDataHelper;

    @Before
    public void setUp() throws Exception {
        dataSourceInfoDataHelper = HelperManager.dataSourceInfoDataHelper;
    }

    @Test
    @Ignore
    public void createDB(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    @Test
    public void addDataSourceInfo() throws Exception {
        dataSourceInfoDataHelper.addDataSourceInfo(new DataSourceInfo("ByronDong",String.valueOf(100), LocalDateTime.now().toString()));
        dataSourceInfoDataHelper.addDataSourceInfo(new DataSourceInfo("61990",String.valueOf(200), LocalDateTime.now().toString()));
        dataSourceInfoDataHelper.addDataSourceInfo(new DataSourceInfo("cuihua",String.valueOf(50), LocalDateTime.now().toString()));
        dataSourceInfoDataHelper.addDataSourceInfo(new DataSourceInfo("HarveyGong",String.valueOf(120), LocalDateTime.now().toString()));
        dataSourceInfoDataHelper.addDataSourceInfo(new DataSourceInfo("CharlesFeng",String.valueOf(400), LocalDateTime.now().toString()));
    }

    @Test
    public void updateDataSourceInfo() throws Exception {
        dataSourceInfoDataHelper.updateDataSourceInfo(new DataSourceInfo("HarveyGong",String.valueOf(320), LocalDateTime.now().toString()));
    }

    @Test
    @Ignore
    public void deleteDataSourceInfo() throws Exception {
        dataSourceInfoDataHelper.deleteDataSourceInfo("cuihua");
    }

    @Test
    public void getDataSourceInfo() throws Exception {
        DataSourceInfo dataSourceInfo = dataSourceInfoDataHelper.getDataSourceInfo("ByronDong");
        DataSourceInfo dataSourceInfo0 = dataSourceInfoDataHelper.getDataSourceInfo("ByronDong");
        DataSourceInfo dataSourceInfo1 = dataSourceInfoDataHelper.getDataSourceInfo("HarveyGong");
        DataSourceInfo dataSourceInfo2 = dataSourceInfoDataHelper.getDataSourceInfo("HarveyGong");
        DataSourceInfo dataSourceInfo3 = dataSourceInfoDataHelper.getDataSourceInfo("61990");
        DataSourceInfo dataSourceInfo4 = dataSourceInfoDataHelper.getDataSourceInfo("61990");
        assertEquals("100",dataSourceInfo.getFileSize());
        assertEquals("2017-05-09T21:03:23.619",dataSourceInfo.getUploadTime());
    }

}