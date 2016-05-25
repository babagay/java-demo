package ru.babagay.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import java.util.Deque;

@Controller
public class GreetingController {

//    @Autowired
//    private final Example example;

//    @Autowired
//    public GreetingController(Example example) {
//
//        this.example = example;
//    }

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", name);

        return "greeting";
    }

    @RequestMapping("/")
    public String root(@RequestParam(required = false) Integer page, Model model) {
//        model.addAttribute("name1", name1);
        return "hello";
    }

//    private Deque<String> queue;
//
//    public void addTag(String tag) {
//        queue.push(tag);
//    }

    // inject the actual template
//    @Autowired
//    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, String> listOps;

//    public void addLink(String userId, URL url) {
//        listOps.leftPush(userId, url.toExternalForm());
//    }

}