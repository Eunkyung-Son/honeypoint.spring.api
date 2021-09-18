package com.ek.honeypoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
public class FileController {
  
  // @Autowired
  // private FileService fileService;
  
  @PostMapping(value="/api/file/restaurant/{restaurantId}")
  public void addFileOnRestaurant(
    @RequestPart("file") FilePart filePart
  ) {
      //TODO: process POST request
      
  }
  
}
