package net.guides.springboot2.springboot2jpacrudexample.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_address")
public class EmailAddress {

    @Id
    @Column(name = "email_address", length = 40)
    private String emailAddress;

	@Column(name = "email_name",length = 40)
	private String emailName;

	@Column(name = "is_enable", length = 40)
	private Boolean isEnable;

}
