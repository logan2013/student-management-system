package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.service.JobService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("import")
    public Map importData(@RequestBody String params){
        Map data = jobService.analytical(params);
        SessionUtils.getSession().setAttribute("data",data);
        return ControllerUtils.getSuccessMap(data);
    }

    @PostMapping("template")
    public Map template(){
        List dt = jobService.getDownloadTemplate();
        return ControllerUtils.getSuccessMap(dt);
    }

    @GetMapping("saveImport")
    public Map saveImport(){
        Map data = (Map)SessionUtils.getSession().getAttribute("data");
        try {
            jobService.batchSave((List)data.get("listData"));
        }catch (CustomException e){
            return ControllerUtils.getCustomException(e.getMessage());
        }
        return ControllerUtils.getSuccessMap();
    }

    @GetMapping("cancelImport")
    public Map cancelImport(){
        SessionUtils.getSession().removeAttribute("data");
        return ControllerUtils.getSuccessMap();
    }
}
