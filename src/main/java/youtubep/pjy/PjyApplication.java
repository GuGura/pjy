package youtubep.pjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import youtubep.pjy.controller.FileUploadController;

import java.io.File;

@SpringBootApplication
public class PjyApplication {

	public static void main(String[] args) {
		ApplicationContext application = SpringApplication.run(PjyApplication.class, args);
		for(String str : application.getBeanDefinitionNames()){
			System.out.println(str);
		}
	}

}
