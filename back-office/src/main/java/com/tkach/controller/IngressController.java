package com.tkach.controller;

import com.tkach.model.Ingress;
import com.tkach.repositories.IngressRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.IngressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingress")
public class IngressController {

    @Autowired
    private IngressRepository ingressRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private IngressService ingressService;

    @GetMapping
    public String ingressList(
            Model model
    ) {
        Iterable<Ingress> ingress = ingressRepository.findAll();
        model.addAttribute("ingress", ingress);

        return "ingress/index";
    }

    @GetMapping("delete/{ingress}")
    public String delete(
            @PathVariable Ingress ingress
    ) {
        ingressService.delete(ingress);
        return "redirect:/ingress";
    }
}
