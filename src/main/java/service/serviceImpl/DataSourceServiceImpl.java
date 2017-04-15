package service.serviceImpl;

import dao.DataSourceDao;
import dao.daoImpl.DataSourceDaoImpl;
import service.DataSourceService;
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
    public boolean upload(String filePath) throws IOException {
        return dao.upload(filePath);
    }

    @Override
    public DataSourceInfoVO getMyDataSource() throws IOException {
        return new DataSourceInfoVO(dao.getMyDataSource());
    }
}
