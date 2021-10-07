package com.ek.honeypoint.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {

    //FIXME: UUID로 파일명 만들어주기 (util로 빼내기)
  public ArrayList<File> saveFile(List<MultipartFile> multiFiles, String path) {
    ArrayList<File> files = new ArrayList<File>();
    for (MultipartFile multiFile: multiFiles) {
      log.info("handling file upload {}", multiFile.getResource().getFilename());
      String uniqueFileName = UUID.randomUUID().toString();
  
      File folder = new File(path);
      if (folder.exists() == false) {
        folder.mkdirs();
      }
      
      String fileExtension = StringUtils.getFilenameExtension(multiFile.getOriginalFilename());
      log.info("type", fileExtension);
      File file = new File(path + "/" + uniqueFileName + '.' + fileExtension);
      try {
        multiFile.transferTo(file);
        files.add(file);
      } catch (IOException e) {
        log.error(e.toString());
      }
    }
    return files;
  }

  
}
