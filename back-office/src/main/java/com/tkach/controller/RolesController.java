package com.tkach.controller;

import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.repositories.RolesRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private RolesService rolesService;

    @GetMapping
    public String rolesList(
            Map<String, Object> model
    ) {
        Iterable<Roles> roles = rolesRepository.findAll();
        model.put("roles", roles);
        return "roles/index";
    }

    @PostMapping
    public String add(@RequestParam String name, Map<String, Object> model
    ) {
        Roles role = new Roles(name);
        rolesRepository.save(role);

        Iterable<Roles> roles = rolesRepository.findAll();
        model.put("roles", roles);
        return "roles/index";
    }

    @GetMapping("{roles}")
    public String rolesEditForm(
            @PathVariable Roles roles,
            Map<String, Object> model2,
            Model model
    ) {
        model.addAttribute("roles", roles);
        model.addAttribute("idRolSers", roles.getIdRolSer());

        Iterable<Services> services = servicesRepository.findAll();
        model2.put("services", services);

        return "roles/edit";
    }

    @PostMapping("/edit")
    public String rolesSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("rolesId") Roles roles
    ) {
        roles.setName(name);
        rolesRepository.save(roles);

        return "redirect:/roles";
    }

    @GetMapping("delete/{roles}")
    public String delete(
            @PathVariable Roles roles
    ) {
        rolesService.delete(roles);
        return "redirect:/roles";
    }

    @GetMapping("addServices/{roles}/{services}")
    public String addServices(
            @PathVariable Roles roles,
            @PathVariable Services services
    ) {
        rolesService.addServices(roles, services);

        return "redirect:/roles/" + roles.getIdRole();
    }

    @GetMapping("removeServices/{roles}/{services}")
    public String removeRoles(
            @PathVariable Roles roles,
            @PathVariable Services services
    ) {
        rolesService.removeServices(roles, services);

        return "redirect:/roles/" + roles.getIdRole();
    }

}
