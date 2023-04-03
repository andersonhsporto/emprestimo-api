package dev.anderson.emprestimoapi.mapper;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "number", target = "address.number")
    @Mapping(source = "zipCode", target = "address.zipCode")
    ClientEntity toModel(ClientDto clientDto);
}
