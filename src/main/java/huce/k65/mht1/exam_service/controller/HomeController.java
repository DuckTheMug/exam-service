package huce.k65.mht1.exam_service.controller;

import huce.k65.mht1.exam_service.constant.HomeConstant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class HomeController {
    public String index() {
        return HomeConstant.HOME;
    }
}
