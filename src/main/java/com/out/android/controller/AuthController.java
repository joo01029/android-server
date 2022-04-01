package com.out.android.controller;

import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.Response;
import com.out.android.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("register")
	public Response register(@Valid @RequestBody RegisterDto registerDto){
		authService.register(registerDto);
		return new Response();
	}
}
