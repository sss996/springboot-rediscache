package com.chenyun.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.data.redis.connection.RedisConnectionFactory;
        import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
        import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.data.redis.serializer.RedisSerializer;
        import org.springframework.web.bind.annotation.RequestMapping;
        import redis.clients.jedis.JedisPoolConfig;

        import java.util.Map;

@Configuration
public class RedisConfig {

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name="RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){

        if(this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // 最大空闲数
        poolConfig.setMaxIdle(30);
        // 最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        // 创建Jedis链接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        // 获取单机Redis配置
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();

        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);

        //设置密码，如果redis没有密码，一定要注释掉，否则编译报错
//        connectionFactory.setPassword("123456");

        this.connectionFactory = connectionFactory;

        return  connectionFactory;

    }

    @Bean(name="redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        // RedisTemplate 会自动初始化StringRedisSerializer, 所以这里就直接获取了
        RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        // 设置字符串序列化器，这样spring 会把redis 的key当作字符串处理
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());

        return redisTemplate;
    }

}
