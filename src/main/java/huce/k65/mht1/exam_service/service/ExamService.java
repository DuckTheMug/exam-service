package huce.k65.mht1.exam_service.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import huce.k65.mht1.exam_service.dto.ExamInfoDto;
import huce.k65.mht1.exam_service.entity.QExam;
import huce.k65.mht1.exam_service.repo.ExamRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamService {
    ExamRepo examRepo;

    ModelMapper modelMapper;

    JPAQueryFactory jpaQueryFactory;

    public ExamInfoDto getExamInfo() {
    return modelMapper.map(jpaQueryFactory.select(QExam.exam.examId,
                QExam.exam.examName,
                QExam.exam.examDuration,
                QExam.exam.note,
                QExam.exam.repeatableCount,
                QExam.exam.repeatableLimit,
                QExam.exam.reviewableAfterFinishFlag
            ), ExamInfoDto.class);
    }
}
