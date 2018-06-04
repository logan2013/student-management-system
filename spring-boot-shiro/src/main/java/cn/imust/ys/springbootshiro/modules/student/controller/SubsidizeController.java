package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Subsidize;
import cn.imust.ys.springbootshiro.modules.student.repository.SubsidizeRepository;
import cn.imust.ys.springbootshiro.modules.student.service.SubsidizeService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
