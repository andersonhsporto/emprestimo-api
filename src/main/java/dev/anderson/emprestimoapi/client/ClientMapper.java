package dev.anderson.emprestimoapi.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "number", target = "address.number")
    @Mapping(source = "zipCode", target = "address.zipCode")
    ClientEntity toModel(ClientDto clientDto);

    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.number", target = "number")
    @Mapping(source = "address.zipCode", target = "zipCode")
    ClientDto toDto(ClientEntity clientEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "number", target = "address.number")
    @Mapping(source = "zipCode", target = "address.zipCode")
    void updateClientEntity(ClientDto clientDto, @MappingTarget ClientEntity clientEntity);

    List<ClientDto> toDtoList(List<ClientEntity> clientEntityList);
}
