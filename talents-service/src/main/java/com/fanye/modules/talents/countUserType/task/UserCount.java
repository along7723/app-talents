package com.fanye.modules.talents.countUserType.task;

import com.fanye.modules.core.task.BaseAbstractTask;
import com.fanye.modules.core.task.ScheduledTask;
import com.fanye.modules.sys.manage.user.service.ISysCoreUserService;
import com.fanye.modules.talents.countUserType.entity.TalCountUser;
import com.fanye.modules.talents.countUserType.service.ITalCountUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class UserCount extends BaseAbstractTask {

    @DubboReference
    ISysCoreUserService userService;

    @DubboReference
    ITalCountUserService countUserService;
    @Override
    public void execute() {
        List<Map<String, Object>>  list=userService.countUserType();
        list.forEach(item->{
            TalCountUser talCountUser=new TalCountUser();
            talCountUser.setCountNum(Long.parseLong(item.get("result").toString()));
            talCountUser.setUserType(item.get("role_name").toString());
            talCountUser.setCountTime(new Date());
            if (countUserService!=null){
                countUserService.add(talCountUser);
            }else {
                log.error("没有找到服务，ITalCountUserService");
            }
        });
    }
}
