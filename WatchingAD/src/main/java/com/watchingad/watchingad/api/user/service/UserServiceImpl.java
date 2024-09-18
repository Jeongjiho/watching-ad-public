package com.watchingad.watchingad.api.user.service;


import java.util.Optional;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.watchingad.watchingad.api.user.entity.LoginHistoryEntity;
import com.watchingad.watchingad.api.user.entity.UserEntity;
import com.watchingad.watchingad.api.user.repository.LoginHistoryRepository;
import com.watchingad.watchingad.api.user.repository.UserRepository;
import com.watchingad.watchingad.api.user.vo.TokenVO;
import com.watchingad.watchingad.api.user.vo.UserTokenVO;
import com.watchingad.watchingad.common.CommonUtils;
import com.watchingad.watchingad.exception.exception.NotFoundTokenException;
import com.watchingad.watchingad.exception.exception.NotFoundUserException;
import com.watchingad.watchingad.exception.exception.UserAlreadyJoinException;
import com.watchingad.watchingad.modules.JwtModule;
import com.watchingad.watchingad.specification.JpaSpecification;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 Service Implements
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private final PasswordEncoder passwordEncoder;
	private final StringEncryptor stringEncryptor;
	private final UserRepository userRepository;
	private final LoginHistoryRepository loginHistoryRepository;
	private final JwtModule jwtModule;
	
	@Override
	@Transactional
	public boolean save(@NonNull UserEntity user) {
		
		checkExistId(user.getId());
		
		String encodePassword = passwordEncoder.encode(user.getPassword());
		String phone = stringEncryptor.encrypt(user.getPhone());
		
		user.setPassword(encodePassword);
		user.setPhone(phone);
		
		userRepository.save(user);
		
		return true;
		
	}

	@Override
	@Transactional(readOnly = true)
	public void checkExistId(@NonNull String id) {
		Specification<UserEntity> spec = Specification.where(JpaSpecification.equalObject("id", id));
		userRepository.findOne(spec).ifPresent(userEntity -> {throw new UserAlreadyJoinException();});
	}
	
	@Override
	@Transactional
	public TokenVO login(String id, String password, @NonNull String dUuid, @NonNull String dModel, @NonNull String sessionId) throws JsonProcessingException {
		
		Specification<UserEntity> spec = Specification.where(JpaSpecification.equalObject("id", id));
		UserEntity user = userRepository.findOne(spec).orElseGet(UserEntity::new);
		
		if(user.isEmpty())
			throw new NotFoundUserException();
		
		if(!passwordEncoder.matches(password, user.getPassword()))
			throw new NotFoundUserException();
		
		return getRefreshHashWithAccessToken(user, dUuid, dModel);
	}

	@Override
	@Transactional
	public LoginHistoryEntity getRefreshToken(@NonNull int idx) {
		
		if(idx == 0)
			throw new IllegalArgumentException("idx is empty");
		
		Optional<LoginHistoryEntity> loginHistoryEntityOptional = loginHistoryRepository.findById(idx);
		loginHistoryEntityOptional.orElseThrow(NotFoundTokenException::new);

		return loginHistoryEntityOptional.orElseGet(LoginHistoryEntity::new);
	}

	@Override
	@Transactional
	public TokenVO updateAccessTokenWithRefreshToken(@NonNull int idx, @NonNull String dUuid, @NonNull String dModel) throws JsonProcessingException {
		
		if(idx == 0)
			throw new IllegalArgumentException("idx is empty");
		
		return getRefreshHashWithAccessToken(getUser(idx), dUuid, dModel);
	}
	
	@Override
	@Transactional
	public TokenVO updateAccessToken(@NonNull int idx) throws JsonProcessingException {
		
		if(idx == 0)
			throw new IllegalArgumentException("idx is empty");
		
		String accessToken = getAccessToken(getUser(idx));
		
		return TokenVO.builder()
				.accessToken(accessToken)
				.build();
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserEntity getUser(int idx) {
		
		if(idx == 0)
			throw new IllegalArgumentException("idx is empty");
		
		Optional<UserEntity> userEntityOptional = userRepository.findById(idx);
		userEntityOptional.orElseThrow(NotFoundUserException::new);
		return userEntityOptional.orElseGet(UserEntity::new);
	}
	
	private TokenVO getRefreshHashWithAccessToken(@NonNull UserEntity user, @NonNull String dUuid, @NonNull String dModel) throws JsonProcessingException {
		
		String accessToken = getAccessToken(user);
		String refreshToken = jwtModule.createRefreshToken();
		
		LoginHistoryEntity loginHistoryEntity = LoginHistoryEntity.builder()
		.userIdx(user.getIdx())
		.token(refreshToken)
		.dUuid(dUuid)
		.dModel(dModel)
		.build();
		
		loginHistoryRepository.updateDeleteYByUserIdx(user.getIdx());
		loginHistoryRepository.save(loginHistoryEntity);
		
		Integer idx = loginHistoryEntity.getIdx();
		String refreshHash = stringEncryptor.encrypt(String.valueOf(idx));
		
		return TokenVO.builder()
				.accessToken(accessToken)
				.refreshHash(refreshHash)
				.build();
		
	}
	
	private String getAccessToken(@NonNull UserEntity user) throws JsonProcessingException {
		if(user == null || user.isEmpty())
			throw new IllegalArgumentException("user is null or empty");
		
		UserTokenVO userTokenVO = CommonUtils.userEntityToUserTokenVO(user);
		
        String userAsString = CommonUtils.objectMapper.writeValueAsString(userTokenVO);
		return jwtModule.createAccessToken(userAsString);
	}
	
}
