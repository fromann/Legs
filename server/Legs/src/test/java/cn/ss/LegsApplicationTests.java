package cn.ss;

import cn.ss.mapper.LoginMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class LegsApplicationTests {
	@Autowired
	private LoginMapper userMapper;


	@Test
	void UserLogin() {
		System.out.println(userMapper.userLogin("admin", "admin"));
	}

	@Test
	void TestGenjwt() {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("username", "admin");
		claims.put("password", "admin");

		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "ssssss")
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.compact();

		System.out.println(jwt);
		System.out.println(jwt);
		System.out.println(jwt);
		System.out.println(jwt);
		System.out.println(jwt);
	}

}
