package com.edu.nju.asi.service.serviceImpl;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.dao.DataSourceDao;
import com.edu.nju.asi.dao.daoImpl.DataSourceDaoImpl;
import com.edu.nju.asi.service.DataSourceService;
import com.edu.nju.asi.utilities.DataSourceStateKeeper;
import com.edu.nju.asi.utilities.enums.DataSourceState;
import com.edu.nju.asi.utilities.exceptions.NotCSVException;
import com.edu.nju.asi.vo.DataSourceInfoVO;

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
    public boolean upload(String filePath) throws IOException, NotCSVException, PinyinException {
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
