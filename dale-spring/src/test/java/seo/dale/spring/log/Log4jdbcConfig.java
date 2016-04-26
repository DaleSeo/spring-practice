package seo.dale.spring.log;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class Log4jdbcConfig {

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public Log4jdbcProxyDataSource log4jdbcProxyDataSource() {
		EmbeddedDatabase embeddedDatabase = new  EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("classpath:sql/examples.sql")
				.build();

		Log4JdbcCustomFormatter log4JdbcCustomFormatter = new Log4JdbcCustomFormatter();
		log4JdbcCustomFormatter.setLoggingType(LoggingType.MULTI_LINE);

		Log4jdbcProxyDataSource log4jdbcProxyDataSource = new Log4jdbcProxyDataSource(embeddedDatabase);
		log4jdbcProxyDataSource.setLogFormatter(log4JdbcCustomFormatter);

		return log4jdbcProxyDataSource;
	}

}
