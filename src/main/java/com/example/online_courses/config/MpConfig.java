package com.example.online_courses.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.online_courses.mapper")
public class MpConfig {
    // optimistic lock
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // optimistic lock plug-in
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // pagination plus-in
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        
        return interceptor;
    }
}
