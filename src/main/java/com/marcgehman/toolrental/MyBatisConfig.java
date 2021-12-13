package com.marcgehman.toolrental;

import javax.sql.DataSource;

import com.marcgehman.toolrental.tool.ToolMapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.marcgehman.toolrental.tool")
@MapperScan("com.marcgehman.toolrental.rental")
public class MyBatisConfig {
    
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver").username("root").password("root").url("jdbc:mysql://localhost:3306/toolrental?useSSL=false").build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }
}
