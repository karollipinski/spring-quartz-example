package net.atos.springquartzexample.job.goodbye;

import net.atos.springquartzexample.BaseQuartzJobConfiguration;
import net.atos.springquartzexample.job.hello.SayHelloJob;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class SayGoodbyeJobConfiguration extends BaseQuartzJobConfiguration {

    @Value("${quartz.job.sayGoodbye.trigger.cronExpression}")
    // @Value("0/2 * * ? * *")
    private String cromExpression;

    @Override
    @Bean("sayGoodbyeTrigger")
    public CronTriggerFactoryBean jobTrigger(@Qualifier("sayGoodbyeJobDetail") JobDetail jobDetail) {
        return createCronTrigger(jobDetail, cromExpression);
    }

    @Override
    @Bean("sayGoodbyeJobDetail")
    public JobDetailFactoryBean jobDetails() {
        return createJobDetails(SayGoodbyeJob.class);
    }
}
