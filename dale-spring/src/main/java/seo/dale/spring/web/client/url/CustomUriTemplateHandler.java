package seo.dale.spring.web.client.url;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Properties;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class CustomUriTemplateHandler implements UriTemplateHandler {

	private String scheme;
	private String host;
	private int port;
	private Object query;

	public CustomUriTemplateHandler() {
		Properties properties = new Properties();
		try {
			properties.load(new ClassPathResource("properties/web.client.properties").getInputStream());
			scheme = properties.getProperty("jsonplaceholder.scheme");
			host = properties.getProperty("jsonplaceholder.host");
			port = Integer.parseInt(properties.getProperty("jsonplaceholder.port"));
		} catch (IOException e) {
		}
	}

	@Override
	public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
		UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder(uriTemplate);
		return uriComponentsBuilder.build().expand(uriVariables).encode().toUri();
	}

	@Override
	public URI expand(String uriTemplate, Object... uriVariableValues) {
		UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder(uriTemplate);
		return uriComponentsBuilder.build().expand(uriVariableValues).encode().toUri();
	}

	protected UriComponentsBuilder initUriComponentsBuilder(String path) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(path);
		builder.scheme(scheme);
		builder.host(host);
		builder.port(port);
		return builder;
	}

}
