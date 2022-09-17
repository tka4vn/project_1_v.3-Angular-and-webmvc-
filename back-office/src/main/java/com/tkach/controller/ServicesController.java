package com.tkach.controller;

import com.tkach.model.Ingress;
import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.model.Users;
import com.tkach.repositories.IngressRepository;
import com.tkach.repositories.RolesRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private IngressRepository ingressRepository;


    @GetMapping
    public String servicesList(
            Map<String, Object> model
    ) {
        Iterable<Services> services = servicesRepository.findAll();
        model.put("services", services);

        return "services/index";
    }

    @PostMapping
    public String add(@RequestParam String name, Map<String, Object> model
    ) {
        Services service = new Services(name);
        servicesRepository.save(service);

        Iterable<Services> services = servicesRepository.findAll();
        model.put("services", services);

        return "services/index";
    }

    @GetMapping("{services}")
    public String servicesEditForm(
            @PathVariable Services services,
            Map<String, Object> model2,
            Model model
    ) {
        model.addAttribute("services", services);
        model.addAttribute("idSerRols", services.getIdSerRol());
        model.addAttribute("idSerIngs", services.getIdSerIng());

        Iterable<Roles> roles = rolesRepository.findAll();
        model2.put("roles", roles);

        return "services/edit";
    }
    @GetMapping("new/{user}/{services}/{role}")
    public String newServicesEditForm(
            @PathVariable("user") Users idIngUse,
            @PathVariable("role") Roles idIngRol,
            @PathVariable("role") Roles roles,
            @PathVariable("services") Services idIngSer,
            @PathVariable Services services
    ) {
        Ingress ingress = new Ingress(idIngUse, idIngSer, idIngRol);
        ingressRepository.save(ingress);

        servicesService.addRoles(services, roles);

        return "redirect:/services/" + services.getIdServices();
    }

    @PostMapping("/edit")
    public String servicesSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("servicesId") Services services
    ) {
        services.setName(name);
        servicesRepository.save(services);

        return "redirect:/services";
    }

    @GetMapping("delete/{services}")
    public String delete(
            @PathVariable Services services
    ) {
        servicesService.delete(services);
        return "redirect:/services";
    }

    @GetMapping("addRoles/{services}/{roles}")
    public String addRoles(
            @PathVariable Services services,
            @PathVariable Roles roles
    ) {
        servicesService.addRoles(services, roles);

        return "redirect:/services/" + services.getIdServices();
    }

    @GetMapping("removeRoles/{services}/{roles}")
    public String removeRoles(
            @PathVariable Services services,
            @PathVariable Roles roles
    ) {
        servicesService.removeRoles(services, roles);

        return "redirect:/services/" + services.getIdServices();
    }

}
