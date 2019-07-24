package com.varun.notificationsolution;

import com.varun.notificationsolution.service.Workflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class NotificationsolutionApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(NotificationsolutionApplication.class, args);
    Workflow workflow = (Workflow) context.getBean("workflow");
    workflow.execute();
  }

}
