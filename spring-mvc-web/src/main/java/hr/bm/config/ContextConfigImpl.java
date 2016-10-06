package hr.bm.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;

import hr.bm.config.aspect.AspectConfig;
import hr.bm.config.cache.EhCacheConfig;
import hr.bm.config.datasource.H2Config;
import hr.bm.config.fileupload.FileUploadConfig;
import hr.bm.config.mail.MailConfig;
import hr.bm.config.messages.MessagesConfig;
import hr.bm.config.webservice.JaxEndpointConfig;

@Configuration
@ComponentScan(basePackages = { "hr.bm.context", "hr.bm.utils" }, excludeFilters = @ComponentScan.Filter(Controller.class))
@ImportResource({ "classpath:/config/xmlbeans/bean-context.xml" })
@Import({
	EhCacheConfig.class, H2Config.class, JaxEndpointConfig.class,
	MessagesConfig.class, AspectConfig.class, FileUploadConfig.class,
	MailConfig.class
})
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
public class ContextConfigImpl extends ContextConfig implements AsyncConfigurer, SchedulingConfigurer {

	public void configureTasks(ScheduledTaskRegistrar registrar) {
		TaskScheduler scheduler = this.taskScheduler();
		registrar.setTaskScheduler(scheduler);
	}

	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		return executor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("task-");
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}
}
