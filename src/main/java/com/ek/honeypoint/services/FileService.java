package com.ek.honeypoint.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ek.honeypoint.models.Photofile;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  public ArrayList<Photofile> saveFileOnRestaurant(List<MultipartFile> multiFiles, String path, int restaurantId);

  public File saveFile(MultipartFile multiFile, String path);
}