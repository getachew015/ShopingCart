package org.dagim.shoppingCart.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan()
@EnableTransactionManagement
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {
	
	
	@Autowired
	private Environment env;
	public ApplicationContextConfig() {
		// TODO Auto-generated constructor stub
	}
	
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        // Load property in message/validator.properties
        rb.setBasenames(new String[] { "messages/validator" });
        return rb;
    }
    @Bean
    public InternalResourceViewResolver getViewResolver(){
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setPrefix("/WEB-INF/pages/");
    	viewResolver.setSuffix(".jsp");
    	return viewResolver;
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multiPartResolver(){
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver ();
    	//set MaxSize ...
    	//multipartResolver.setMaxUploadSize(...);
    	return multipartResolver;
    }
    
    @Bean(name = "dataSource")
    public DataSource getdataSource(){
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	// See: ds-hibernate-cfg.properties
    	dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
    	dataSource.setUrl(env.getProperty("ds.url"));
    	dataSource.setUsername(env.getProperty("ds.username"));
    	dataSource.setPassword(env.getProperty("ds.password"));
    	System.out.println(" ## getdatasource ... "+dataSource);
    	return dataSource;
    }
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
    	Properties properties = new Properties();
    	// See: ds-hibernate-cfg.properties
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean ();
        factoryBean.setPackagesToScan(new String [] {"org.dagim.shoppingCart.entity"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        System.out.println(" ## getSessionFactory ... " +sf);
        return sf;   	
    
    }
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    	HibernateTransactionManager transactionManager = new HibernateTransactionManager (sessionFactory);
    	return transactionManager;
    }
    @Bean(name = "accountDAO")
    public AccountDAO getApplicantDAO(){
    	return new AccountDAOImpl();
    }
    @Bean(name = "orderDAO")
    public Order getOrderDAO() {
        return new OrderDAOImpl();
    }
     
    @Bean(name = "accountDAO")
    public AccountDAO getAccountDAO()  {
        return new AccountDAOImpl();
    }

    
}
