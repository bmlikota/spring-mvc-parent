package hr.bm.context;

import java.util.Date;

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
	 * This method starts executing 5 seconds after 
	 * Spring starts and executes again and again with 5 
	 * seconds between the end of one invocation and the start of the next.
	 */
	@Async
	@Scheduled(fixedDelay = 5000L, initialDelay = 5000L)
	public void logAsyncMsg() {
		Date date = new Date();
		try {
			if (date.getTime() % 2 == 0) {
				Thread.sleep(6000L);
			}
		} catch (InterruptedException ignore) {
			logger.error("Sleep went wrong!");
		}
		logger.info("Message from async and scheduled method!" + date.toString());
	}

}
