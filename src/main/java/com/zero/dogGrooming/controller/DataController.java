package com.zero.dogGrooming.controller;

import com.zero.dogGrooming.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private IDataService servData;

    @PostMapping("/reset")
    public String resetData() {
        return servData.resetDatabase();
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        return servData.deleteAllData();
    }
}
