package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.service.JobService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired private JobRepository jobRepository;

    @Autowired private JobService jobService;

    @RequestMapping("findAll")
    public Map findAll(){
        return ControllerUtils.getSuccessMap(jobRepository.findAll());
    }

    @PostMapping("save")
    public Map save(@RequestBody Job job){
        jobService.save(job);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("delete")
    public Map delete(@RequestBody Job job){
        jobRepository.delete(job);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Job job){
        jobService.update(job);
        return ControllerUtils.getSuccessMap();
    }
}
