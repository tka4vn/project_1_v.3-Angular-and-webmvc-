package com.tkach.controller;

import com.tkach.model.*;
import com.tkach.repositories.IngressRepository;
import com.tkach.repositories.RequestRepository;
import com.tkach.repositories.RolesRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private IngressRepository ingressRepository;
    @Autowired
    private RequestService requestService;

    @GetMapping
    public String requestList(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Request> request = requestRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            request = requestRepository.findByDateReq(filter);
        } else {
            request = requestRepository.findAll();
        }

        model.addAttribute("request", request);
        model.addAttribute("filter", filter);

        Iterable<Services> services = servicesRepository.findAll();
        model.addAttribute("services", services);

        return "request/index";
    }

    @PostMapping
    public String addRequest(
            @AuthenticationPrincipal Users idReqUse,
            @RequestParam String dateReq,
            @RequestParam Services idReqSer,
            @RequestParam Roles idReqRol,
            @RequestParam Status idReqSta,
            Map<String, Object> model
    ) {
        Request request = new Request(dateReq, idReqUse, idReqSer, idReqRol, idReqSta);
        requestRepository.save(request);

        Iterable<Request> requests = requestRepository.findAll();
        model.put("request", requests);
        return "redirect:/request";
    }

    @GetMapping("/ingressNew/{requestId}/{status}")
    public String accessStatusRequest(
            @PathVariable("requestId") Request request,
            @PathVariable("status") Status idReqSta
    ) {
        request.setIdReqSta(idReqSta);
        requestRepository.save(request);

        return "redirect:/request";
    }

    @GetMapping("ingressNewAdmin/{user}/{services}/{role}/{requestId}/{status}")
    public String newServicesEditForm(
            @PathVariable("user") Users idIngUse,
            @PathVariable("services") Services idIngSer,
            @PathVariable("role") Roles idIngRol,
            @PathVariable("requestId") Request request,
            @PathVariable("status") Status idReqSta
    ) {
        Ingress ingress = new Ingress(idIngUse, idIngSer, idIngRol);
        ingressRepository.save(ingress);

        request.setIdReqSta(idReqSta);
        requestRepository.save(request);

        return "redirect:/request";
    }

    @GetMapping("/ingressRemove/{requestId}/{status}/{idIngress}")
    public String accessStatusRequestRemove(
            @PathVariable("requestId") Request request,
            @PathVariable("status") Status idReqSta,
            @PathVariable("idIngress") Ingress ingress
    ) {
        request.setIdReqSta(idReqSta);
        requestRepository.save(request);

        ingressRepository.delete(ingress);

        return "redirect:/request";
    }

    @GetMapping("delete/{request}")
    public String delete(
            @PathVariable Request request
    ) {
        requestService.delete(request);

        return "redirect:/request";
    }

}
