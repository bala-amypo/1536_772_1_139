public class TransferEvaluationServiceImpl {

    private final TransferEvaluationResultRepository resultRepository;
    private final CourseRepository courseRepository;
    private final CourseContentTopicRepository topicRepository;
    private final TransferRuleRepository ruleRepository;

    public TransferEvaluationServiceImpl(
            TransferEvaluationResultRepository resultRepository,
            CourseRepository courseRepository,
            CourseContentTopicRepository topicRepository,
            TransferRuleRepository ruleRepository) {
        this.resultRepository = resultRepository;
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.ruleRepository = ruleRepository;
    }
}
