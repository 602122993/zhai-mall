package com.xiaoazhai.util.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key, Class<T> targetClass) {
        if (targetClass == String.class) {
            return key == null ? null : (T) redisTemplate.opsForValue().get(key);
        }
        return key == null ? null : redisTemplate.opsForValue().get(key) == null ? null : JSON.parseObject(String.valueOf(redisTemplate.opsForValue().get(key)), targetClass);
    }

    /**
     * 查询是否有某个key
     *
     * @param key
     * @return
     */
    public boolean containKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {

            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从缓存中获取list
     *
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> List<T> getList(final String field, Class<T> targetClass) {
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(field.getBytes()));
        if (result == null) {
            return null;
        }
        return ProtoStuffUtil.deserializeList(result, targetClass);
    }

    /**
     * 插入list
     *
     * @param field
     * @param objList
     * @param <T>
     * @return
     */
    public <T> void setList(String field, List<T> objList) {
        final byte[] value = ProtoStuffUtil.serializeList(objList);
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.set(field.getBytes(), value);
            return null;
        });
    }

    /**
     * 插入带过期时间的list
     *
     * @param field
     * @param objList
     * @param expireTime
     * @param <T>
     * @return
     */
    public <T> void setListWithExpire(String field, List<T> objList, final long expireTime) {
        final byte[] value = ProtoStuffUtil.serializeList(objList);
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.setEx(field.getBytes(), expireTime, value);
            return null;
        });
    }


    /**
     * 查询list某一结点的值
     *
     * @param key
     * @param index
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T lIndex(String key, int index, Class<T> targetClass) {
        byte[] value =
                redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.lIndex(key.getBytes(), index));
        return ProtoStuffUtil.deserialize(value, targetClass);
    }


    /**
     * push 单条数据
     *
     * @param key
     * @param obj
     * @param <T>
     */
    public <T> void lPush(String key, T obj) {
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.lPush(key.getBytes(), value));
    }

    /**
     * list push插入多个数据
     *
     * @param key
     * @param objList
     * @param <T>
     */
    public <T> void lPush(String key, List<T> objList) {
        List<byte[]> byteFields = objList.stream().map(ProtoStuffUtil::serialize).collect(Collectors.toList());
        byte[][] values = new byte[byteFields.size()][];

        redisTemplate.execute((RedisCallback<Long>) connection -> connection.lPush(key.getBytes(), values));
    }


    /**
     * 精确删除key
     *
     * @param key
     */
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 放一个map
     *
     * @param key
     * @param map
     */
    public <T> void setAllHash(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 插入具有超时时间得map
     *
     * @param key
     * @param map
     * @param time
     * @param <T>
     */
    public <T> void setAllHash(String key, Map<String, T> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 插入一个有时效hash
     *
     * @param key
     * @param map
     * @param time
     * @param <T>
     */
    public <T> void setHash(String key, String field, Object value, long time) {
        redisTemplate.opsForHash().put(key, field, value);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 插入一个hash
     *
     * @param key
     * @param map
     * @param time
     * @param <T>
     */
    public <T> void setHash(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 删除哈希中得某个减值
     *
     * @param key
     * @param field
     */
    public void deleteHash(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    public void deleteByValues(String key, Object value) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        Object userId = null;
        for (Map.Entry<Object, Object> entry :
                map.entrySet()) {
            if (entry.getValue().equals(value)) {
                userId = entry.getKey();
                break;
            }
        }
        if (userId != null) {
            redisTemplate.opsForHash().delete(key, userId);
        }

    }

    /**
     * 获取所有哈希
     *
     * @param key
     * @return
     */
    public Map hashGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取某个value
     *
     * @param key
     * @param field
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getHashValue(String key, String field, Class<T> tClass) {
        if (tClass == null || tClass == String.class) {
            return redisTemplate.opsForHash().get(key, field) == null ? null : (T) redisTemplate.opsForHash().get(key, field);
        }
        return redisTemplate.opsForHash().get(key, field) == null ? null : JSON.parseObject(String.valueOf(redisTemplate.opsForHash().get(key, field)), tClass);
    }


    /**
     * 获取某个value
     *
     * @param key
     * @param field
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getHashValues(String key, Class<T> tClass) {
        return redisTemplate.opsForHash().values(key) == null ? null : JSON.parseArray(String.valueOf(redisTemplate.opsForHash().values(key)), tClass);
    }

    /**
     * 查询是否有某个哈希key
     *
     * @param key
     * @param field
     * @return
     */
    public boolean containHashKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 查询哈希得大小
     *
     * @param key
     * @return
     */
    public Long getHashSize(String key) {
        return redisTemplate.opsForHash().size(key) == null ? 0 : redisTemplate.opsForHash().size(key);
    }

    /**
     * 插入set
     *
     * @param key
     * @param field
     * @return
     */
    public Long addSet(String key, String field) {
        return redisTemplate.opsForSet().add(key, field);
    }

    /**
     * 删除set
     *
     * @param key
     * @param field
     */
    public void deleteSet(String key, String field) {
        redisTemplate.opsForSet().remove(key, field);
    }

    /**
     * 查询set大小
     *
     * @param key
     * @return
     */
    public Long setSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 是否含有set
     *
     * @param key
     * @param field
     * @return
     */
    public boolean containSet(String key, String field) {
        return redisTemplate.opsForSet().isMember(key, field) == null ? false : redisTemplate.opsForSet().isMember(key, field);
    }




}