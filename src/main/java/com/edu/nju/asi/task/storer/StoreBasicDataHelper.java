package com.edu.nju.asi.task.storer;

import com.edu.nju.asi.task.storer.financialStatement.*;

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
