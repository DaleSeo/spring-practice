package seo.dale.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    @Profile({"default", "local"})
    public CacheManager concurrentMapCacheManager() {
        log.debug("concurrentMapCacheManager");
        return new ConcurrentMapCacheManager("examples");
    }

    @Bean
    @Profile("dev")
    public CacheManager simpleCacheManager(){
        log.debug("simpleCacheManager");
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(concurrentMapCacheFactoryBean().getObject());
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    @Profile("dev")
    public ConcurrentMapCacheFactoryBean concurrentMapCacheFactoryBean(){
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("examples");
        return cacheFactoryBean;
    }

    @Bean
    @Profile("prod")
    public CacheManager ehCacheCacheManager() {
        log.debug("ehCacheCacheManager");
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }

    @Bean
    @Profile("prod")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return cmfb;
    }

}
