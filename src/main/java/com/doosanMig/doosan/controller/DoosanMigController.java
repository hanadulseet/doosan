package com.doosanMig.doosan.controller;


import com.doosanMig.doosan.model.Migration.MigrationType;
import com.doosanMig.doosan.service.MigService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/helper")
public class DoosanMigController {

    private MigService migService;

    @Autowired
    public void setMigService(MigService migService) { this.migService = migService; }

    @GetMapping(value = {"/Migration/{MigrationType}"})
    public ResponseEntity<HttpStatus> setMigration(@PathVariable("MigrationType") MigrationType migrationType) {
        migService.migrationStart(migrationType);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    /*
    @GetMapping(value = {"/TestServiceNowAPI"})
    public ResponseEntity<HttpStatus> TestServiceNowAPI() {
        migService.testServiceNowAPI();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = {"/ServiceNowAPIupdateFile"})
    public ResponseEntity<HttpStatus> ServiceNowAPIupdateFile() {
        try {
            migService.serviceNowAPIupdateFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


}
