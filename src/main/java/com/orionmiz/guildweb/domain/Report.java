package com.orionmiz.guildweb.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_reports")
@ToString
@EqualsAndHashCode(of="id")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String reporter;
    private String reported;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "report")
    private List<ReportEvidence> evidences;
}
