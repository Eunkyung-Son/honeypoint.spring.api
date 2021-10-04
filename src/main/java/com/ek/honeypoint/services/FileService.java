package com.ek.honeypoint.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  public File saveFile(MultipartFile multiFile, String path);
}
