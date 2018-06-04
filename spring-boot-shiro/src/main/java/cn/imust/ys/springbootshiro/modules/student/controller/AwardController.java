package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.repository.AwardRepository;
import cn.imust.ys.springbootshiro.modules.student.service.AwardService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("award")
public class AwardController {

    @Autowired private AwardRepository awardRepository;

    @Autowired private AwardService awardService;

    @PostMapping("save")
    public Map save(@RequestBody Award award){
        awardService.save(award);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Award award){
        awardRepository.delete(award);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Award award){
        awardService.update(award);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(awardRepository.findAll());
    }
}
