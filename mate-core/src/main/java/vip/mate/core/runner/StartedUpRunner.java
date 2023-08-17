package vip.mate.core.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 自定义启动类打印日志
 *
 * @author pangu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {
	private final ConfigurableApplicationContext context;
	private final Environment environment;

	private static void printSystemUpBanner(Environment environment) throws UnknownHostException {
		log.info("\n----------------------------------------------------------\n\t" +
						"应用 '{}' 运行成功! 访问连接:\n\t" +
						"Swagger文档: \t\thttp://{}:{}/doc.html\n" +
						"----------------------------------------------------------",
				environment.getProperty("spring.application.name"),
				InetAddress.getLocalHost().getHostAddress(),
				environment.getProperty("server.port"),
				"127.0.0.1",
				environment.getProperty("server.port"));
	}

	@Override
	public void run(ApplicationArguments args) throws UnknownHostException {
		if (context.isActive()) {
			printSystemUpBanner(environment);
		}
	}
}
