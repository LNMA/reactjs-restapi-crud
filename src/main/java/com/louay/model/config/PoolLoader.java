package com.louay.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PoolLoader implements ApplicationRunner {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(@Qualifier("pool") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.dataSource.getConnection();
    }
}
