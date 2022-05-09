package com.out.android.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column(unique = true, nullable = false, length = 20)
	private String id;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer score;
}
