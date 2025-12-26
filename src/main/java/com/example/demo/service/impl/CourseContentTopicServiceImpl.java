public class CourseContentTopicServiceImpl {

    private final CourseContentTopicRepository topicRepository;
    private final CourseRepository courseRepository;

    public CourseContentTopicServiceImpl(
            CourseContentTopicRepository topicRepository,
            CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
    }
}
