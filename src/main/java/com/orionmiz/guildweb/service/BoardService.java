package com.orionmiz.guildweb.service;

import com.google.gson.JsonObject;
import com.orionmiz.guildweb.domain.WebBoard;
import com.orionmiz.guildweb.vo.BoardInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardInfo> makeBoardsInfo();
    JsonObject handleSummernoteImageFile(MultipartFile multipartFile);

}
