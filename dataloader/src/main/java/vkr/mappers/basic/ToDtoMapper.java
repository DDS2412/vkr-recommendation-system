package vkr.mappers.basic;

import vkr.models.Identifiable;

public interface ToDtoMapper<DTO, E extends Identifiable> extends MappingConfig {
    DTO toDto(E entity);
}