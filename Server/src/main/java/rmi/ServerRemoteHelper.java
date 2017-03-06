package rmi;

import serviceImpl.ChartServiceImpl;
import serviceImpl.StockServiceImpl;
import serviceImpl.StockSituationServiceImpl;
import serviceImpl.UserServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class ServerRemoteHelper {


    private final static String url = "rmi://localhost:8889/";

    public ServerRemoteHelper() {
        initServer();
    }

    private void initServer(){
        try {
            LocateRegistry.createRegistry(8888);
            Naming.bind(url+"ChartService", new ChartServiceImpl());
            Naming.bind(url+"UserService", new UserServiceImpl());
            Naming.bind(url+"StockService", new StockServiceImpl());
            Naming.bind(url+"StockSituationService", new StockSituationServiceImpl());
            System.out.println("link");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
