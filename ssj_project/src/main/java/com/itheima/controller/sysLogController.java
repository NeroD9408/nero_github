package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysLog")
public class sysLogController {

    @Autowired
    private SysLogService sysLogService;

    /*@RequestMapping("findAll")
    public String findAll(Model model,
                          @RequestParam(required = false, defaultValue = "1") int currentPage,
                          @RequestParam(required = false, defaultValue = "10") int rows) throws Exception {
        PageBean<SysLog> sysLogs = sysLogService.findAll(currentPage, rows);
        model.addAttribute("pb", sysLogs);
        return "syslog-list";
    }*/
    @RequestMapping("findAll")
    public String findAll(String currentPage, String rows) throws Exception {
        return "syslog-list";
    }

    @RequestMapping("/exportTxt")
    @ResponseBody
    public Map<String, Object> exportTxt() throws Exception {
        Map<String, Object> data = new HashMap<>();
        try {
            //从数据库中查询所有信息
            PageBean<SysLog> pb = sysLogService.findAll(1, 9999999);
            List<SysLog> list = pb.getList();
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("D:\\syslog.txt")));
            for (SysLog sysLog : list) {
                bw.write(sysLog.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
            data.put("flag", true);
        } catch (IOException e) {
            e.printStackTrace();
            data.put("flag", false);
        }
        return data;
    }
}
