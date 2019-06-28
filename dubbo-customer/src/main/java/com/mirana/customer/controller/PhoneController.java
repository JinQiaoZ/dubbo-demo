package com.mirana.customer.controller;

import com.mirana.api.model.Phone;
import com.mirana.api.service.IPhoneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Resource
    private IPhoneService phoneService;

    /**
     * @return
     */
    @RequestMapping("/findById")
    public Phone findById (Long id) {
        return phoneService.findById(id);
    }
}
