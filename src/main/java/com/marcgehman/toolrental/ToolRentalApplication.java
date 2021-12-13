package com.marcgehman.toolrental;
import com.marcgehman.toolrental.tool.Tool;
import com.marcgehman.toolrental.tool.ToolMapper;
import com.marcgehman.toolrental.tool.ToolResource;
import com.marcgehman.toolrental.tool.ToolType;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@MappedTypes(Tool.class)
@MapperScan("com.marcgehman.toolrental.tool")
@MapperScan("com.marcgehmna.toolrental.rental")
@ComponentScan
@SpringBootApplication(scanBasePackages = {
	"com.marcgehman.toolrental.tool",
	"com.marcgehman.toolrental.rental",
	"com.marcgehman.toolrental.checkout"
})
public class ToolRentalApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ToolMapper toolMapper;

	public static void main(String[] args) {

			SpringApplication app = new SpringApplication(ToolRentalApplication.class);
			app.setRegisterShutdownHook(false);
			app.run(ToolRentalApplication.class);
	}

}
