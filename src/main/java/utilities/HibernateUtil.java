package utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Byron Dong on 2017/5/7.
 */
public class HibernateUtil {

    //用于获取session的配置对象
    private static Configuration configuration = null;

    //session工厂
    private static SessionFactory sessionFactory =null;

    //需要的session对象
    private static Session session =null;

    //优先初始化的静态代码块
    static {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build());
    }

    /**
     *
     * 获取session对象
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/7
     * @return Session session对象
     */
    public static Session getSession(){
        if(sessionFactory == null){
            sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build());
        }
        session = sessionFactory.openSession();
        return session;
    }

    /**
     *
     * 关闭session对象
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/7
     */
    public static void closeSession(){
        if(session !=null && session.isOpen()){
            session.close();
        }
    }
}
