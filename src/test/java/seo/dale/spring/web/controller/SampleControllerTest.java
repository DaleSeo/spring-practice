package seo.dale.spring.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import seo.dale.spring.sample.domain.Sample;
import seo.dale.spring.sample.service.SampleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SampleControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SampleController controller;

    @Mock
    private SampleService mockSvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<Sample> expected = createSampleList(5);
        when(mockSvc.find(5)).thenReturn(expected);
    }

    @Test
    public void testList() throws Exception {
        int count = 5;

        List<Sample> expected = createSampleList(count);
        when(mockSvc.find(count)).thenReturn(expected);

        mockMvc.perform(get("/samples?count=" + count))
                .andDo(print())
                .andExpect(status().isOk());

        verify(mockSvc).find(count);
    }

    private List<Sample> createSampleList(int count) {
        List<Sample> list = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            list.add(new Sample(i, "name_" + i, "hahaha", new Date()));
        }
        return list;
    }

}