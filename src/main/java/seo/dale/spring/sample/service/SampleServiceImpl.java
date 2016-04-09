package seo.dale.spring.sample.service;

import org.springframework.stereotype.Service;
import seo.dale.spring.sample.domain.Sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public List<Sample> find(int count) {
        List<Sample> list = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            list.add(new Sample(i, "name_" + i, "hahaha", new Date()));
        }
        return list;
    }

}
