package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.infoCarrier.MailInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.MailService;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.utilities.enums.MailNotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/6/2.
 */
@Service("StrategyService")
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    StrategyDao strategyDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MailService mailService;


    @Override
    public List<Strategy> getAllStrategies() {
        return strategyDao.getAllStrategies();
    }

    @Override
    public List<Strategy> getMyStrategies(User curUser) {
        return strategyDao.getAllStrategies(curUser.getUserName());
    }

    @Override
    public boolean saveStrategy(Strategy newStrategy) {
        boolean fakeSave = strategyDao.saveStrategy(newStrategy.getContent(), newStrategy);


        // TODO 另开一个线程跑回测，跑完了将回测的数据存入数据库


        return fakeSave;
    }

    @Override
    public Strategy getOneStrategy(String strategyID) {
        return strategyDao.getStrategy(strategyID);
    }

    // 只有创建者能够操作（修改／删除）策略
    @Override
    public boolean canUpdate(Strategy strategy, User curUser) {
        return isCreator(strategy, curUser);
    }

    @Override
    public boolean modify(Strategy modified) {
        boolean modifyResult = strategyDao.updateStrategy(modified.getCreater(), modified);
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
        if (strategy.getCreater().equals(user.getUserName())) return true;
        else return false;
    }

    private boolean notify(Strategy strategy, MailNotificationType type) {
        String creatorAddress = userDao.get(strategy.getCreater()).getEmail();

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
