package com.mirana.provider.service;

public interface DistributionLockService {

    /**
     * @param lockName the name of the lock
     * @param uniqueCode uniqueCode for the lock
     * @param expireTime expire time of lock(MILLISECONDS)
     * @return the result of get lock
     * */
    boolean getLock(String lockName, String uniqueCode, int expireTime);

    /**
     * @param lockName the name of the lock
     * @param uniqueCode uniqueCode for the lock
     * */
    void releaseLock(String lockName, String uniqueCode);
}
