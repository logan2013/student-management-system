package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import cn.imust.ys.springbootshiro.modules.student.repository.PunishmentRepository;
import cn.imust.ys.springbootshiro.modules.student.service.PunishmentService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("punishment")
public class PunishmentController {

    @Autowired private PunishmentRepository punishmentRepository;
    @Autowired private PunishmentService punishmentService;

    @PostMapping("save")
    public Map save(@RequestBody Punishment punishment){
        punishmentService.save(punishment);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Punishment punishment){
        punishmentRepository.delete(punishment);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Punishment punishment){
        punishmentService.update(punishment);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(punishmentRepository.findAll());
    }
}
