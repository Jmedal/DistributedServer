package com.example.worknet.config.redis;


import org.springframework.context.annotation.Configuration;

/**
 * @Author: YunJieJiang
 * @Date: Created in 21:44 2019/3/6 0006
 * 注：未完成（没下redis数据库等等）
 */

@Configuration
public class RedisConfig {
//    @Bean(name = "springSessionDefaultRedisSerializer")
//    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
//    // 以下两种redisTemplate自由根据场景选择
//   /* @Bean
//    public RedisTemplate<Object, Object> template(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//        template.setValueSerializer(serializer);
//        //使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }*/
//    @Bean
//    public RedisTemplate<Object, Object> template(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//
//        // 使用Jackson2JsonRedisSerialize 替换默认序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        objectMapper.registerModule(new ParameterNamesModule())
//                .registerModule(new Jdk8Module())
//                .registerModule(new JavaTimeModule());
////        objectMapper.set
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        // 设置value的序列化规则和 key的序列化规则
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setEnableDefaultSerializer(true);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//    /* @Bean
//     public RedisTemplate<String, Object> template(
//             RedisConnectionFactory connectionFactory) {
//         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//         redisTemplate.setConnectionFactory(connectionFactory);
//         redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//         StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//         redisTemplate.setKeySerializer(stringRedisSerializer);
//         redisTemplate.setHashKeySerializer(stringRedisSerializer);
//         return redisTemplate;
//     }*/
//    @Bean
//    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(factory);
//        return stringRedisTemplate;
//    }
}
