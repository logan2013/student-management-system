package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.service.JobService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @RequestMapping("findAll")
    public Map findAll() {
        return ControllerUtils.getSuccessMap(jobRepository.findAll());
    }

    @PostMapping("save")
    public Map save(@RequestBody Job job) {
        jobService.save(job);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("delete")
    public Map delete(@RequestBody Job job) {
        jobRepository.delete(job);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Job job) {
        jobService.update(job);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("import")
    public Map importData(@RequestBody String params) {
        Map data = ImportUtils.analytical(params, jobService.getTemplate());
        SessionUtils.getSession().setAttribute("data", data);
        return ControllerUtils.getSuccessMap(data);
    }

    @GetMapping("template")
    public Map template() {
        List dt = ImportUtils.getDownloadTemplate(jobService.getTemplate());
        return ControllerUtils.getSuccessMap(dt);
    }

    @PostMapping("saveImport")
    public Map saveImport() {
        Map data = (Map) SessionUtils.getSession().getAttribute("data");
        jobService.batchSave((List) data.get("listData"));
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("filter")
    public Map filter(@RequestBody String params){
        List<Job> jobs = jobService.filter(params);
        return ControllerUtils.getSuccessMap(jobs);
    }

}
