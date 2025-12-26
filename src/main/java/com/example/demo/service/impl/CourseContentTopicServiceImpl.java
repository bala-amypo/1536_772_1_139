package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository topicRepo;
    private final CourseRepository courseRepo;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository topicRepo,
                                         CourseRepository courseRepo) {
        this.topicRepo = topicRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {

        if (topic.getTopicName() == null || topic.getTopicName().isEmpty()) {
            throw new RuntimeException("Topic name");
        }

        if (topic.getWeightPercentage() < 0 || topic.getWeightPercentage() > 100) {
            throw new RuntimeException("0-100");
        }

        Course course = courseRepo.findById(topic.getCourse().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        topic.setCourse(course);
        return topicRepo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic updated) {
        CourseContentTopic topic = getTopicById(id);
        topic.setTopicName(updated.getTopicName());
        topic.setWeightPercentage(updated.getWeightPercentage());
        return topicRepo.save(topic);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return topicRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourse(Long courseId) {
        return topicRepo.findByCourseId(courseId);
    }
}
