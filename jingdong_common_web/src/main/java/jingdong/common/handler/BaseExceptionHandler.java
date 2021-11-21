package jingdong.common.handler;

import com.jingdong.model.base.ResultMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// @ControllerAdvice 表示是控制器通知类
@ControllerAdvice
public class BaseExceptionHandler {

    // @ResponseBody 让他把结果输出到
    // @ExceptionHandler 用来指定监测异常类型，这里用的所有Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultMsg error(Exception e) {
        e.printStackTrace();
        System.out.println("产生异常");
        return new ResultMsg(2,e.getMessage());
    }
}
