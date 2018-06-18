package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.student.repository.SubsidizeRepository;
import cn.imust.ys.springbootshiro.modules.student.service.SubsidizeService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("subsidize")
public class SubsidizeController {

    @Autowired private SubsidizeRepository subsidizeRepository;
    @Autowired private SubsidizeService subsidizeService;

    @PostMapping("save")
    public Map save(@RequestBody Subsidize subsidize){
        subsidizeService.save(subsidize);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Subsidize subsidize){
        subsidizeRepository.delete(subsidize);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Subsidize subsidize){
        subsidizeService.update(subsidize);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(subsidizeRepository.findAll());
    }

    @PostMapping("import")
    public Map importData(@RequestBody String params){
        Map data = ImportUtils.analytical(params,subsidizeService.getTemplate());
        SessionUtils.getSession().setAttribute("data",data);
        return ControllerUtils.getSuccessMap(data);
    }

    @GetMapping("template")
    public Map template(){
        List dt = ImportUtils.getDownloadTemplate(subsidizeService.getTemplate());
        return ControllerUtils.getSuccessMap(dt);
    }

    @PostMapping("saveImport")
    public Map saveImport(){
        Map data = (Map)SessionUtils.getSession().getAttribute("data");
        subsidizeService.batchSave((List)data.get("listData"));
        return ControllerUtils.getSuccessMap();
    }

}
