package cn.imust.ys.springbootshiro.modules.student.controller;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.repository.AwardRepository;
import cn.imust.ys.springbootshiro.modules.student.service.AwardService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.ImportUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("award")
public class AwardController {

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private AwardService awardService;

    @PostMapping("save")
    public Map save(@RequestBody Award award) {
        awardService.save(award);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("delete")
    public Map delete(@RequestBody Award award) {
        awardRepository.delete(award);
        return ControllerUtils.getSuccessMap();
    }

    @PostMapping("update")
    public Map update(@RequestBody Award award) {
        awardService.update(award);
        return ControllerUtils.getSuccessMap();
    }

    @RequestMapping("findAll")
    public Map findAll() {
        return ControllerUtils.getSuccessMap(awardRepository.findAll());
    }

    @PostMapping("import")
    public Map importData(@RequestBody String params) {
        Map data = ImportUtils.analytical(params, awardService.getTemplate());
        SessionUtils.getSession().setAttribute("data", data);
        return ControllerUtils.getSuccessMap(data);
    }

    @GetMapping("template")
    public Map template() {
        List dt = ImportUtils.getDownloadTemplate(awardService.getTemplate());
        return ControllerUtils.getSuccessMap(dt);
    }

    @GetMapping("saveImport")
    public Map saveImport() {
        Map data = (Map) SessionUtils.getSession().getAttribute("data");
        awardService.batchSave((List) data.get("listData"));
        return ControllerUtils.getSuccessMap();
    }

}
