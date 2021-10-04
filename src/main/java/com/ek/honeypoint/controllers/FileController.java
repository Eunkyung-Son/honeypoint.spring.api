package com.ek.honeypoint.controllers;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.print.attribute.standard.Media;

import com.ek.honeypoint.services.FileService;

import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class FileController {
  
  @Autowired
  private FileService fileService;

  @Value("${file.path}")
  private String filePath;
  
  @PostMapping(value="/api/file/restaurant/{restaurantId}")
  public void addFileOnRestaurant(
    @RequestPart("file") MultipartFile filePart
  ) {
    System.out.println(filePart.toString());
    System.out.println(filePart.getName());
    System.out.println(filePart.getSize());
    System.out.println(filePart.getResource().getFilename());
    System.out.println(filePart.getContentType());
    System.out.println(filePath);
    File returnFile = fileService.saveFile(filePart, "/honeypoint");
    System.out.println(returnFile.getName());
  }

  @GetMapping(value="/api/file/restaurant/{restaurantId}/{fileId}")
  @ResponseBody
  public ResponseEntity<Resource> getFileFromRestaurant(
    @PathVariable(value = "restaurantId") String restaurantId,
    @PathVariable(value = "fileId") String fileId
  ) {
    String path = filePath + "/" + fileId;
    return this.makeFileResponse(path);    
  }

  private ResponseEntity<Resource> makeFileResponse(String filePath) {
    HttpHeaders headers = new HttpHeaders();
    try {
      final UrlResource fileResource = new UrlResource(String.format("filex:%s", filePath));
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileResource.getFilename(), "UTF-8"));
      return ResponseEntity.ok()
            .headers(headers)
            .contentLength(fileResource.contentLength())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(fileResource);
    } catch (Exception e) {
      e.printStackTrace();
      // 파일 못읽었을 때 return 값에 대한 음 ...
      return null;
    }
  }
  
}

