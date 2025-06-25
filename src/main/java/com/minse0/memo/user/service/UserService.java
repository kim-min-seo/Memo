package com.minse0.memo.user.service;

import org.springframework.stereotype.Service;

import com.minse0.memo.common.MD5HashingEncoder;
import com.minse0.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	//final : 해당 변수에 값이 저장된 후 수정 불가 (상수)
	private final UserRepository userRepository;


	// 해당 클래스가 생성될 때 Spring 이 생성자를 호출해서 객체를 주입해준다.
	// 다른 생성자 없이 Autowirde를 위한 생성자만 있는 경우 Autowired 어노테이션 생략 가능
	
	//@Autowired
	public UserService( UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	// 사용자 추가 기능
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
//		MD5HashingEncoder encoder = new MD5HashingEncoder();
//		String hashingPassword = encoder.encode(password);
		
		String hashingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, hashingPassword, name, email);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
}
