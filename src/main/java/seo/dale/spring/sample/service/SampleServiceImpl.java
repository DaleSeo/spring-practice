package seo.dale.spring.sample.service;

import org.springframework.stereotype.Service;
import seo.dale.spring.sample.domain.Sample;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public List<Sample> find(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sample findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sample save(Sample sample) {
        throw new UnsupportedOperationException();
    }

}
