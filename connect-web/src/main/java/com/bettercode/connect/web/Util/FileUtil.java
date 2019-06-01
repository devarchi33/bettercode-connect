package com.bettercode.connect.web.Util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
  public static boolean isExcelFile(MultipartFile file) {
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    return (extension.equalsIgnoreCase("xls") ||
        extension.equalsIgnoreCase("xlsx"));
  }
}
