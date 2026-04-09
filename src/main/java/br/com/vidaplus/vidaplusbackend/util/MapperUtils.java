package br.com.vidaplus.vidaplusbackend.util;

import org.modelmapper.ModelMapper;

public class MapperUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, T> D map(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, T> void map(T entity, D dto) {
        modelMapper.map(entity, dto);
    }
}
