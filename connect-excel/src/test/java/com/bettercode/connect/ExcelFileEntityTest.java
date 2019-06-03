package com.bettercode.connect;

import com.bettercode.connect.entity.ExcelFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
public class ExcelFileEntityTest {

  @Test
  public void createRowMapperNameTest() throws IOException {
    // given
    ExcelFile excelFile = new ExcelFile("bettercode", "connect", "account", new MultipartFile() {
      @Override
      public String getName() {
        return null;
      }

      @Override
      public String getOriginalFilename() {
        return null;
      }

      @Override
      public String getContentType() {
        return null;
      }

      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public long getSize() {
        return 0;
      }

      @Override
      public byte[] getBytes() throws IOException {
        return new byte[0];
      }

      @Override
      public InputStream getInputStream() throws IOException {
        return null;
      }

      @Override
      public void transferTo(File file) throws IOException, IllegalStateException {

      }
    }, "li.dongxun");

    //when
    String rowMapperName = excelFile.getRowMapperName();

    //then
    assertThat(rowMapperName).isEqualTo("accountExcelItemProcessor");

  }
}
