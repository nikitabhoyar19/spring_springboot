package com.guide.studentmanagementsystem.mapper;

import com.guide.studentmanagementsystem.entity.TaskRequestDTO;
import com.guide.studentmanagementsystem.entity.TaskResponseDTO;
import com.guide.studentmanagementsystem.entity.TaskStatus;
import com.guide.studentmanagementsystem.entity.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // DTO - Entity
    @Mapping(target = "taskStatus",expression = "java(com.guide.studentmanagementsystem.entity.TaskStatus.valueOf(dto.getTaskStatus().toUpperCase()))")
    Tasks toEntity(TaskRequestDTO dto);

    // Entity - DTO
    @Mapping(
            target = "taskStatus",
            expression = "java(task.getTaskStatus().name())"
    )//Convert enum to string while sending response”
    TaskResponseDTO toResponse(Tasks task);

    // update
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(
            target = "taskStatus",
            expression = "java(com.guide.studentmanagementsystem.entity.TaskStatus.valueOf(dto.getTaskStatus().toUpperCase()))"
    )
    void updateTaskFromDto(TaskRequestDTO dto, @MappingTarget Tasks task);//@MappingTarget - Update existing object instead of creating new one
}
