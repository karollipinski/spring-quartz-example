package net.atos.springquartzexample;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

public abstract class BaseQuartzJobConfiguration {

    public abstract CronTriggerFactoryBean jobTrigger(JobDetail jobDetail);

    public abstract JobDetailFactoryBean jobDetails();

    protected JobDetailFactoryBean createJobDetails(Class jabClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jabClass);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    protected CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

}
