package org.jsp.cda.entity;

import org.jsp.cda.utility.UserRole;
import org.jsp.cda.utility.UserStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false, unique = false)
	private String password;
	@Value("${name}")          //automatically takes name from application properties ...and to write value annotation class should be component class
	private String name;
	@Column(unique = true)
	@Value("${email}")
	private String email;
	@Column(unique = true, length = 10)
	@Value("${phone}")
	private long phone;
	private int otp;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

}
