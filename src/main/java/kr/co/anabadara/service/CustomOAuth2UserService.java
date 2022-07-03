package kr.co.anabadara.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.anabadara.SessionMember;
import kr.co.anabadara.entity.Member;
import kr.co.anabadara.repository.MemberRepository;
import kr.co.anabadara.service.model.OAuthAttributes;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private HttpSession httpSession;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
		//OAuth2 서비스 ID
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		//OAuth2 로그인 키 (PRIMARY KEY)
		String userNameAttributeName = userRequest.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		Member member = saveOrUpdate(attributes);
		httpSession.setAttribute("user", new SessionMember(member));
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}
	private Member saveOrUpdate(OAuthAttributes attributes) {
		Member member = memberRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getImage(), attributes.getMobile()))
				.orElse(attributes.toEntity());
		return memberRepository.save(member);
	}
}
