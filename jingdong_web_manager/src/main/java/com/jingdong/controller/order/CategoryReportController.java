package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.pojo.goods.Category;
import com.jingdong.pojo.order.CategoryReport;
import com.jingdong.goods.service.CategoryService;
import com.jingdong.order.service.CategoryReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoryReport")
@SuppressWarnings("unchecked")
public class CategoryReportController {

    @Reference
    private CategoryReportService categoryReportService;

    /**
     * 昨天的数据统计(测试)
     * @return
     */
    @GetMapping("/yesterday")
    public List<CategoryReport> yesterday(){
        LocalDate localDate = LocalDate.now().minusDays(1);// 得到昨天的日期
        return categoryReportService.categoryReport(localDate);
    }

    /**
     * 统计任务(每天凌晨1点开始统计昨天数据)
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void createDataTask(){
        System.out.println("任务执行了。");
        categoryReportService.createData();
    }


    @Reference
    private CategoryService categoryService;

    /**
     * 统计一级类目
     * @param date1
     * @param date1
     * @return
     */
    @GetMapping("/category1Count")
    public List<Map> category1Count(String date1 ,String date2){

        Map map=new HashMap();
        map.put("parentId",0);
        List<Category> categoryList = categoryService.findList(map);//查询一级分类列表
        Map<Integer, String> categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId,Category::getName ));
        //date1="2019-01-26";
        //data2="2019-01-26";
        List<Map> categoryReports = categoryReportService.category1Count(date1, date2);
        for(Map  report:categoryReports){
            String categoryName = categoryMap.get((Integer)report.get("categoryId1"));
            report.put("categoryName",categoryName);//追加名称
        }
        return categoryReports;

    }



}
