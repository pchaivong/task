package com.example.task;

import org.hyperledger.fabric.sdk.NetworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hyperledger.fabric.sdk.HFClient;

import java.io.InputStream;


@SpringBootApplication
public class TaskApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TaskApplication.class);

	@Value("${perf.testname}")
	String testName;

	@Value("${perf.ccp}")
	String ccp;

	@Value("${perf.channel}")
	String channelName;

	@Value("${perf.admin}")
	String admin;

	@Value("${perf.secret}")
	String secret;

	HFClient client;


	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Entry point
		setup();
		try {
			loadProfile();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void setup(){
		logger.info(String.format("Test: %s", testName));
	}

	private void loadProfile() throws Exception {
		logger.info(String.format("Load connection profile: %s", ccp));
		InputStream stream = getClass().getClassLoader().getResourceAsStream(ccp);
		NetworkConfig config = NetworkConfig.fromJsonStream(stream);
		config.getClientOrganization().getPeerNames()
				.forEach(p -> {
					logger.info(String.format("Peer: %s", p));
				});
	}

}

