package com.edu.nju.asi.dataHelperVersion2.dataHelperImpl;

import com.edu.nju.asi.dataHelperVersion2.DataSourceDataHelper;
import com.edu.nju.asi.model.DataSourceInfo;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/8.
 */
public class DataSourceDataHelperImplTest {

    private DataSourceDataHelper dataSourceDataHelper;

    @Before
    public void setUp() throws Exception {
        dataSourceDataHelper =  new DataSourceDataHelperImpl();
    }

    @Test
    public void createDB(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    @Test
    public void upload() throws Exception {
        dataSourceDataHelper.upload("ByronDong",String.valueOf(100), LocalDateTime.now());
        dataSourceDataHelper.upload("61990",String.valueOf(200), LocalDateTime.now());
        dataSourceDataHelper.upload("cuihua",String.valueOf(50), LocalDateTime.now());
        dataSourceDataHelper.upload("HarveyGong",String.valueOf(120), LocalDateTime.now());
        dataSourceDataHelper.upload("CharlesFeng",String.valueOf(400), LocalDateTime.now());
    }

    @Test
    public void getMyDataSource() throws Exception {
        DataSourceInfo dataSourceInfo = dataSourceDataHelper.getMyDataSource("ByronDong");
        assertEquals("100",dataSourceInfo.getFileSize());
        assertEquals("2017-05-08T21:54:34.365",dataSourceInfo.getUploadTime());
    }

}