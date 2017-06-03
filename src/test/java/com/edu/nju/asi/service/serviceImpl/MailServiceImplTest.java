package test.com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.infoCarrier.MailInfo;
import com.edu.nju.asi.service.MailService;
import com.edu.nju.asi.service.serviceImpl.MailServiceImpl;
import com.edu.nju.asi.utilities.enums.MailNotificationType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * MailServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 3, 2017</pre>
 */
public class MailServiceImplTest {

    MailService service;

    @Before
    public void before() throws Exception {
        service = new MailServiceImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: notify(MailInfo mailInfo)
     */
    @Test
    public void testNotify() throws Exception {
        List<String> subscribers = new LinkedList<>();
        subscribers.add("fdfengjunjie@126.com");
        subscribers.add("fdfengjunjie970407@gmail.com");
        MailInfo nowMailInfo = new MailInfo("a52212498@163.com", subscribers, MailNotificationType.UPDATE,
                "赚不到钱的策略哈哈哈");
        boolean result = service.notify(nowMailInfo);
        assertEquals(true, result);

    }


} 
