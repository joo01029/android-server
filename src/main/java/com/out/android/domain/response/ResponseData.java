package com.out.android.domain.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData <t> extends Response{
	private t data;

	public ResponseData(t data){
		super();
		this.data = data;
	}
}
