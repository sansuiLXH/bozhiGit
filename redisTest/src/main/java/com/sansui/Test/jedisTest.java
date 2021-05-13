package com.sansui.Test;

import com.alibaba.fastjson.JSONObject;
import com.sansui.entity.Student;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 14:24
 * @modified By  西西里_SanSui in 2021/5/12 14:24
 * @description AddDescriptionHere
 */
public class jedisTest {

    @Test
    public void jedistest1(){
// 1. 创建JedisPoolConfig配置文件
        JedisPoolConfig config = new JedisPoolConfig();

        // 2. 设置一些配置文件的参数
        config.setMaxTotal(30);
        config.setMaxIdle(10);

        // 3. 实例化JedisPool
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        // 4. 从JedisPool中获取Jedis实例
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            // 5. 赋值测试
//            jedis.set("name", "test");
//            String value = jedis.get("name");
//            String id = 1+"";
//            Double avgscore = 88.0;
//            String str ="1"+""+"cc"+""+"1999-06-10"+""+"come";
//            String str2 ="2"+""+"aa"+""+"1999-06-10"+""+"come";
//            String str3 ="3"+""+"ee"+""+"1999-06-10"+""+"come";
//            String str4 ="4"+""+"bb"+""+"1999-06-10"+""+"come";
//            String str5 ="5"+""+"dd"+""+"1999-06-10"+""+"come";
            Student stu = new Student("3","三花","1999-08-23","备注说明sdas",57.0);

            String str7  =JSONObject.toJSONString(stu);
//            jedis.zadd("student",57.0,str7);
            String id = "4";
            String name = "林雾阳";
            String birthday = "1998-09-09";
            String description = "测试更新";
            Double avgscore = 84.0;
            Student s2 = new Student();
            s2.setId(id);
            s2.setName(name);
            s2.setBirthday(birthday);
            s2.setDescription(description);
            s2.setAvgscore(avgscore);
            String s2j = JSONObject.toJSONString(s2);
            System.out.println("s2j--"+s2j);
            Set<String> temp = jedis.zrevrange("student", 0, -1);
            System.out.println("temp--->"+temp);
            for (String s : temp) {
                Student parse = JSONObject.parseObject(s, Student.class);
                System.out.println("进入了");
                if(id.equals(parse.getId()) ){
                    String deletestr = JSONObject.toJSONString(parse);
                    System.out.println("开始移除");
                    jedis.zrem("student",deletestr);
                    System.out.println("开始新增");
                    jedis.zadd("student",avgscore,s2j);
                }
                System.out.println(parse.toString());
            }
//            jedis.zadd("student",avgscore+10,str2);
//            jedis.zadd("student",avgscore-10,str3);
//            jedis.zadd("student",avgscore+4,str4);
//            jedis.zadd("student",avgscore-5,str5);
//            jedis.zrem("student",str,str2,str3,str4,str5);
//            System.out.println(jedis.get(id));
//            System.out.println(value);
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            // 6. 关闭连接资源
            if (jedis != null) {
               // jedis.close();
            }
            if (jedisPool != null) {
               // jedisPool.close();
            }

        }
    }
}
