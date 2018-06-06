package cn.imust.ys.springbootshiro.config;

import cn.imust.ys.springbootshiro.shiro.AuthRealm;
import cn.imust.ys.springbootshiro.shiro.CredentialMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        bean.setLoginUrl("/user/redirtLogin");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/user/unauthorized");

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/** ", "anon");
        filterChainDefinitionMap.put("/images/** ", "anon");
        filterChainDefinitionMap.put("/js/** ", "anon");
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/permission", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        filterChainDefinitionMap.put("/role/findAll", "roles[admin]");
        filterChainDefinitionMap.put("/edit", "perms[edit]");
        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/**", "user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 随后需要修改安全管理器：
     * 配置SecurityManager的管理
     *                 <property name="cacheManager" ref="ehCacheManager"></property>
     *                 <property name="sessionManager" ref="sessionManager"></property>
     * */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm/*,
                                           @Qualifier("ehCacheManager") EhCacheManager ehCacheManager,
                                           @Qualifier("sessionManager") SessionManager sessionManager*/) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 配置你需要使用的Realms
        manager.setRealm(authRealm);
        // 设置缓存
        // manager.setCacheManager(ehCacheManager);
        // manager.setSessionManager(sessionManager);
        return manager;
    }

/*    *//**
     * shiro session 解决方案
     *//*
    // 注入一个 sessionID 生成器
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    *//**
     * 注入会话 DAO
     *//*
    @Bean
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(@Qualifier("sessionIdGenerator") JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        // 设置session缓存的名字
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return enterpriseCacheSessionDAO;
    }

    *//**
     * 定义 Cookie
     *//*
    @Bean
    public SimpleCookie sessionIdCookie() {
        // 在Tomcat运行下默认使用的Cookie的名字为JSESSIONID
        SimpleCookie simpleCookie = new SimpleCookie("JSESSIONID");
        // 保证该系统不会受到跨域的脚本操作攻击
        simpleCookie.setHttpOnly(false);
        // 定义Cookie的过期时间，单位为秒，如果设置为-1标识浏览器关闭就失效
        simpleCookie.setMaxAge(1);
        return simpleCookie;
    }

    *//**
     * 定义会话的管理器：
     *//*
    @Bean
    public DefaultWebSessionManager sessionManager(@Qualifier("enterpriseCacheSessionDAO") EnterpriseCacheSessionDAO enterpriseCacheSessionDAO,
                                                   @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie,
                                                   @Qualifier("quartzSessionValidationScheduler") QuartzSessionValidationScheduler quartzSessionValidationScheduler,
                                                   @Qualifier("ehCacheManager") EhCacheManager ehCacheManager) {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        // 定义全局的 session 会话超时时间 ,此操作会覆盖 web.xml 文件中的超时时间设置
        defaultWebSessionManager.setGlobalSessionTimeout(1000000);
        // 删除所有无效的 session 对象，此时 session 被保存在内存里面
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        // 定义要使用的无效的Session定时调度器
        defaultWebSessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler);
        // 需要让此 session 可以使用该定时调度器进行检测
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        // 定义Session可以进行序列化的工具类
        defaultWebSessionManager.setSessionDAO(enterpriseCacheSessionDAO);
        // 所有的session一定要将id设置到cookie之中，需要提供有cookie的操作模板
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie);
        //　配置缓存
        defaultWebSessionManager.setCacheManager(ehCacheManager);
        return defaultWebSessionManager;
    }

    *//**
     * 增加有一个会话的验证调度器。
     *
     * 配置session的定时验证检测程序类，以让无得session释放
     *                 <!-- 随后还需要定义有一个会话管理的程序的引用 -->
     *                 <property name="sessionManager" ref="sessionManager"></property>
     * *//*
    @Bean
    public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
        QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
        // 设置session的失效时间
        quartzSessionValidationScheduler.setSessionValidationInterval(100000);
        // 随后还需要定义有一个会话管理的程序的引用
        return quartzSessionValidationScheduler;
    }

    @Bean("ehCacheManager")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }*/

/*    // 密码盐   可以不必实现    因为一般密码可以自己定义自己的密码加密规则
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }*/

}
