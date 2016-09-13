package hr.bm.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hr.bm.api.MyAsyncClass;

@Component
public class MyAsyncClassImpl implements MyAsyncClass {

	final static Logger logger = LogManager.getLogger(MyAsyncClassImpl.class);

	/** 
	 * This method starts executing 15 seconds after 
	 * Spring starts and executes again and again with 15 
	 * seconds between the end of one invocation and the start of the next.
	 */
	@Async
	@Scheduled(fixedDelay = 5000L, initialDelay = 5000L)
	public void logAsyncMsg() {
		logger.info("Message from async and scheduled method!");
	}

}
