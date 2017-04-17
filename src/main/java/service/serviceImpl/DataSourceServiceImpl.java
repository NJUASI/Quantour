package service.serviceImpl;

import dao.DataSourceDao;
import dao.daoImpl.DataSourceDaoImpl;
import service.DataSourceService;
import utilities.DataSourceStateKeeper;
import utilities.enums.DataSourceState;
import utilities.exceptions.NotCSVException;
import vo.DataSourceInfoVO;

import java.io.IOException;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceServiceImpl implements DataSourceService {

    DataSourceDao dao;

    public DataSourceServiceImpl() {
        this.dao = new DataSourceDaoImpl();
    }

    @Override
    public boolean upload(String filePath) throws IOException, NotCSVException {
        if (!filePath.endsWith(".csv")) throw new NotCSVException();
        return dao.upload(filePath);
    }

    @Override
    public DataSourceInfoVO getMyDataSource() throws IOException {
        if (dao.getMyDataSource() != null) {
            return new DataSourceInfoVO(dao.getMyDataSource());
        }
        return null;
    }

    @Override
    public boolean setDataSourceState(DataSourceState newState) {
        DataSourceStateKeeper.getInstance().setState(newState);
        return true;
    }

    @Override
    public DataSourceState getDataSourceState() {
        return DataSourceStateKeeper.getInstance().getState();
    }
}
