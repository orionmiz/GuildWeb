package com.orionmiz.guildweb.service;

import com.orionmiz.guildweb.domain.Report;
import com.orionmiz.guildweb.domain.ReportEvidence;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportService {
    void saveReport(Report report, List<MultipartFile> files);
}
