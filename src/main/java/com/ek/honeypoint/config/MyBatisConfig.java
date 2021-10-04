package com.ek.honeypoint.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        // dataSource.setUrl("jdbc:oracle:thin:@210.113.14.162:49161:XE");
        // dataSource.setUsername("system");
        // dataSource.setPassword("oracle");
        dataSource.setUsername("honeypoint");
        dataSource.setPassword("honeypoint1");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        // Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml");
        // factoryBean.setMapperLocations(mapperLocations);
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        factoryBean.setVfs(SpringBootVFS.class);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
        
    }

    @Bean @Primary public PlatformTransactionManager transactionManager(DataSource masterDataSource) { DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(masterDataSource); transactionManager.setGlobalRollbackOnParticipationFailure(false); return transactionManager; }

}
