package ru.babagay.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

//    Could not autowire
//    @Autowired
//    public GreetingController(Example example) {
//
//        this.example = example;
//    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addLink(String userId, String url) {
        redisTemplate.opsForList().leftPush(userId, url);
    }


    /**
     * Test of redis list
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        /**
         * После вызова метода вызов команды LINDEX uid:123 0 даст "localhost"
         */
        addLink("uid:123", "localhost");

        /**
         * Получение переменной из списка
         */
        String UID = redisTemplate.opsForList().range("uid:123", 0, 10).get(0);

        model.addAttribute("name", UID + " item");

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