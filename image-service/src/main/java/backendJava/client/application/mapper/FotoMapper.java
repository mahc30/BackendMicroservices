package backendJava.client.application.mapper;

import backendJava.client.application.dto.FotoDTO;
import backendJava.client.domain.entity.Foto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FotoMapper {
    FotoMapper INSTANCE = Mappers.getMapper(FotoMapper.class);

    @Mapping(source="id", target="id")
    @Mapping(source="file", target="file")
    FotoDTO fotoToFotoDto(Foto foto);

    @Mapping(source="id", target="id")
    @Mapping(source="file", target="file")
    @Mapping(target = "cliente", ignore = true)
    Foto fotoDtoToFoto(FotoDTO foto);

    List<FotoDTO> mapEntityListToDtoList(List<Foto> fotos);
    List<Foto> mapDtoListToEntityList(List<FotoDTO> fotos);
}
