package com.fh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionPool {

    //最大连接数
    private static final int MAX_TOTAL = 5;

    //最小空闲连接数
    private static final int MIN_IDLE = 1;

    //最大空闲连接数
    private static final int MAX_IDLE = 4;

    //Redis服务IP地址
    private static final String REDIS_SERVER_IP = "192.168.26.130";

    //Redis服务端口号
    private static final int REDIS_SERVER_PORT = 6379;

    private static JedisPool jedisPool;

    static{
        initPool();
    }

    private static void initPool(){
        //实例化一个Jedis连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMinIdle(MIN_IDLE);
        config.setMaxIdle(MAX_IDLE);
        //实例化一个Jedis连接池对象
        jedisPool = new JedisPool(config,REDIS_SERVER_IP,REDIS_SERVER_PORT);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
