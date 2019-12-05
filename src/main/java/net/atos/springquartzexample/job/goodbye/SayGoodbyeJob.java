package net.atos.springquartzexample.job.goodbye;

import lombok.extern.slf4j.Slf4j;
import net.atos.springquartzexample.service.SayGoodbyeService;
import net.atos.springquartzexample.service.SayHelloService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DisallowConcurrentExecution
public class SayGoodbyeJob implements Job {

    @Autowired
    private SayGoodbyeService sayGoodbyeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start - quartz job say goodbye");
        sayGoodbyeService.sayGoodbye();
    }
}
