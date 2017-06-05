package com.edu.nju.asi.crawler.StoreDataHelper;

import com.csvreader.CsvReader;
import com.edu.nju.asi.crawler.StoreDataHelper.financialStatement.*;
import com.edu.nju.asi.dataHelper.BasicDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/4.
 */
public class StoreBasicDataHelper {

    private String path = "F:\\Quant\\cwzb\\";

    public void store() {
        Statement balanceSheet = new BalanceSheet();
        Statement profitStatement = new ProfitStatement();
        Statement statementOfCashFlow = new StatementOfCashFlow();
        Statement repaymentAbility = new RepaymentAbility();
        Statement profitability = new Profitability();

        profitability.write(profitability.get(path + "ylnl\\"));
        repaymentAbility.write(repaymentAbility.get(path+"chnl\\"));
        statementOfCashFlow.write(statementOfCashFlow.get(path + "xjllb\\"));
        profitStatement.write(profitStatement.get(path+"lrb\\"));
        balanceSheet.write(balanceSheet.get(path+"zcfzb\\"));
    }
}
