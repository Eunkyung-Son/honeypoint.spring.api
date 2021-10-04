package com.ek.honeypoint.services;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {

    //FIXME: UUID로 파일명 만들어주기 (util로 빼내기)
  public File saveFile(MultipartFile multiFile, String path) {
    log.info("handling file upload {}", multiFile.getResource().getFilename());
    String uniqueFileName = UUID.randomUUID().toString();

    File folder = new File("/Users/seungwon/Projects");
    if (folder.exists() == false) {
      folder.mkdirs();
    }
    
    String fileExtension = StringUtils.getFilenameExtension(multiFile.getOriginalFilename());
    log.info("type", fileExtension);
    File file = new File("/Users/seungwon/Projects" + "/" + uniqueFileName + '.' + fileExtension);
    try {
      multiFile.transferTo(file);
    } catch (IOException e) {
      log.error(e.toString());
    }
    return file;
  }

  
}
