package net.guides.springboot2.springboot2jpacrudexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
//@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "sex", length = 40, nullable = false)
	private String sex;

	@Column(name = "age", columnDefinition = "int(11)",	nullable = false)
	private Integer age;

	@Column(name = "name", length = 40, nullable = false)
	private String name;

	@Column(name = "role", length = 40, nullable = false)
	private String role;

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "email_address", unique = true)
	public EmailAddress emailAddress;

	
}
