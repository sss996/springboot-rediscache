package com.chenyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

/**
 *
 * @author chenyun
 * @version 1.0.0
 * @blog https://blog.csdn.net/sss996
 *
 */
@SpringBootApplication
@EnableCaching // 加入@EnableCaching 注解，用来驱动spring 缓存机制工作
public class Application {

	// 注入RedisTemplate
	@Autowired
	private RedisTemplate redisTemplate;

	//  定义自定义后初始化方法
	@PostConstruct
	public void init(){
		initRedisTemplate();
	}

	/**
	 * redisTemplate 会默认使用JdkSerializationRedisSerializer进行序列化值，
	 * 这样便能购存储到redis服务器中。这样redis存入的是一个经过序列化的特殊字符串，
	 * 有时候对于我们跟踪并不是很友好，如果我们在redis只是使用字符串，那么使用自动生成
	 * stringRedisTemplate即可，这样就只支持字符串，并不支持java对象存储。
	 *
	 * */
	private void initRedisTemplate(){
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
	}


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
