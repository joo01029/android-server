package com.out.android.service.auth;

import com.out.android.domain.request.auth.RegisterDto;

public interface AuthService {
	public void register(RegisterDto registerDto);
}
