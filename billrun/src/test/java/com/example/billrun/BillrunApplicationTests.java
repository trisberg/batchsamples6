package com.example.billrun;

import com.example.billrun.model.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BillrunApplicationTests {

  @Autowired
  private DataSource dataSource;

  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  public void setup() {
    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
  }

  @Test
  public void testJobResults() {
    List<Bill> billStatements = this.jdbcTemplate.query("select id, " +
            "first_name, last_name, minutes, data_usage, bill_amount " +
            "FROM bill_statements ORDER BY id",
        (rs, rowNum) -> new Bill(rs.getLong("id"),
            rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
            rs.getLong("DATA_USAGE"), rs.getLong("MINUTES"),
            rs.getDouble("bill_amount")));

    assertThat(billStatements.size()).isEqualTo(5);
    Bill billStatement = billStatements.get(0);
    assertThat(billStatement.billAmount()).isEqualTo(6.0);
    assertThat(billStatement.firstName()).isEqualTo("jane");
    assertThat(billStatement.lastName()).isEqualTo("doe");
    assertThat(billStatement.id()).isEqualTo(1);
    assertThat(billStatement.minutes()).isEqualTo(500);
    assertThat(billStatement.dataUsage()).isEqualTo(1000);

  }
}