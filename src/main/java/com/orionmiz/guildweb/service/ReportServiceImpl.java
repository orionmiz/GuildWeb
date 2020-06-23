package com.orionmiz.guildweb.service;

import com.orionmiz.guildweb.domain.Report;
import com.orionmiz.guildweb.domain.ReportEvidence;
import com.orionmiz.guildweb.persistence.ReportRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository repo;

    @Override
    public void saveReport(Report report, List<MultipartFile> files) {

        List<ReportEvidence> evidences = new ArrayList<>();

        files.forEach(file -> {
            log.info("오리지널 : " + file.getOriginalFilename());
            String fileName = UUID.randomUUID() + "." + file.getOriginalFilename().split("\\.")[1];
            File saveFile = new File("C:\\report_evidence\\" + fileName);
            try {
                file.transferTo(saveFile);
                ReportEvidence evidence = new ReportEvidence();
                evidence.setFileLocation("/reportEvidence/" + fileName);
                evidences.add(evidence);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        report.setEvidences(evidences);

        repo.save(report);

    }
}
