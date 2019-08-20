package com.mirana.provider.service.impl;

import com.mirana.provider.service.DistributionLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DistributionLockServiceImpl implements DistributionLockService {

    @Autowired
   // private RedisTemplate redis;


    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String IS_LOCKED = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * @param lockName the name of the lock
     * @param uniqueCode uniqueCode for the lock
     * @param expireTime expire time of lock
     * @return the result of get lock
     * */
    @Override
    public boolean getLock(String lockName, String uniqueCode, int expireTime) {

        boolean isLock = false;
        try {
            String result="";
         //   String result = redis.set(lockName, uniqueCode, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            isLock = IS_LOCKED.equalsIgnoreCase(result) ? true : false;

        } catch (Exception e){
            e.printStackTrace();
        }
        return isLock;
    }
    /**
     * @param lockName the name of the lock
     * @param uniqueCode uniqueCode for the lock
     * */
    @Override
    public void releaseLock(String lockName, String uniqueCode) {
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                    "then return redis.call('del', KEYS[1]) " +
                    "else return 0 end";
            //jedis.eval(script, Arrays.asList(lockName), Arrays.asList(uniqueCode));

        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
