package ru.drv.configs;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.drv.models.Auto;
import ru.drv.models.User;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.drv")
public class SpringConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/webjars/")
                .addResourceLocations("/resources/");
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.addAnnotatedClass(User.class).addAnnotatedClass(Auto.class);
        builder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/usersdao");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("qwerty123");
        return driverManagerDataSource;
    }
}
