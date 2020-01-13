package com.fh.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = JedisConnectionPool.getJedis();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public static String get(String key){
        String value = null;
        Jedis jedis = null;
        try {
            jedis = JedisConnectionPool.getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return value;
    }

    public static boolean exists(String key){
        boolean existed = false;
        Jedis jedis = null;
        try {
            jedis = JedisConnectionPool.getJedis();
            existed = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return existed;
    }

}
