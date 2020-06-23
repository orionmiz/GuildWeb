package com.orionmiz.guildweb.persistence;

import com.orionmiz.guildweb.domain.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {
}
