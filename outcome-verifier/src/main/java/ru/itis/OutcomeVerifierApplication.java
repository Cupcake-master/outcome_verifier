package ru.itis;

import ru.itis.config.SenderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Properties;

@SpringBootApplication
public class OutcomeVerifierApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SenderConfig sender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.host", "smtp.yandex.ru");
        properties.put("mail.port", "465");
        return new SenderConfig("bilalov.bulat.2023@yandex.com",
                "daptfevmuzrwbqlm", properties);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(OutcomeVerifierApplication.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
