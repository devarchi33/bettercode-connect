package com.bettercode.connect;

import com.bettercode.connect.entity.BankAccount;
import com.bettercode.connect.entity.ExcelType;
import com.srxcloud.integration.test.jpa.TransactionJpaTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;
import java.util.Objects;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest
public class ConnectWebApplicationTests {

  public static final String CREATED_BY = "li.dongxun";
  private Environment env;

  @Autowired
  public void setEnv(Environment env) {
    this.env = env;
  }

  @PersistenceUnit(unitName = "excel")
  protected EntityManagerFactory emf;


  @Before
  public void setUp() {
    if (Objects.equals(env.getProperty("spring.profiles"), "development")) {
      TransactionJpaTemplate jpaTemplate = new TransactionJpaTemplate(emf);
      jpaTemplate.execute(em -> {
        ExcelType excelType1 = new ExcelType("bettercode", "connect", "stock", CREATED_BY);
        em.merge(excelType1);

        ExcelType excelType2 = new ExcelType("bettercode", "connect", "account", CREATED_BY);
        em.merge(excelType2);

        BankAccount bettercodeAccount = new BankAccount("895437-00-000864", new BigDecimal(0), new BigDecimal(0), CREATED_BY);
        em.merge(bettercodeAccount);
        return null;
      });
    }
  }

  @Test
  public void contextLoads() {
  }

}
