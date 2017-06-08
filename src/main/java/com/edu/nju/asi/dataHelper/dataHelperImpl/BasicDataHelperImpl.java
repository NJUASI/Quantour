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
import java.util.Map;

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
     * 根据已知的codeList获取所有基本数据
     *
     * @param codes 代码列表
     * @return 以code作为键值的Map
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public Map<String, List<BasicData>> getAllBasicData(List<String> codes) {
        return null;
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
        String sql = "INSERT INTO basicdata(code, totalAssets, totalLiabilities, " +
                "totalEquityAttributableToShareholdersOfTheParentCompany, totalOwnerEquity, " +
                "totalBusinessIncome, totalOperatingCost, operatingProfit, totalProfit, netProfit, " +
                "netProfitAttributableToTheOwnerOfTheParentCompany, basicIncomePerStock, " +
                "netCashFlowsFromOperatingActivities, assetLiabilityRatio, returnOnEquity, date)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList){
                preparedStatement.setString(1,basicData.getBasicDataID().getCode());
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
                preparedStatement.setDouble(15,basicData.getReturnOnEquity());
                preparedStatement.setObject(16,basicData.getBasicDataID().getDate());
                System.out.println(basicData.getBasicDataID().getCode()+" "+basicData.getBasicDataID().toString());
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
