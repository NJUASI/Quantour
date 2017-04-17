package presentation.controller;

import presentation.view.panel.user.UserPanel;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.ProgressBar;
import service.DataSourceService;
import service.StockService;
import service.StockSituationService;
import service.UserService;
import service.serviceImpl.DataSourceServiceImpl;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.UserServiceImpl;
import utilities.IDReserve;
import utilities.enums.DataSourceState;
import utilities.exceptions.NotCSVException;
import utilities.exceptions.PasswordInputException;
import utilities.exceptions.PrivateStockNotExistException;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.DataSourceInfoVO;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/13.
 */
public class UserController {
    /**
     * The constant User controller.
     */
    private static UserController userController = new UserController();

    /**
     * The user panel.
     */
    private UserPanel userPanel;



    JProgressBar progressBar;
    StockService stockService;
    UserService userService;
    DataSourceService dataSourceService;
    /**
     * Instantiates a new User controller.
     */
    private UserController(){
        userPanel = UserPanel.getInstance();
    }

    /**
     * Get instance  User controller.
     *
     * @return the  User controller.
     */
    public static UserController getInstance(){
        return userController;
    }
    /**
     * TODO 导入数据
     * filePath 路径
     */

    public void importDate(String filePath) {
        try {
            dataSourceService = new DataSourceServiceImpl();
            dataSourceService.upload(filePath);

            if (progressBar != null) {
                userPanel.fileImportPanel.remove(progressBar);
            }
            progressBar = new JProgressBar();
            progressBar.setStringPainted(true);  //显示提示信息
            progressBar.setIndeterminate(false);
            progressBar.setBounds(adaptScreen(60, 340, 200, 35));
            userPanel.fileImportPanel.add(progressBar);
            new ProgressBar(progressBar).start();

            setUpdateMessage();

        } catch (IOException e) {
            e.printStackTrace();
            new PopUpFrame(e.getMessage());
            setUpdateMessage();
        } catch (NotCSVException e) {
            e.printStackTrace();
            new PopUpFrame(e.getMessage());
            setUpdateMessage();
        }
    }

    public void setUpdateMessage(){
        System.out.println("44444");
        try {
            dataSourceService = new DataSourceServiceImpl();
            DataSourceInfoVO vo = dataSourceService.getMyDataSource();
            System.out.println("2222222");
            if(vo!=null) {
                userPanel.fileImportPanel.setUploadInfo("由" + vo.userName + "上传于" + vo.uploadTime);
            }else{
                userPanel.fileImportPanel.setUploadInfo("未上传本地数据");
                userPanel.fileImportPanel.notFoundDate();
                System.out.println("11");
                dataSourceService.changeDataSourceState(DataSourceState.ORIGINAL);
            }
            System.out.println("111122");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void modifyPassword() throws PasswordInputException {
        userService = new UserServiceImpl();
        String newPassword = userPanel.messagePanel.getPassword();
        if (newPassword != null) {
            userService.modifyUser(new UserVO(IDReserve.getInstance().getUserID(), newPassword));
            new PopUpFrame("修改成功！");
            userPanel.messagePanel.cancelModify();
        }

    }
    public void deleteFavorite() throws PrivateStockNotExistException, PrivateStockNotFoundException {

        stockService = new StockServiceImpl();

        stockService.deletePrivateStock(IDReserve.getInstance().getUserID(), userPanel.favoritePanel.getCode());
        userPanel.refreshFavorite();
    }

    /**
     * 适应不同大小的屏幕
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public Rectangle adaptScreen(int x, int y, int width, int height){
        int w=WindowData.getInstance().getWidth();
        int h=WindowData.getInstance().getHeight();
        return new Rectangle(w*x/1920,h*y/1030,w*width/1920,h*height/1030);
    }

}
