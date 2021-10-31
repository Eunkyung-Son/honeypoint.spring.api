package com.ek.honeypoint.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ek.honeypoint.models.Photofile;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {

  public Boolean deleteFilesOnRestaurant(List<Photofile> deleteList, String restaurantFolderPath) {
    Boolean isAllDeleted = true;
    for (Photofile deleteFile: deleteList) {
      String fileName = deleteFile.getStreFileName();
      String filePath = restaurantFolderPath + "/" + fileName;
      Boolean isDeleted = this.deleteFile(filePath);
      if (isDeleted == false) {
        isAllDeleted = false;
        break;
      }
    }
    return isAllDeleted;
  }

  public ArrayList<Photofile> saveFileOnRestaurant(List<MultipartFile> multiFiles, String path, int restaurantId) {
    ArrayList<Photofile> photofiles = new ArrayList<Photofile>();
    for (MultipartFile multiFile : multiFiles) {
      Photofile photofile = new Photofile();
      String originalFilename = multiFile.getResource().getFilename();
      File file = this.saveFile(multiFile, path);
      String uuidFileName = file.getName();
      photofile.setOriginFileName(originalFilename);
      photofile.setStreFileName(uuidFileName);
      photofile.setRNo(restaurantId);
      photofile.setImgType(1);
      photofiles.add(photofile);
    }
    return photofiles;
  }

  // FIXME: UUID로 파일명 만들어주기 (util로 빼내기)
  public File saveFile(MultipartFile multiFile, String path) {
    // ArrayList<File> files = new ArrayList<File>();
    // for (MultipartFile multiFile: multiFiles) {
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
    } catch (IOException e) {
      log.error(e.toString());
    }
    return file;
  }

  public Boolean deleteFile(String filePath) {
    File deleteFile = new File(filePath);
    Boolean isDeleted = false;
    try {
      if (deleteFile.exists()) {
        isDeleted = deleteFile.delete();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return isDeleted;
  }
}