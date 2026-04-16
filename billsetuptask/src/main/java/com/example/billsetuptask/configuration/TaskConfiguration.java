package com.example.billsetuptask.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableTask
public class TaskConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  public CommandLineRunner commandLineRunner() {
      return args -> {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS BILL_STATEMENTS ( id int, " +
                "first_name varchar(50), last_name varchar(50), minutes int, " +
                "data_usage int, bill_amount decimal(10,2))");
      };
  }
}
