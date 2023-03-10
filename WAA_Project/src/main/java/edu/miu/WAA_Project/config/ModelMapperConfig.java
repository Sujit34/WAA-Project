package edu.miu.WAA_Project.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        /*
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(Property.class, ListingPropertyDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getPropertyAttributes(), ListingPropertyDto::setPropertyAttributesBasicDto);
                });
        modelMapper.typeMap(Offer.class, OfferDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getProperty().getPropertyType(), OfferDto::setPropertyType);
                    mapper.map(src -> src.getProperty().getPropertyImages(), OfferDto::setPropertyImage);
                });
        */

        return modelMapper;
    }
}
