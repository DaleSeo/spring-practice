package seo.dale.spring.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import seo.dale.spring.CacheConfig;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExampleCacheServiceTest.TestConfiguration.class, CacheConfig.class})
@ActiveProfiles("prod")
public class ExampleCacheServiceTest {

    @Autowired
    ExampleCacheService svc;

    @Test
    public void testFind() {
        // 첫번째 시도 : 메서드를 호출하여 반환값을 가져옴 (# Didn't hit the cache. 로그 출력)
        assertEquals("dummy1", svc.find(1));
        // 두번째 시도 : 메서드르 호출하지 않고 캐시로부터 반환값 가져옴
        assertEquals("dummy1", svc.find(1));
        // 하지만 다른 키로 조회하면 다시 메서드를 호출함
        assertEquals("dummy2", svc.find(2));
        // 이 키로도 두번째 시도이므로 캐시에서 가져옴
        assertEquals("dummy2", svc.find(2));

        // 결과적으로 # Didn't hit the cache. 로그가 2줄 찍힘
    }

    @Test
    public void testRemoveAll() {
        // 3, 4에 대한 결과값 캐시에 로딩
        assertEquals("dummy3", svc.find(3));
        assertEquals("dummy4", svc.find(4));

        // 모든 키에 대해서 캐시를 초기화
        svc.removeAll();

        // 따라서 3, 4에 대한 결과값을 모두 메소드를 호출해서 가져옴
        assertEquals("dummy3", svc.find(3));
        assertEquals("dummy4", svc.find(4));

        // 결과적으로 # Didn't hit the cache. 로그가 4줄 찍힘
    }

    @Test
    public void testRemove() {
        // 5, 6에 대한 결과값 캐시에 로딩
        assertEquals("dummy5", svc.find(5));
        assertEquals("dummy6", svc.find(6));

        // remove 메서드에 @LocalCacheEvict 어노테이션이 적용되어 있기 때문에 5에 대한 결과값은 캐시에서 초기화 됨
        svc.remove(5);
        // 따라서 다음 조회 때 캐시를 타지 않음 (만약에 remove 메서드에 @LocalCacheEvict 어노테이션이 없었다면 캐시를 타서 테스트가 실패했을 것임)
        assertNull(svc.find(5));

        // 6에 대한 결과값은 여전히 캐시에서 가져옴
        assertEquals("dummy6", svc.find(6));
    }

    @Configuration
    @EnableCaching
    @ComponentScan("seo.dale.spring.cache")
    public static class TestConfiguration {
    }

}