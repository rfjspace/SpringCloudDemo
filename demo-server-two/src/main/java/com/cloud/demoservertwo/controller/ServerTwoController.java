package com.cloud.demoservertwo.controller;

import com.cloud.demoservercommon.model.InMoney;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server-two")
public class ServerTwoController {

    @PostMapping("/one")
    public String one(){
        return "/server-two/one";
    }

    @PostMapping("/two")
    public String two(@RequestBody InMoney im){
        return "/server-two/two:".concat(im.toString());
    }
}
