public class CourseServiceImpl {

    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UniversityRepository universityRepository) {
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
    }
}
