package storeservice.configuration;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import storeservice.dao.*;
import storeservice.components.SimpleEmailComponent;
import storeservice.encryption.Encryption;
import storeservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan("storeservice")
@EnableWebMvc
public class SpringBeansConfiguraton implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringBeansConfiguraton(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/storeservice");
        driverManagerDataSource.setUsername("mysql");
        driverManagerDataSource.setPassword("");
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return driverManagerDataSource;
    }

    @Bean
    public ProductDAO getProductDAO(){
        return new ProductDAOImpl(getJdbcTemplate());
    }

    @Bean
    public AllService getProductServiceImpl() {
        return new ProductServiceImpl();
    }

    @Bean
    public AdminDAO getAdminDAO(){
        return new AdminDAOImpl(getJdbcTemplate());
    }

    @Bean
    public AllService getAdminServiceImpl() {
        return new AdminServiceImpl();
    }

    @Bean
    public ClientDAO getClientDAO(){
        return new ClientDAOImpl(getJdbcTemplate());
    }

    @Bean
    public AllService getClientServiceImpl(){return new ClientServiceImpl();}

    @Bean
    public DealDAO getDealDAO(){return new DealDAOImpl(getJdbcTemplate());}

    @Bean
    public AllService getDealServiceImpl(){
        return new DealServiceImpl();
    }

    @Bean
    public InterestDAO getInterestDAO(){return new InterestDAOImpl(getJdbcTemplate());}

    @Bean
    public AllService getInterestServiceImpl(){
        return new InterestServiceImpl();
    }

    @Bean
    public SimpleEmailComponent getSimpleEmailComponent(){return new SimpleEmailComponent();}

    @Bean
    public Encryption getEncryption(){return new Encryption();}
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
