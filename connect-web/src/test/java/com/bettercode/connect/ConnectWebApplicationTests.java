package com.bettercode.connect;

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
import java.util.Objects;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest
public class ConnectWebApplicationTests {

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
        ExcelType excelType = new ExcelType("bettercode", "connect", "stock", "li.dongxun");
        em.merge(excelType);
        return null;
      });
    }
  }

  @Test
  public void contextLoads() {
  }

}
