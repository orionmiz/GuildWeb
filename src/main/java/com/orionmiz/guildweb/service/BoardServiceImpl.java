package com.orionmiz.guildweb.service;

import com.google.gson.JsonObject;
import com.orionmiz.guildweb.domain.WebBoard;
import com.orionmiz.guildweb.persistence.WebBoardRepository;
import com.orionmiz.guildweb.vo.BoardInfo;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Log
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    WebBoardRepository repo;

    //@Override
    public Map<Long, String> getThumbNailUrl() {

        Map<Long, String> urls = new HashMap<>();

        repo.findAll().forEach(board -> {
            urls.put(board.getBno(), findFirstImgUrlInArticle(board));
        });

        return urls;
    }

    @Override
    public List<BoardInfo> makeBoardsInfo() {
        List<BoardInfo> infos = new ArrayList<>();

        repo.findAll().forEach(board -> {

            BoardInfo info = new BoardInfo();

            Document doc = Jsoup.parse(board.getEditordata());

            Elements imgs = doc.getElementsByTag("img");

            info.setBno(board.getBno());
            info.setPictures(imgs.size());

            // TODO: comments, likes
            info.setComments(0);
            info.setLikes(0);

            if (imgs.size() > 0) {
                info.setContent(imgs.first().attr("src"));
            } else {
                info.setContent(doc.getAllElements().first().text());
            }
            infos.add(info);
        });

        return infos;
    }

    private String findFirstImgUrlInArticle(WebBoard board) {

        log.info("!! 데이터 !! : " + board.getEditordata());

        Document doc = Jsoup.parse(board.getEditordata());

        Elements imgs = doc.getElementsByTag("img");

        String url = null;

        if (imgs.size() > 0) {
            url = imgs.first().attr("src");
        } else {
            url = doc.getAllElements().first().toString();
        }

        return url;
    }

    @Override
    public JsonObject handleSummernoteImageFile(MultipartFile multipartFile) {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }
}
