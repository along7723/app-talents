package com.fanye.modules.talents.countCompany.task;

import com.fanye.modules.core.task.ScheduledTask;
import com.fanye.modules.talents.countCompany.entity.TalCountCompany;
import com.fanye.modules.talents.countCompany.service.ITalCountCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class CompanyCount implements ScheduledTask {

    @DubboReference
    ITalCountCompanyService service;

    @Override
    public void execute() {
        int count =service.queryQuantityChange();
        TalCountCompany talCountCompany=new TalCountCompany();
        talCountCompany.setCountNum(Integer.toUnsignedLong(count));
        talCountCompany.setCountTime(new Date());
        service.add(talCountCompany);
    }
}
