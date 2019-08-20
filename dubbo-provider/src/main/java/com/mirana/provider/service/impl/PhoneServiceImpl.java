package com.mirana.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mirana.api.model.Phone;
import com.mirana.api.service.IPhoneService;

import java.math.BigDecimal;

@Service(version = "1.0.0")
public class PhoneServiceImpl implements IPhoneService {


    /**
     * 根据id查找手机
     *
     * @param id
     * @return
     */
    @Override
    public Phone findById (Long id) {
        Phone phone = new Phone();
        phone.setId(id);
        phone.setName("iphonex");
        phone.setPrice(BigDecimal.valueOf(8848.88));
        return phone;
    }
}
