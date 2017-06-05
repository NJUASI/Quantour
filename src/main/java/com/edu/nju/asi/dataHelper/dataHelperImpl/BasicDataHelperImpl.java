package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.BasicDataHelper;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;
import com.edu.nju.asi.utilities.util.JDBCUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/4.
 */
@Repository
public class BasicDataHelperImpl implements BasicDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取指定code,年份和季度的基本数据（单个季度）
     *
     * @param basicDataID 基本数据的ID
     * @return 指定ID的基本数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public BasicData get(BasicDataID basicDataID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        BasicData basicData = (BasicData)session.get(BasicData.class,basicDataID);
        transaction.commit();
        session.close();
        return basicData;
    }

    /**
     * 获取指定code和年份的所有季度的基本数据（4个季度）
     *
     * @param code 股票代号
     * @param year 制定的年份
     * @return 基本数据列表
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public List<BasicData> getBasicDatas(String code, int year) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from BasicData where basicDataID.code =:code and basicDataID.year =:year";
        Query query = session.createQuery(hql);
        query.setParameter("code",code);
        query.setParameter("year",year);
        List<BasicData> basicDataList = query.list();
        transaction.commit();
        session.close();
        return basicDataList;
    }

    /**
     * 获取指定code，年份和当前季度后的N（number）个季度的基本数据（传入的当前季度也会获取,N个季度）
     *
     * @param basicDataID 基本数据的ID
     * @param number      需要获取数据的季度数
     * @return 基本数据列表
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public List<BasicData> getBasicDatas(BasicDataID basicDataID, int number) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from BasicData where basicDataID.code =:code and basicDataID.year>=:year order by basicDataID.year";
        Query query = session.createQuery(hql);
        query.setParameter("code",basicDataID.getCode());
        query.setParameter("year",basicDataID.getYear());
        List<BasicData> basicDataList = query.list();
        List<BasicData> result =  new ArrayList<>();

        //获取N个季度的基本数据
        for(int i=0;i<basicDataList.size();i++){
            if(basicDataList.get(i).getBasicDataID().equals(basicDataID)){
                for(int j = 0;j<number;j++){
                    result.add(basicDataList.get(i+j));
                }
                break;
            }
        }

        transaction.commit();
        session.close();
        return result;
    }

    /**
     * 批量进行添加基本数据
     *
     * @param basicDataList 基本数据列表
     * @return 是否成功添加
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public boolean addAll(List<BasicData> basicDataList) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO basicdata(quarter, totalAssets, totalLiabilities, " +
                "totalEquityAttributableToShareholdersOfTheParentCompany, totalOwnerEquity, totalBusinessIncome, " +
                "totalOperatingCost, operatingProfit, totalProfit, netProfit, " +
                "netProfitAttributableToTheOwnerOfTheParentCompany, basicIncomePerStock, " +
                "netCashFlowsFromOperatingActivities, assetLiabilityRatio, netDebtRatio,year, code)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList){
                preparedStatement.setInt(1,basicData.getBasicDataID().getQuarter());
                preparedStatement.setString(2,basicData.getTotalAssets());
                preparedStatement.setString(3,basicData.getTotalLiabilities());
                preparedStatement.setString(4,basicData.getTotalEquityAttributableToShareholdersOfTheParentCompany());
                preparedStatement.setString(5,basicData.getTotalOwnerEquity());
                preparedStatement.setString(6,basicData.getTotalBusinessIncome());
                preparedStatement.setString(7,basicData.getTotalOperatingCost());
                preparedStatement.setString(8,basicData.getOperatingProfit());
                preparedStatement.setString(9,basicData.getTotalProfit());
                preparedStatement.setString(10,basicData.getNetProfit());
                preparedStatement.setString(11,basicData.getNetProfitAttributableToTheOwnerOfTheParentCompany());
                preparedStatement.setDouble(12,basicData.getBasicIncomePerStock());
                preparedStatement.setString(13,basicData.getNetCashFlowsFromOperatingActivities());
                preparedStatement.setDouble(14,basicData.getAssetLiabilityRatio());
                preparedStatement.setDouble(15,basicData.getNetDebtRatio());
                preparedStatement.setInt(16,basicData.getBasicDataID().getYear());
                preparedStatement.setString(17,basicData.getBasicDataID().getCode());
                System.out.println(basicData.getBasicDataID().getCode()+" "+basicData.getBasicDataID().getYear()+" "+basicData.getBasicDataID().getQuarter());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        } finally {
            JDBCUtil.close(preparedStatement,connection);
        }
        return result;
    }

    /**
     * 批量进行更新基本数据
     *
     * @param basicDataList 基本数据列表
     * @return 是否成功添加
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public boolean updateAll(List<BasicData> basicDataList) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE basicdata SET totalAssets = ?,totalLiabilities =?,totalEquityAttributableToShareholdersOfTheParentCompany = ?," +
                "totalOwnerEquity = ?,totalBusinessIncome = ?,totalOperatingCost = ?,operatingProfit=?,totalProfit=?," +
                "netProfit=?,netProfitAttributableToTheOwnerOfTheParentCompany=?,basicIncomePerStock=?," +
                "netCashFlowsFromOperatingActivities = ?,assetLiabilityRatio=?,netDebtRatio=?" +
                "WHERE code=? AND year=? AND quarter=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList){
                preparedStatement.setString(1,basicData.getTotalAssets());
                preparedStatement.setString(2,basicData.getTotalLiabilities());
                preparedStatement.setString(3,basicData.getTotalEquityAttributableToShareholdersOfTheParentCompany());
                preparedStatement.setString(4,basicData.getTotalOwnerEquity());
                preparedStatement.setString(5,basicData.getTotalBusinessIncome());
                preparedStatement.setString(6,basicData.getTotalOperatingCost());
                preparedStatement.setString(7,basicData.getOperatingProfit());
                preparedStatement.setString(8,basicData.getTotalProfit());
                preparedStatement.setString(9,basicData.getNetProfit());
                preparedStatement.setString(10,basicData.getNetProfitAttributableToTheOwnerOfTheParentCompany());
                preparedStatement.setDouble(11,basicData.getBasicIncomePerStock());
                preparedStatement.setString(12,basicData.getNetCashFlowsFromOperatingActivities());
                preparedStatement.setDouble(13,basicData.getAssetLiabilityRatio());
                preparedStatement.setDouble(14,basicData.getNetDebtRatio());
                preparedStatement.setString(15,basicData.getBasicDataID().getCode());
                preparedStatement.setInt(16,basicData.getBasicDataID().getYear());
                preparedStatement.setInt(17,basicData.getBasicDataID().getQuarter());
                System.out.println(basicData.getBasicDataID().getCode()+" "+basicData.getBasicDataID().getYear()+" "+basicData.getBasicDataID().getQuarter());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        } finally {
            JDBCUtil.close(preparedStatement,connection);
        }
        return result;
    }
}
