package com.out.android.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private HttpStatus status;
	private String message;

	public CustomException(){
		this.message = "success";
		this.status = HttpStatus.OK;
	}


}
