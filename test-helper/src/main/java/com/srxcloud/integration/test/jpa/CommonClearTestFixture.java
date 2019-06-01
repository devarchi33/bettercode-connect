package com.srxcloud.integration.test.jpa;

import javax.persistence.EntityManagerFactory;

public class CommonClearTestFixture {
  EntityManagerFactory emf;

  public CommonClearTestFixture(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void clearTestFixture() {
    TransactionJpaTemplate jpaTemplate = new TransactionJpaTemplate(emf);
    jpaTemplate.execute(em -> {
//      em.createNativeQuery("DELETE FROM srx_brand_tmall_order_record.tmall_orders").executeUpdate();
//      em.createNativeQuery("DELETE FROM srx_brand_tmall_order_record.tmall_trade_histories").executeUpdate();
//      em.createNativeQuery("DELETE FROM srx_brand_tmall_order_record.tmall_trades").executeUpdate();
      em.createNativeQuery("DELETE FROM srx_brand_nhub.mng_props").executeUpdate();
      em.createNativeQuery("INSERT INTO srx_brand_nhub.mng_props (`key`, store_code, tenant_code, value, created, created_by, modified, modified_by) VALUES ('srx.colleague.token', '0', 'demo', 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJjb2xsZWFndWUiLCJjaGFubmVsQ29kZSI6IkRlZmF1bHRDb2RlIiwiY2hhbm5lbElkIjoxLCJjb2xsZWFndWVOYW1lIjoi5bCP5piOIiwiY29sbGVhZ3VlTm8iOiJzdGFnaW5nLmFkbWluIiwiY29sbGVhZ3VlUm9sZSI6IkNvbGxlYWd1ZVJvbGUiLCJleHAiOjE1NDgyNTk4ODYsImlkIjoxLCJpc3MiOiJjb2xsZWFndWUiLCJuYmYiOjE1NDgyMDU1ODYsInRlbmFudENvZGUiOiJodWFtYW8iLCJ0ZW5hbnRJZCI6MTIsInVzZXJOYW1lIjoicWEuYWRtaW4ifQ.zSgdRse94w48e_Goy__iFps9pdY5evT9TkY2SRiM3PA', current_date, 'li.dongxun', current_date, 'li.dongxun')").executeUpdate();
      return null;
    });
  }
}
