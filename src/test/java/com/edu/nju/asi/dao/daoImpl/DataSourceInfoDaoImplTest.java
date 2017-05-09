package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.DAOManager;
import com.edu.nju.asi.dao.DataSourceInfoDao;
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
 * Created by Byron Dong on 2017/5/9.
 */
public class DataSourceInfoDaoImplTest {

    private DataSourceInfoDao dataSourceInfoDao;

    @Before
    public void setUp() throws Exception {
        dataSourceInfoDao = DAOManager.dataSourceInfoDao;
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
//        dataSourceInfoDao.addDataSourceInfo(new DataSourceInfo("ByronDong",String.valueOf(100), LocalDateTime.now().toString()));
//        dataSourceInfoDao.addDataSourceInfo(new DataSourceInfo("61990",String.valueOf(200), LocalDateTime.now().toString()));
        dataSourceInfoDao.addDataSourceInfo(new DataSourceInfo("cuihua",String.valueOf(50), LocalDateTime.now().toString()));
//        dataSourceInfoDao.addDataSourceInfo(new DataSourceInfo("HarveyGong",String.valueOf(120), LocalDateTime.now().toString()));
//        dataSourceInfoDao.addDataSourceInfo(new DataSourceInfo("CharlesFeng",String.valueOf(400), LocalDateTime.now().toString()));
    }

    @Test
    public void updateDataSourceInfo() throws Exception {
        dataSourceInfoDao.updateDataSourceInfo(new DataSourceInfo("HarveyGong",String.valueOf(320), LocalDateTime.now().toString()));
    }

    @Test
    public void deleteDataSourceInfo() throws Exception {
        dataSourceInfoDao.deleteDataSourceInfo("cuihua");
    }

    @Test
    public void getDataSourceInfo() throws Exception {
        DataSourceInfo dataSourceInfo = dataSourceInfoDao.getDataSourceInfo("ByronDong");
        assertEquals("100",dataSourceInfo.getFileSize());
        assertEquals("2017-05-09T21:03:23.619",dataSourceInfo.getUploadTime());
    }

}