package cn.imust.ys.springbootshiro.modules.student.service;

import cn.imust.ys.springbootshiro.exception.CustomException;
import cn.imust.ys.springbootshiro.modules.student.entity.Job;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import cn.imust.ys.springbootshiro.modules.student.repository.JobRepository;
import cn.imust.ys.springbootshiro.modules.student.repository.StudentRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class JobService {

    @Autowired private JobRepository jobRepository;
    @Autowired private StudentRepository studentRepository;

    public void save(Job job){
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            job.setStudent(bySno);
            jobRepository.save(job);
        }
    }

    public void batchSave(List<Map> listData) {
        Job job = null;
        for (Map map : listData){
            job = new Job();
            /**
             *         templateMap.put("就业时间","jtime");
             * */
            String sno = (String) map.get("sno");
            Student bySno = studentRepository.findBySno(sno);
            if(bySno == null){
                throw new CustomException("学号为：" + sno + "的学生未找到！请修改后重新导入");
            }
            job.setStudent(bySno);
            job.setJobunit((String) map.get("jobunit"));
            job.setFileAddress((String) map.get("fileAddress"));
            job.setJobwhere((String) map.get("jobwhere"));
            job.setMode((String) map.get("mode"));
            jobRepository.save(job);
        }
    }

    public void update(Job job){
        String sno = job.getJobsno();
        Student bySno = studentRepository.findBySno(sno);
        if(bySno !=null){
            job.setStudent(bySno);
            jobRepository.saveAndFlush(job);
        }
    }

    public Map analytical(String params) {
        Map map = new HashMap();
        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray jsonArrayColumns = (JSONArray) paramsMap.get("0");// 0 是标题
        List listColumns = getColumnsList(jsonArrayColumns);
        List listData = getListData(paramsMap, listColumns);
        map.put("columns",listColumns);
        map.put("listData",listData);
        return map;
    }

    private List getListData(Map<String, Object> paramsMap, List listColumns) {
        JSONArray jsonArrayColumns;
        int count = 1;
        List listData = new ArrayList();
        HashMap mapData = null;
        while ( (jsonArrayColumns = (JSONArray) paramsMap.get(count + "")) != null){
            Object[] objects = jsonArrayColumns.toArray();
            mapData = new HashMap();
            for(int i = 0 ; i < listColumns.size(); i ++){
                mapData.put(((HashMap)listColumns.get(i)).get("index"),(String)objects[i]);
            }
            listData.add(mapData);
            count ++ ;
        }
        return listData;
    }

    private List getColumnsList(JSONArray jsonArrayColumns) {
        Object[] objects = jsonArrayColumns.toArray();
        List listColumns = new ArrayList();
        HashMap mapColumns = null;
        for (Object obj: objects){
            mapColumns = new HashMap();
            mapColumns.put("title",(String)obj);
            mapColumns.put("index",getIndexByTitle((String)obj));
            listColumns.add(mapColumns);
    }
        return listColumns;
    }

    private String getIndexByTitle(String obj) {
        Map<String,String> map = getTemplate();
        return map.get(obj);
    }

    public List getDownloadTemplate() {
        List listColumns = new ArrayList();
        Map template = getTemplate();
        Iterator iterator = template.entrySet().iterator();
        Map map = null;
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            map = new HashMap();
            map.put("title",entry.getKey());
            listColumns.add(map);
        }
        return listColumns;
    }

    private Map<String,String> getTemplate(){
        Map templateMap = new LinkedHashMap();
        templateMap.put("姓名","sname");
        templateMap.put("学号","sno");
        templateMap.put("方式","mode");
        templateMap.put("就业单位","jobunit");
        templateMap.put("就业时间","jtime");
        templateMap.put("档案转递地址","fileAddress");
        templateMap.put("就业去向","jobwhere");
        return templateMap;
    }

}
