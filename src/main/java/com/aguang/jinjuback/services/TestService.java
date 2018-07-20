package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.TestMapper;
import com.aguang.jinjuback.model.TestInfo;
import com.aguang.jinjuback.utils.SpringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class TestService {

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private TestMapper testMapper;

    public Object list() {

        return testMapper.selectAll();
    }

    public void create(TestInfo test) {
        testMapper.insert(test);
    }

    @Transactional
    public void create2() {

        TestInfo test1 = new TestInfo();
        test1.setName("haha1");

        TestInfo test2 = new TestInfo();
        test2.setName("haha2");

        testMapper.insert(test1);

        int i = 1/0;

        testMapper.insert(test2);
    }

    @Autowired
    protected PlatformTransactionManager transactionManager;

    public void create3() {

//        System.out.println(sessionFactory);
//
//        SqlSession session = sessionFactory.openSession();

//        TestInfo test1 = new TestInfo();
//        test1.setName("test1");
//        session.insert("com.aguang.jinjuback.dao.TestMapper.createTest", test1);

        //1.获取事务控制管理器

        DataSourceTransactionManager transactionManager = SpringUtils.getBean("transactionManager");

        //2.获取事务定义

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        //3.设置事务隔离级别，开启新事务

        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        //4.获得事务状态

        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            TestInfo test1 = new TestInfo();
            test1.setName("haha1");

            TestInfo test2 = new TestInfo();
            test2.setName("haha2");

            testMapper.insert(test1);

//            int i = 1/0;

            testMapper.insert(test2);

//            //5.具体的数据库操作（多个）
//
//            TestInfo test1 = new TestInfo();
//            test1.setName("haha1");

//            BOBaseJob r = new BOBaseJob();
//
//            r.setJobCode("SW001");
//
//            r.setJobName("事务001");
//
//            BOBaseJobMapper.deleteByPrimaryKey(jobCode);
//
//            BOBaseJobMapper.insert(r);
//
//            flag = true;

            transactionManager.commit(status);

        } catch (Exception e) {

            transactionManager.rollback(status);

        }

//        return flag;

//        TestInfo test1 = new TestInfo();
//        test1.setName("haha1");
//
//        TestInfo test2 = new TestInfo();
//        test2.setName("haha2");
//
//        testMapper.insert(test1);
//
//        testMapper.insert(test2);

//        session.commit();

//        session.rollback();
    }
}
