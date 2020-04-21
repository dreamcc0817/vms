package com.bonc.common.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: vms
 * @Package: com.cms.common.security.util
 * @Description: Jwt工具类
 * @Author: dreamcc
 * @Date: 2020/4/19 17:05
 * @Version: V1.0
 */
@Slf4j
public class JwtTokenUtil {
	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	@Value("${jwt.tokenHead}")
	private String tokenHead;


	/**
	 * 根据负责生成JWT的token
	 *
	 * @param claims claims
	 * @return token
	 */
	public String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * 根据用户信息生成token
	 *
	 * @param userDetails 用户信息
	 * @return token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	/**
	 * 从token中获取JWT中的负载token中获取JWT中的负载
	 *
	 * @param token token
	 * @return Claims对象
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			log.info("JWT格式验证失败:{}", token);
		}
		return claims;
	}

	/**
	 * 生成token的过期时间
	 *
	 * @return token的过期时间
	 */
	private Date generateExpirationDate() {
		//return new Date(System.currentTimeMillis() + expiration * 1000);
		return new Date(System.currentTimeMillis() +   1000);
	}

	/**
	 * 从token中获取登录用户名
	 */
	public String getUserNameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 验证token是否还有效
	 *
	 * @param token       客户端传入的token
	 * @param userDetails 从数据库中查询出来的用户信息
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	/**
	 * 判断token是否已经失效
	 *
	 * @param token token
	 * @return 判断token是否已经失效
	 */
	private boolean isTokenExpired(String token) {
		Date expiredDate = getExpiredDateFromToken(token);
		return expiredDate.before(new Date());
	}

	/**
	 * 从token中获取过期时间
	 *
	 * @param token token
	 * @return 过期时间
	 */
	private Date getExpiredDateFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}

	/**
	 * 判断token是否可以被刷新
	 *
	 * @param token token
	 * @return 是否可以刷新
	 */
	public boolean canRefresh(String token) {
		return !isTokenExpired(token);
	}

	/**
	 * 刷新token
	 *
	 * @param token token
	 * @return 刷新之后的token值
	 */
	public String refreshToken(String token) {
		Claims claims = getClaimsFromToken(token);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
}
