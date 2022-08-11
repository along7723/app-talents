package com.fanye.modules.talents.count;

import com.fanye.modules.core.task.BaseAbstractTask;
import com.fanye.modules.core.task.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TalentsCount extends BaseAbstractTask {
    @Override
    public void execute() {
        log.info("TalentsCount run!!!");
    }
}
