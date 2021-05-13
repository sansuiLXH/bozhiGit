package com.sansui.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 14:21
 * @modified By  西西里_SanSui in 2021/5/12 14:21
 * @description AddDescriptionHere
 */
public class JedisDriver {

    public static final int REDIS_PORT = 6379;
    public static final String REDIS_IP = "127.0.0.1";

    public Jedis getJedisConnection() {
        // 1. 创建JedisPoolConfig配置文件
        JedisPoolConfig config = new JedisPoolConfig();

        // 2. 设置一些配置文件的参数
        config.setMaxTotal(30);
        config.setMaxIdle(10);

        // 3. 实例化JedisPool
        JedisPool jedisPool = new JedisPool(config, REDIS_IP, REDIS_PORT);

        // 4. 从JedisPool中获取Jedis实例
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return jedis;

    }
}
