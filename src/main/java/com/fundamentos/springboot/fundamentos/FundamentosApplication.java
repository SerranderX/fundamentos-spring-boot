package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.BeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log logger = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private BeanWithDependency beanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  BeanWithDependency beanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository
	) {
		this.myBean = myBean;
		this.componentDependency = componentDependency;
		this.beanWithDependency = beanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveUsersInDB();
	}

	public void saveUsersInDB() {
		List<UserPojo> listaUsuarios = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			UserPojo user = new UserPojo("Jhon-"+i, "asd", i );
			listaUsuarios.add(user);
		}
		List<UserPojo> savedUsers = userRepository.saveAll(listaUsuarios);

		listaUsuarios.forEach( item -> {
			boolean saved = savedUsers.stream().anyMatch( elem -> elem.getEmail().equals(item.getEmail()) );
			if(!saved) {
				try {
					throw new Exception("Elemento "+item.getAge()+" no se guardo correctamente");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		// JPQL example
		Optional<UserPojo> response = Optional.ofNullable(userRepository.findUserByEmail("Jhon-9").orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
		if(response.isPresent()) logger.info("Usuario encontrado: " + response.get().getEmail());

		userRepository.findAndSort("Jhon", Sort.by("id").descending()).stream().forEach(user -> logger.info("usuario: " + user));

		userRepository.findByEmailAndAge("Jhon-3", 3).stream().forEach(user -> logger.info("usuario findByNameAndPassword: " + user));
	}

	public void ejemplo() {
		this.componentDependency.saludar();
		this.myBean.print();
		this.beanWithDependency.saludo();
		System.out.println(this.myBeanWithProperties.getFullName());
		System.out.println(this.userPojo.getEmail()+" - "+this.userPojo.getPassword()+" - "+this.userPojo.getAge());
		this.logger.error("Ha ocurrido un error");
	}
}
