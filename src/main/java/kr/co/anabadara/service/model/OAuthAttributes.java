package kr.co.anabadara.service.model;

import java.util.Map;

import kr.co.anabadara.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String image;
	private String mobile;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String image, String mobile) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.image = image;
		this.mobile = mobile;
	}

	public OAuthAttributes() {
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		switch (registrationId) {
		case "naver":
			return ofNaver(userNameAttributeName, attributes);
		case "kakao":
			return ofKakao(userNameAttributeName, attributes);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");
		return OAuthAttributes.builder().name((String) response.get("name")).email((String) response.get("email"))
				.image((String) response.get("profile_image"))
				.mobile((String) response.get("mobile")).attributes(attributes)
				.nameAttributeKey(userNameAttributeName).build();
	}

	@SuppressWarnings("unchecked")
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

		return OAuthAttributes.builder().name((String) kakaoProfile.get("nickname"))
				.email((String) kakaoAccount.get("email")).image((String) kakaoProfile.get("profile_image_url")).mobile("000-0000-0000")
				.attributes(attributes).nameAttributeKey(userNameAttributeName).build();
	}

	public Member toEntity() {
		return new Member(name, email, image, mobile);
	}

}
