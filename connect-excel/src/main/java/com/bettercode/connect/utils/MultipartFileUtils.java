package com.bettercode.connect.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MultipartFileUtils {
    public static File multipartToFile(MultipartFile multipart) throws IOException {
        String prefix = FilenameUtils.getBaseName(multipart.getOriginalFilename());
        File tempFile = File.createTempFile(prefix, FilenameUtils.getExtension(multipart.getOriginalFilename()));
        multipart.transferTo(tempFile);
        return tempFile;
    }

    public static File writeBytesToFile(byte[] bytes, String fileDest) throws IOException {
        FileUtils.writeByteArrayToFile(new File(fileDest), bytes);
        return new File(fileDest);
    }
}