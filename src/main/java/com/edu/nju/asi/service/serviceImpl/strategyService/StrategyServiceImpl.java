package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.infoCarrier.MailInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.MailService;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.utilities.enums.MailNotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by cuihua on 2017/6/2.
 */
@Service("StrategyService")
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    TraceBackService traceBackService;

    @Autowired
    MailService mailService;

    @Autowired
    StrategyDao strategyDao;

    @Autowired
    UserDao userDao;


    @Override
    public List<Strategy> getAllStrategies() {
        List<Strategy> wanted = strategyDao.getAllStrategies();
        wanted.sort(new StrategyUsersDescComparator());
        return wanted;
    }

    @Override
    public List<Strategy> getMyStrategies(User curUser) {
        List<Strategy> wanted = strategyDao.getAllStrategies(curUser.getUserName());
        wanted.sort(new StrategyUsersDescComparator());
        return wanted;

    }

    @Override
    public boolean saveStrategy(Strategy newStrategy) {
        boolean fakeSave = strategyDao.saveStrategy(newStrategy);

        // 另开一个线程跑回测，跑完了将回测的数据存入数据库
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Boolean> ft = new FutureTask<>(new TraceBackSave(traceBackService, strategyDao, newStrategy));
        executor.submit(ft);
        executor.shutdown();

        return fakeSave;
    }

    @Override
    public boolean isExist(String strategyID) {
        return strategyDao.isExist(strategyID);
    }

    @Override
    public Strategy getOneStrategy(String strategyID) {
        return strategyDao.getStrategy(strategyID);
    }

    @Override
    public boolean modify(Strategy modified) {
        boolean modifyResult = strategyDao.updateStrategy(modified);
        boolean notifyResult = notify(modified, MailNotificationType.MODIFY);
        return modifyResult && notifyResult;

    }

    @Override
    public boolean delete(User curUser, String strategyID) {
        Strategy deleteStrategy = strategyDao.getStrategy(strategyID);
        boolean notifyResult = notify(deleteStrategy, MailNotificationType.DELETE);
        boolean deleteResult = strategyDao.deleteStrategy(curUser.getUserName(), strategyID);
        return notifyResult && deleteResult;
    }

    @Override
    public boolean subscribe(String strategyID, User curUser) {
        return strategyDao.addStrategyLink(curUser.getUserName(), strategyID);
    }

    @Override
    public boolean revokeSubscribe(String strategyID, User curUser) {
        return strategyDao.deleteStrategy(curUser.getUserName(), strategyID);
    }


    private boolean isCreator(Strategy strategy, User user) {
        if (strategy.getCreator().equals(user.getUserName())) return true;
        else return false;
    }

    private boolean notify(Strategy strategy, MailNotificationType type) {
        String creatorAddress = userDao.get(strategy.getCreator()).getEmail();

        List<String> subscribersAddress = new LinkedList<>();
        for (User user : strategy.getUsers()) {
            subscribersAddress.add(user.getEmail());
        }

        MailInfo mailInfo = new MailInfo(creatorAddress, subscribersAddress, type, strategy.getStrategyID());
        try {
            mailService.notify(mailInfo);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

}
