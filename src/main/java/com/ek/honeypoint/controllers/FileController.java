package com.ek.honeypoint.controllers;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class FileController {

  @Value("${file.path}")
  private String filePath;

  @GetMapping(value="/api/file/restaurant/{restaurantId}/{fileId}")
  @ResponseBody
  public ResponseEntity<Resource> getFileFromRestaurant(
    @PathVariable(value = "restaurantId") String restaurantId,
    @PathVariable(value = "fileId") String fileId
  ) {
    String path = filePath + "/restaurants/" + restaurantId + "/" + fileId;
    return this.makeFileResponse(path);    
  }

  private ResponseEntity<Resource> makeFileResponse(String filePath) {
    HttpHeaders headers = new HttpHeaders();
    try {
      System.out.println(filePath);
      final UrlResource fileResource = new UrlResource(String.format("file:%s", filePath));
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileResource.getFilename(), "UTF-8"));
      return ResponseEntity.ok()
            .headers(headers)
            .contentLength(fileResource.contentLength())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(fileResource);
    } catch (Exception e) {
      e.printStackTrace();
      // 파일 못읽었을 때 return 값에 대한 음 …
      return null;
    }
  }
  
}