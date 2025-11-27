package com.bielecki.Socket.Data.Provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/hello")
    public Map<String, Object> hello() {

        SystemInfo sysInf = new SystemInfo();
        return sysInf.getSystemInfo();

    }

}
