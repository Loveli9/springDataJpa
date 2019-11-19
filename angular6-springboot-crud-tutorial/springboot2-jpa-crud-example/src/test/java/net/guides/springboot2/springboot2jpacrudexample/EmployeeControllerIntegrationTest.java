package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertNotNull;
import net.guides.springboot2.springboot2jpacrudexample.model.EmailAddress;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmailAddressRepository;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmailAddressRepository emailAddressRepository;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testCreateEmployee() {
		for(int i=1;i <= 30;i++) {
			Employee employee = new Employee();
			employee.setSex("男" + i);
			employee.setAge(i);
			employee.setName("姓名" + i);
			employee.setRole("员工");
			employee.setEmailAddress(new EmailAddress(i+"@qq.com",null,null));
			employeeRepository.save(employee);
		}
	}

	@Test
	public void testCreateEmailAddress() {
		for(int i=1;i <= 30;i++) {
			EmailAddress emailAddress = new EmailAddress(i+"@qq.com","QQ邮箱" + i,true);
			emailAddressRepository.save(emailAddress);
		}
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
		employee.setId(1);
		employee.setRole("PM");
		employee.setName("张三");
		employeeRepository.save(employee);
		//删除
		employeeRepository.delete(employee);
	}

	@Test
	public void testJPA() {

		List<Employee> result = employeeRepository.findByName("姓名8");
		System.out.println(result);



	}
}
