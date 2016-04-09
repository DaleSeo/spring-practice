package seo.dale.spring.sample.service;

import seo.dale.spring.sample.domain.Sample;

import java.util.List;

public interface SampleService {

    List<Sample> find(int count);
}
