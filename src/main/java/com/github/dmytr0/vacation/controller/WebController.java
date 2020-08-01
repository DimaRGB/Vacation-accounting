package com.github.dmytr0.vacation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebController {

    @Value("${google.domain.verification:}")
    private String googleVerification;

    @RequestMapping({"/", "/policy", "/employees**/**", "/settings**/**", "/unauthorized**"})
    public String indexPage(ModelMap model, Principal principal) {
        model.addAttribute("authorized", principal != null);
        return "index";
    }

    @ResponseBody
    @RequestMapping({"/google**"})
    public String google() {
        return googleVerification;
    }

}
