package com.example.demoheaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoheadersApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoheadersApplication.class, args);
    }


    @Controller
    public static class Home {

        @GetMapping("/")
        public String ok() {
            System.out.println("GOT HERE");
            return "redirect:/swagger-ui.html";
        }
    }

    @RestController
    public static class Demo {

        @GetMapping("/demo")
        public Map<String, Object> demo(@RequestHeader Map<String, String> headers, HttpServletRequest servletRequest) {
            Map<String, Object> map = new HashMap<>();
            map.put("headers", headers);
            map.put("remoteip", servletRequest.getRemoteAddr());
            return map;
        }

    }

    @Controller
    public static class RedirectDemo {

        @GetMapping("/rdr")
        public void demo(HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
            response.sendRedirect("/hello");
        }

    }

    @RestController
    public static class Hello {

        @GetMapping("/hello")
        public String demo(HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
            return "HELLO > " + LocalDateTime.now();
        }

    }

}
