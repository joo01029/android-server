package com.out.android.domain.response.problem;

import com.out.android.domain.response.user.SimpleUserData;
import lombok.*;

@Getter
@Setter
public class GetProblemsResponse {
	Long id;
	String content;
	SimpleUserData user;
}
