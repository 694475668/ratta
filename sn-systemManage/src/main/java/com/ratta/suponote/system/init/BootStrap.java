package com.ratta.suponote.system.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.util.concurrent.AbstractIdleService;

/**
 * @author page 持久层启动 2018-10-31
 */
public class BootStrap extends AbstractIdleService {
	private static final Logger log = LoggerFactory.getLogger(BootStrap.class);

	private ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		BootStrap bootstrap = new BootStrap();
		bootstrap.startAsync();
		try {
			Object lock = new Object();
			synchronized (lock) {
				while (true) {
					lock.wait();
				}
			}
		} catch (InterruptedException ex) {
			log.error("ignore interruption");
		}
	}

	/**
	 * Start the service.
	 */
	@Override
	protected void startUp() throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-base.xml" });
		context.start();
		context.registerShutdownHook();
		log.info("service started successfully");
	}

	/**
	 * Stop the service.
	 */
	@Override
	protected void shutDown() throws Exception {
		context.stop();
		log.info("service stopped successfully");
	}
}
