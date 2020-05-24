package org.arsonal.accounting.controller;

import org.arsonal.accounting.model.service.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    //@RequestMapping(path = "v1/greeting", method = RequestMethod.GET)
    @GetMapping("v1/greeting/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return String.format("Hello, %s", name);
    }

    AtomicLong counter = new AtomicLong();

    @GetMapping("v2/greeting")
    public Greeting sayHelloV2(@RequestParam("name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s", name));
    }
}
