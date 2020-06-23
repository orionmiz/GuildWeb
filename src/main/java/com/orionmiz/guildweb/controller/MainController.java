package com.orionmiz.guildweb.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class MainController {

    @GetMapping("/home")
    public void home() {

    }

}
