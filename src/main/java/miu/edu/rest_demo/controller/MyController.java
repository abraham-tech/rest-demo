package miu.edu.rest_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return "Hello "+name;
    }

    @GetMapping("/hello/full-name")
    public String sayName(@RequestParam String firstName, @RequestParam String lastName) {
        return "Hello, "+firstName+" "+lastName;
    }

    @GetMapping("/another-hello")
    public ResponseEntity<String> sayAnotherHello(){
        return ResponseEntity.ok("Hello World Another 1");
    }

    @GetMapping("/another-hello/{name}")
    public String sayAnotherHello2(@PathVariable("name") String myName){
        return "Hello World "+myName;
    }


    @GetMapping("test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
