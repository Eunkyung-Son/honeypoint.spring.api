package com.ek.honeypoint.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  public ArrayList<File> saveFile(List<MultipartFile> multiFile, String path);
}
