package net.atos.springquartzexample.job.hello;

import net.atos.springquartzexample.BaseQuartzJobConfiguration;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class SayHelloJobConfiguration extends BaseQuartzJobConfiguration {

    @Value("${quartz.job.sayHello.trigger.cronExpression}")
    // @Value("0/2 * * ? * *")
    private String cromExpression;

    @Override
    @Bean("sayHelloTrigger")
    public CronTriggerFactoryBean jobTrigger(@Qualifier("satHelloJobDetail") JobDetail jobDetail) {
        return createCronTrigger(jobDetail, cromExpression);
    }

    @Override
    @Bean("satHelloJobDetail")
    public JobDetailFactoryBean jobDetails() {
        return createJobDetails(SayHelloJob.class);
    }
}
