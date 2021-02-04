package vkr.mappers.basic;

import vkr.models.Identifiable;

public interface TwoWayMapper<DTO, E extends Identifiable> extends ToDtoMapper<DTO, E>, ToEntityMapper<DTO, E> {
}
