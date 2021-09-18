package com.ek.honeypoint.controllers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    DataSource dataSource;

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        try {
            // Connection con = dataSource.getConnection();
            System.out.println("home 요청");
            // DatabaseMetaData metaData = con.getMetaData();
            // ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
            // while (tables.next()) {
            //     String tableName=tables.getString("TABLE_NAME");
            //     System.out.println(tableName);
                // ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
                // while (columns.next()) {
                //     String columnName=columns.getString("COLUMN_NAME");
                //     System.out.println("\t" + columnName);
                // }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "home!";
    }
}
