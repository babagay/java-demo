package ru.babagay.web.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.net.URL;


public class Example {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addLink(String userId, URL url) {
        redisTemplate.opsForList().leftPush(userId, url.toExternalForm());
    }
}

/*
public class Example {



    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {

        listOps.leftPush(userId, url.toExternalForm());
    }

    public void addStrLink(String userId, URL url) {
        redisTemplate.opsForList().leftPush(userId, url.toExternalForm());
    }
}
*/