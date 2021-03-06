/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package seo.dale.spring.web.client.interceptor;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * 원격 서비스 호출용 요청 인터셉터
 * @author 서대영, SK플래닛
 */
public class LogInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	    logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        LOGGER.debug("=========================== REQUEST INFO ===========================");
        LOGGER.debug("> " + request.getMethod() + " " + request.getURI());
	    HttpHeaders httpheaders = request.getHeaders();
	    for (Map.Entry<String, List<String>> entry : httpheaders.entrySet()) {
		    LOGGER.debug("> " + entry.toString());
	    }
        if (request.getMethod() == HttpMethod.GET) {
            return;
        }
        LOGGER.debug("> \n" + new String(body, "UTF-8"));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        String responseBody = IOUtils.toString(new InputStreamReader(response.getBody(), "UTF-8"));
        LOGGER.debug("=========================== RESPONSE INFO ===========================");
        LOGGER.debug("< " + response.getStatusCode() + " " + response.getStatusText());
	    HttpHeaders httpheaders = response.getHeaders();
	    for (Map.Entry<String, List<String>> entry : httpheaders.entrySet()) {
		    LOGGER.debug("< " + entry.toString());
	    }
        LOGGER.debug("< \n" + responseBody);
    }

}
