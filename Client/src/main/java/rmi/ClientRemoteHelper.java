package rmi;

import service.ChartService;
import service.StockService;
import service.StockSituationService;
import service.UserService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Harvey on 2017/3/6.
 */
public class ClientRemoteHelper {

    ChartService chartService;
    UserService userService;
    StockService stockService;
    StockSituationService stockSituationService;


    private static ClientRemoteHelper remoteHelper = new ClientRemoteHelper();
    private final String url = "rmi://localhost:8888/";

    private ClientRemoteHelper() {
        init();
    }

    private void init() {
        try {
            chartService = (ChartService) Naming.lookup(url+"ChartService");
            userService = (UserService)Naming.lookup(url+"UserService");
            stockService = (StockService)Naming.lookup(url+"StockService");
            stockSituationService = (StockSituationService)Naming.lookup(url+"StockSituationService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static ClientRemoteHelper getInstance(){
        return remoteHelper;
    }

    public ChartService getChartService() {
        return chartService;
    }

    public UserService getUserService() {
        return userService;
    }

    public StockService getStockService() {
        return stockService;
    }

    public StockSituationService getStockSituationService() {
        return stockSituationService;
    }
}
