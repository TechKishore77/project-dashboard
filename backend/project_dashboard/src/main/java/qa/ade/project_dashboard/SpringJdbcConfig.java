package qa.ade.project_dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import qa.ade.project_dashboard.exceptions.MySqlExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class SpringJdbcConfig {

    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        MySqlExceptionTranslator mySqlExceptionTranslator = new MySqlExceptionTranslator();
        mySqlExceptionTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(mySqlExceptionTranslator);
        return jdbcTemplate;
    }

    @Profile("dev")
    @Bean
    public DataSource mysqlDataSourceDev() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(env.getRequiredProperty("jdbc.driverClassName")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource mysqlDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(env.getRequiredProperty("jdbc.driverClassName")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
}
