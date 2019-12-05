package net.atos.springquartzexample.job.hello;

import lombok.extern.slf4j.Slf4j;
import net.atos.springquartzexample.service.SayHelloService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SayHelloJob implements Job {

    @Autowired
    private SayHelloService sayHelloService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start - quartz job say hello");
        sayHelloService.sayHello();
    }
}
