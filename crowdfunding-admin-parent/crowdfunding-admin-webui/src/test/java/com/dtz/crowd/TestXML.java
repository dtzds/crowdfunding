package com.dtz.crowd;

import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestXML {
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(TestXML.class);

    /*
    * 测试数据源配置
    */
    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /*
    * 测试spring-mybatis整合配置
    */
    @Test
    public void testMybatis() {
        Admin admin = new Admin(null, "tom", "123456", "汤姆", "tom@gmail.com", null);
        int count = adminMapper.insert(admin);

        //如果在实际的开发中，所有想查看数值的地方都使用sout，会给项目上线带来问题
        //sout本质上是IO操作，IO操作会消耗性能，如果大量使用sout则会对性能的影响比较大
        //即使上线前花费时间将sout代码删除，也有可能会有遗漏，且非常麻烦
        //System.out.println("受影响的行数" + count);
        logger.debug("受影响的行数" + count);
    }

    @Test
    public void testLogger() {

        logger.debug("debug level");
        logger.debug("debug level");
        logger.debug("debug level");

        logger.info("info level");
        logger.info("info level");
        logger.info("info level");

        logger.warn("warn level");
        logger.warn("warn level");
        logger.warn("warn level");

        logger.error("error level");
        logger.error("error level");
        logger.error("error level");

    }

}
