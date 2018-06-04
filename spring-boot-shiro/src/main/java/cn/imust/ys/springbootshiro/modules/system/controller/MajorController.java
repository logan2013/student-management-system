package cn.imust.ys.springbootshiro.modules.system.controller;

import cn.imust.ys.springbootshiro.modules.system.entity.Major;
import cn.imust.ys.springbootshiro.modules.system.repository.MajorRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("major")
public class MajorController {

    @Autowired private MajorRepository majorRepository;

    @PostMapping("save")
    public Map save(@RequestBody Major major){
        return ControllerUtils.getSuccessMap(majorRepository.save(major));
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Major major){
        majorRepository.delete(major);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Major major){
        majorRepository.saveAndFlush(major);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(majorRepository.findAll());
    }
}
