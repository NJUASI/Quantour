package presentation.controller;

import presentation.view.panel.user.UserPanel;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.WindowData;
import service.DataSourceService;
import service.StockService;
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
    Thread thread;
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
        thread = new Thread(() -> {
            userPanel.fileImportPanel.popLabel();
            try {
                dataSourceService = new DataSourceServiceImpl();
                dataSourceService.upload(filePath);

                if (progressBar != null) {
                    userPanel.fileImportPanel.remove(progressBar);
                }

            } catch (IOException e) {
                e.printStackTrace();
                new PopUpFrame(e.getMessage());

            } catch (NotCSVException e) {
                e.printStackTrace();
                new PopUpFrame(e.getMessage());
            }
            setUpdateMessage();
            userPanel.fileImportPanel.hideLabel();
            thread.stop();
        });
        thread.start();
    }

    public void setUpdateMessage(){
        try {
            dataSourceService = new DataSourceServiceImpl();
            DataSourceInfoVO vo = dataSourceService.getMyDataSource();
            if(vo!=null) {
                userPanel.fileImportPanel.setUploadInfo("由" + vo.userName + "上传于" + vo.uploadTime);
            }else{
                userPanel.fileImportPanel.setUploadInfo("未上传本地数据");
                userPanel.fileImportPanel.notFoundDate();
                dataSourceService.setDataSourceState(DataSourceState.ORIGINAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePath(DataSourceState dataSourceState){
        dataSourceService = new DataSourceServiceImpl();
        dataSourceService.setDataSourceState(dataSourceState);
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
