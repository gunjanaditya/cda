package org.jsp.cda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
public class Student {
	@Id
	private int id;
	@OneToOne
	@MapsId //which maps id from user entity to student entity so here no need to genrate id ....id will come from user only
	private User user;
	private String photo; //photo url
    private String year; //first_year,second_year,third_year,forth_year
	@ManyToOne
	private Department department_id;
}
