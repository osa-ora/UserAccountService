package osa.ora;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import osa.ora.UserAccountService.UserAccountsApplication;

@SpringBootTest(classes = UserAccountsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountsApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/accounts/all",
		HttpMethod.GET, entity, String.class);
		System.out.println("Test="+getRootUrl() + "/api/v1/accounts/all");
		System.out.println("Respnse Test="+response.getBody());
		Assert.notNull(response.getBody());
	}
	@Test
	public void testHealthCheck() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/actuator/health",
		HttpMethod.GET, entity, String.class);
		System.out.println("Test="+getRootUrl() + "/actuator/health");
		System.out.println("Respnse Test="+response.getBody());
		Assert.notNull(response.getBody());
	}
}