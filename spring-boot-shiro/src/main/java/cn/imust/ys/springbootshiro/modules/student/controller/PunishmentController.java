package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Punishment;
import cn.imust.ys.springbootshiro.modules.student.repository.PunishmentRepository;
import cn.imust.ys.springbootshiro.modules.student.service.PunishmentService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("import")
    public Map importData(@RequestBody String params){
        Map data = ImportUtils.analytical(params,punishmentService.getTemplate());
        SessionUtils.getSession().setAttribute("data",data);
        return ControllerUtils.getSuccessMap(data);
    }

    @GetMapping("template")
    public Map template(){
        List dt = ImportUtils.getDownloadTemplate(punishmentService.getTemplate());
        return ControllerUtils.getSuccessMap(dt);
    }

    @GetMapping("saveImport")
    public Map saveImport(){
        Map data = (Map)SessionUtils.getSession().getAttribute("data");
        punishmentService.batchSave((List)data.get("listData"));
        return ControllerUtils.getSuccessMap();
    }

}
