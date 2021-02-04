package vkr.mappers.basic;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@org.mapstruct.MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        disableSubMappingMethodsGeneration = true,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MappingConfig {
}
