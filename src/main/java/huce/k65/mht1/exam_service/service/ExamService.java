package huce.k65.mht1.exam_service.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import huce.k65.mht1.exam_service.constant.DeletedPredicateEnum;
import huce.k65.mht1.exam_service.dto.ExamAddDto;
import huce.k65.mht1.exam_service.dto.ExamSearchResponseDto;
import huce.k65.mht1.exam_service.entity.Exam;
import huce.k65.mht1.exam_service.entity.QExam;
import huce.k65.mht1.exam_service.repo.ExamRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamService {
    private final ExamRepo examRepo;

    private final ModelMapper modelMapper;

    private final JPAQueryFactory jpaQueryFactory;

    public List<ExamSearchResponseDto> search(String keyword) {
        Predicate searchPredicate = StringUtils.isNotEmpty(keyword)
                ? QExam.exam.examName.like(StringUtils.join("%", keyword, "%"))
                : null;
        List<Exam> exams = jpaQueryFactory.selectFrom(QExam.exam)
                .where(searchPredicate, DeletedPredicateEnum.EXAM.getPredicate())
                .fetch();
        return exams.stream().map(e -> modelMapper.map(e, ExamSearchResponseDto.class))
                .collect(Collectors.toList());
    }

    public void add(ExamAddDto dto) {
        examRepo.saveAndFlush(modelMapper.map(dto, Exam.class));
    }

    public void delete(Long id) {
        examRepo.deleteById(id);
    }
}
