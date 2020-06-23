package com.orionmiz.guildweb.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInfo {
    private Long bno;
    private String content;
    private int pictures;
    private int likes;
    private int comments;
}
