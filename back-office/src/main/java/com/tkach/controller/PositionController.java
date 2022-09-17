package com.tkach.controller;

import com.tkach.model.Position;
import com.tkach.repositories.PositionRepository;
import com.tkach.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionService positionService;

    @GetMapping
    public String main(
            Map<String, Object> model
    ) {
        Iterable<Position> positions = positionRepository.findAll();
        model.put("positions", positions);

        return "position/index";
    }

    @PostMapping
    public String add(@RequestParam String name, Map<String, Object> model
    ) {
        Position position = new Position(name);
        positionRepository.save(position);

        Iterable<Position> positions = positionRepository.findAll();
        model.put("positions", positions);

        return "position/index";
    }

    @GetMapping("{position}")
    public String positionEditForm(
            @PathVariable Position position,
            Model model
    ) {
        model.addAttribute("position", position);

        return "position/edit";
    }

    @PostMapping("/edit")
    public String positionSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("positionId") Position position
    ) {
        position.setName(name);
        positionRepository.save(position);

        return "redirect:/position";
    }

    @GetMapping("delete/{position}")
    public String delete(
            @PathVariable Position position
    ) {
        positionService.delete(position);

        return "redirect:/position";
    }
}
