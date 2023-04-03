package dev.anderson.emprestimoapi.mapper;

import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.entities.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membership", ignore = true)
    @Mapping(target = "endValue", ignore = true)
    LoanEntity toModel(LoanDto loanDto);
}
