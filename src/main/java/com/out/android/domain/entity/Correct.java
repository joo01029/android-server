package com.out.android.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(indexes = {@Index(name = "i_correct", columnList = "user_idx, problem_id")})
public class Correct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date date;

	@ManyToOne
//	@JoinColumn(name = "id")
	private Problem problem;

	@ManyToOne
//	@JoinColumn(name = "idx")
	private User user;
}
