package com.orionmiz.guildweb.controller;

import com.google.gson.JsonObject;
import com.orionmiz.guildweb.domain.WebBoard;
import com.orionmiz.guildweb.persistence.WebBoardRepository;
import com.orionmiz.guildweb.service.BoardService;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Log
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    WebBoardRepository repo;

    @Autowired
    BoardService service;

    @GetMapping("/list")
    public void list(Model model) {

        model.addAttribute("infos", service.makeBoardsInfo());

    }

    @GetMapping("/post")
    public void post() {

    }

    @PostMapping("/post")
    public String write(@ModelAttribute("vo")WebBoard board) {

        log.info(board.getEditordata());

        repo.save(board);

        return "redirect:/boards/list";
    }

    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        // JsonObject contains url of uploaded image.
        return service.handleSummernoteImageFile(multipartFile);

    }

    @GetMapping("/view")
    public void view(@RequestParam("bno")Long bno, Model model) {
        repo.findById(bno).ifPresent(board -> {
            model.addAttribute("content", board.getEditordata());
        });
    }


}
