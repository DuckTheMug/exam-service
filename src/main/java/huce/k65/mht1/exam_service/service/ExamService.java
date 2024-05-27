package huce.k65.mht1.exam_service.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import huce.k65.mht1.exam_service.dto.ExamAddDto;
import huce.k65.mht1.exam_service.dto.ExamSearchResponseDto;
import huce.k65.mht1.exam_service.entity.Exam;
import huce.k65.mht1.exam_service.entity.QExam;
import huce.k65.mht1.exam_service.repo.ExamRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamService {
    ExamRepo examRepo;

    ModelMapper modelMapper;

    JPAQueryFactory jpaQueryFactory;

    public ExamSearchResponseDto search(String keyword) {
        return modelMapper.map(jpaQueryFactory.selectFrom(QExam.exam)
                .where(QExam.exam.examName.like(StringUtils.join("%", keyword, "%"))),
                ExamSearchResponseDto.class);
    }

    public void add(ExamAddDto dto) {
        examRepo.saveAndFlush(modelMapper.map(dto, Exam.class));
    }
}
