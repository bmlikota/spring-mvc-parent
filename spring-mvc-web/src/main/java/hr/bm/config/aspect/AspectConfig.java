package hr.bm.config.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan(value = { "hr.bm.aspect" })
public class AspectConfig {

}
