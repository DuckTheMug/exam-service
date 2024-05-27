package huce.k65.mht1.exam_service.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import huce.k65.mht1.exam_service.constant.DeletedPredicateEnum;
import huce.k65.mht1.exam_service.dto.ExamAddDto;
import huce.k65.mht1.exam_service.dto.ExamSearchResponseDto;
import huce.k65.mht1.exam_service.entity.Exam;
import huce.k65.mht1.exam_service.entity.QExam;
import huce.k65.mht1.exam_service.repo.AccessGroupRepo;
import huce.k65.mht1.exam_service.repo.ExamRepo;
import huce.k65.mht1.exam_service.repo.PartRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamService {
    private final ExamRepo examRepo;

    private final ModelMapper modelMapper;

    private final JPAQueryFactory jpaQueryFactory;

    private final AccessGroupRepo accessGroupRepo;

    private final PartRepo partRepo;

    public List<ExamSearchResponseDto> search(String keyword) {
        Predicate searchPredicate = StringUtils.isNotEmpty(keyword)
                ? QExam.exam.examName.like(StringUtils.join("%", keyword, "%"))
                : null;
        List<Exam> exams = jpaQueryFactory.selectFrom(QExam.exam)
                .where(searchPredicate, DeletedPredicateEnum.EXAM.getPredicate())
                .fetch();
        return exams.stream().map(e -> {
            ExamSearchResponseDto dto = new ExamSearchResponseDto();
            modelMapper.map(e, dto);
            dto.setAccessGroups(e.getAccessGroups().stream().map(accessGroup -> modelMapper.map(accessGroup,
                    ExamSearchResponseDto.AccessGroupDto.class)).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(ExamAddDto dto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(dto, exam);
        exam.setAccessGroups(accessGroupRepo.findAllById(dto.accessGroupAccessGroupIds()).stream()
                .filter(accessGroup -> Objects.equals(accessGroup.getDeletedFlag(), Boolean.FALSE))
                .collect(Collectors.toList()));
        exam.setParts(partRepo.findAllById(dto.partPartIds()).stream()
                .filter(part -> Objects.equals(part.getDeletedFlag(), Boolean.FALSE))
                .collect(Collectors.toList()));
        examRepo.saveAndFlush(exam);
    }

    public void delete(Long id) {
        examRepo.deleteById(id);
    }
}
