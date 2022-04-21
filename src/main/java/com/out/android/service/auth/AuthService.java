package com.out.android.service.auth;

import com.out.android.domain.request.auth.LoginDto;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.auth.LoginResponse;

public interface AuthService {
	public void register(RegisterDto registerDto);
	LoginResponse login(LoginDto loginDto);
}
