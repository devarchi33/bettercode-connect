package com.bettercode.connect.Util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
  public static boolean isExcelFile(MultipartFile file) {
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    return (extension.equalsIgnoreCase("xls") ||
        extension.equalsIgnoreCase("xlsx"));
  }

  public static File multipartFileToFileConverter(MultipartFile multipartFile) throws IOException {
    File convertFile = new File(multipartFile.getOriginalFilename());
    multipartFile.transferTo(convertFile);
    return convertFile;
  }
}
