package seo.dale.spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExampleCacheService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExampleCacheService.class);

    public static final String CACHE_NAME = "examples";

    private final AtomicInteger counter = new AtomicInteger(1);

    private Map<Integer, String> dummyMap; // 간결한 구현을 위해서 예제에서는 Map 객체를 사용했지만 실제 구현에서는 DB를 사용할 경우가 많을 것임

    public ExampleCacheService() {
        dummyMap = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            dummyMap.put(counter.getAndIncrement(), "dummy" + i);
        }
    }

    // 캐시 조회를 원하는 메서드에는 @Cacheable 어노테이션 사용
    @Cacheable(CACHE_NAME)
    public String find(int key) {
        LOGGER.debug("# Didn't hit the cache.");
        simulateSlowService();
        return dummyMap.get(key);
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public void create(String dummy) {
        dummyMap.put(counter.getAndIncrement(), dummy);
    }

    // 캐시 초기화를 원하는 메서드에는 @CacheEvict 어노테이션 사용
    @CacheEvict(CACHE_NAME)
    public void remove(int key) {
        dummyMap.remove(key);
    }

    @CacheEvict(CACHE_NAME)
    public void update(int key, String dummy) {
        dummyMap.put(key, dummy);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void removeAll() {
    }

}
