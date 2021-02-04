package vkr.mappers.basic;

import org.mapstruct.MappingTarget;
import vkr.models.Identifiable;

public interface ToEntityMapper<DTO,E extends Identifiable> extends MappingConfig {
    E toEntity(DTO dto);

    E   updateEntity(@MappingTarget E entity, DTO dto);
}
