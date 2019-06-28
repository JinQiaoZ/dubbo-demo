package com.mirana.api.service;

import com.mirana.api.model.Phone;

public interface IPhoneService {

    /**
     * 根据id查找手机
     *
     * @param id
     * @return
     */
    Phone findById (Long id);
}
