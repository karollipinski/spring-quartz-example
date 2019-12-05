package net.atos.springquartzexample.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class QuartzConfig {

    private static final String SCHEDULER_NAME = "AtosScheduler";

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource,
                                                     JobFactory jobFactory,
                                                     Trigger[] triggers) {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // nadpisywanie ustawień wywołania trigerów na podstawie properties
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setSchedulerName(SCHEDULER_NAME);
        factoryBean.setDataSource(dataSource);
        factoryBean.setJobFactory(jobFactory);

        factoryBean.setQuartzProperties(quartzProperties());
        log.info("Registering triggers in Quartz Scheduler: {}",
                 Stream.of(triggers)
                       .map(trigger -> ((AbstractTrigger) trigger).getName())
                       .collect(Collectors.toList()));
        factoryBean.setTriggers(triggers);
        return  factoryBean;

    }

    @Bean
    public Properties quartzProperties() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("quartz.yml"));
        yamlPropertiesFactoryBean.afterPropertiesSet();
        return yamlPropertiesFactoryBean.getObject();

    }

}
