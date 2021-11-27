package com.jingdong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;

/**
 * 第一次我用一个4M的文件：
 * fileName：test.rar
 * 方法一的运行时间：14712ms
 * fileName：test.rar
 * 方法二的运行时间：5ms
 *
 *
 * 第二次：我用一个50M的文件
 * 方式一进度很慢，估计得要个5分钟
 * 方法二的运行时间：67ms
 */
@RestController
@RequestMapping("/common-upload")
public class CommonUploadController {

    @Autowired
    private HttpServletRequest request;
    /*<form name="Form2" action="/xxx/xx" method="post"  enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="upload"/>
     </form>*/

    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("fileUpload")
    public String  fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());

        try {
            //获取输出流
            OutputStream os=new FileOutputStream("E:/"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success";
    }

    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("upload-img")
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="E:/"+new Date().getTime()+file.getOriginalFilename();
        String path2=request.getSession().getServletContext().getRealPath("images");
        File newFile=new File(path);
        if(!newFile.getParentFile().exists()){
            newFile.mkdirs();
        }
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success";
    }
}
