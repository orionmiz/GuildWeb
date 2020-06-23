package com.orionmiz.guildweb.controller;

import com.orionmiz.guildweb.domain.Report;
import com.orionmiz.guildweb.domain.ReportEvidence;
import com.orionmiz.guildweb.persistence.ReportRepository;
import com.orionmiz.guildweb.service.ReportService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Log
@Controller
public class ReportController {

    @Autowired
    ReportService service;

    @Autowired
    ReportRepository repo;

    @GetMapping("/report")
    public void report() {

    }

    @PostMapping("/report")
    public void recvReport(Report report, @RequestParam("files") List<MultipartFile> files) {

        service.saveReport(report, files);

    }

}
