package seo.dale.spring.rest.support;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
		log.debug("###### LoggingRequestInterceptor Start #####");
		traceRequest(httpRequest, bytes);
		ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
		traceResponse(response);
		log.debug("##### LoggingRequestInterceptor End #####");
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		log.debug("=========================== Tracing request to SAC ================================================");
		log.debug("URI : " + request.getURI());
		log.debug("Method : " + request.getMethod());
		log.debug("Headers : " + request.getHeaders());
		if (request.getMethod() == HttpMethod.GET) {
			return;
		}
		log.debug("Request Body : " + new String(body, "UTF-8"));
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		String responseBody = IOUtils.toString(new InputStreamReader(response.getBody(), "UTF-8"));
		log.debug("=========================== Tracing response from SAC ================================================");
		log.debug("Status code: " + response.getStatusCode());
		log.debug("Status text: " + response.getStatusText());
		log.debug("Headers : " + response.getHeaders());
		log.debug("Response Body : " + responseBody);
	}

}
