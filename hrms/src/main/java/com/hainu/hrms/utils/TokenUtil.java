package com.hainu.hrms.utils;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hainu.hrms.model.Admin;

@Component
public class TokenUtil {
	private static final Logger log = Logger.getLogger(TokenUtil.class);
	private String secret="secret";
	public String createToken(Admin admin) {
		String token = null;
		try {
			//私钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(secret);
			//过期时间   如果设为new Date(System.currentTimeMillis())即立刻过期则下面的token校验不能通过
			Date date=new Date(System.currentTimeMillis()+24*60*60*1000);
			token = JWT.create()
					.withClaim("username", admin.getUsername())
					.withClaim("password", admin.getPassword())
					.withClaim("role_name", admin.getRole_name())
					.withExpiresAt(date)
				    .sign(algorithm);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.debug("token生成失败");
		}
		return token;
	}
	
	public boolean verifyToken(String token) {
		try {
			//私钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
