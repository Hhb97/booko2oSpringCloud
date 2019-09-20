package com.book.util;

import redis.clients.jedis.Jedis;

public class jedisUtil {
    public static Long expire(String key,int exTime){//设置有效期 exTime单位为秒
        Jedis jedis =null;
        Long result = null;
        try{
            jedis = jedisPool.getJedis();
            result = jedis.expire(key,exTime);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
            return null;
        }
        jedisPool.returnResource(jedis);
        return result;
    }
    //exTime的单位是秒
    //同上直接设置key 的有效期
    public static String setEx(String key,String value,int exTime){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getJedis();
            result = jedis.setex(key,exTime,value);
        } catch (Exception e) {

            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    public static String set(String key,String value){//添加key-value
        Jedis jedis = null;
        String result = null;

        try {
            jedis = jedisPool.getJedis();
            result = jedis.set(key,value);
        } catch (Exception e) {

            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key){//通过key获取value
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {

            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){//删除key
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {

            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

   /* public static void main(String[] args) {//测试
        Jedis jedis = jedisPool.getJedis();


        String value = jedisUtil.get("username");
        System.out.println(value);

        System.out.println("end");
        //结果
        //hhbGeek
        //hash list等数据结构暂未封装；

    }*/
}
