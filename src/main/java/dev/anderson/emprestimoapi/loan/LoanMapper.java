package dev.anderson.emprestimoapi.loan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endValue", ignore = true)
    @Mapping(target = "cpfClient", source = "cpf")
    LoanEntity toModel(LoanDto loanDto, String cpf);

    @Mapping(target = "cpf", source = "cpfClient")
    LoanDto toDto(LoanEntity loanEntity);

    @Mapping(target = "cpf", source = "cpfClient")
    List<LoanDto> toDtoList(List<LoanEntity> loanEntityList);
}
