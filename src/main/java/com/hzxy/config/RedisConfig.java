package com.hzxy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/**
 * 自定义Redis的配置类，进行序列号以及RedisTemplate的设置
 *
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 创建Redis API模版 ：RedisTemplate
     * @param redisConnectionFactory  由SpringBoot自动配置（读取application.yaml中的Spring.data的数据）
     * @return RedisTemplate<Object,Object> 支持任意类型的key：Value
     */
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        //创建RedisThemplate实例{对象}
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        //为RedisTemplate设置连接工厂（指定Redis的host端口、密码、连接池数据）
        template.setConnectionFactory(redisConnectionFactory);
        //创建Jackson的ObjectionMapper<序列化与反序列化>
        ObjectMapper om=new ObjectMapper();
        /**
         * 参数一：指定PropertyAccessor.ALL，包括访问对象的成员变量，getter，setter方法
         * 参数二：JsonAutoDetect.Visibility.ANY 允许序列化private、public等任意修饰字段，默认只序列化public字段
         */
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，类必须是非final修饰，如果该类非final修饰，比如String会包异常
        om.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                ObjectMapper.DefaultTyping.NON_FINAL);
        //使用json格式序列化，Article对象---》Json格式序列化
        Jackson2JsonRedisSerializer<Object> jackson=new Jackson2JsonRedisSerializer<Object>(om,Object.class);
        //设置RedisTemplate模版的序列化方式为JSON
        template.setDefaultSerializer(jackson);
        return template;




    }
    /**
     * 定制Redis的缓冲管理器 RedisCacheManager实现自定义序列号，并设置缓存时效
     * @return RedisCacheManager ,配置Redis缓冲序列化。过期失效等规则
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){

        //创建RedisSerialize(对缓存的对象数据key和Value进行转换)
        RedisSerializer<String> redisSerializer=new StringRedisSerializer();
        //创建RedisThemplate实例{对象}
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        //为RedisTemplate设置连接工厂（指定Redis的host端口、密码、连接池数据）
        template.setConnectionFactory(redisConnectionFactory);
        //创建Jackson的ObjectionMapper<序列化与反序列化>
        ObjectMapper om=new ObjectMapper();
        /**
         * 参数一：指定PropertyAccessor.ALL，包括访问对象的成员变量，getter，setter方法
         * 参数二：JsonAutoDetect.Visibility.ANY 允许序列化private、public等任意修饰字段，默认只序列化public字段
         */
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，类必须是非final修饰，如果该类非final修饰，比如String会包异常
        om.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                ObjectMapper.DefaultTyping.NON_FINAL);
        //使用json格式序列化，Article对象---》Json格式序列化
        Jackson2JsonRedisSerializer<Object> jackson=new Jackson2JsonRedisSerializer<Object>(om,Object.class);
        //定制缓存数据的序列化以及时效
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(7)) //设置缓冲时效为7天
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))  //设置Redis的key
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson)) //设置Redis的Value
                .disableCachingNullValues();  //对空数据不进行缓存
        RedisCacheManager cacheManager=RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();


        return cacheManager;
    }

}
