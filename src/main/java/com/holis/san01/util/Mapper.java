package com.holis.san01.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converte uma lista para outra lista
     * <p>
     * Exemplo de uso:
     * Converter para dto
     * List<UserDTO> userDtos = ConverterUtil.mapList(users, UserDTO.class);
     * <p>
     * Converter para entity
     * List<User> users = ConverterUtil.mapList(dtoUsers, User.class);
     *
     * @param source      (lista - objeto de entrada instanciado)
     * @param targetClass (lista - classe de saida)
     * @return - nova instancia da classe de saida
     */
    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * Converte um objeto para outro objeto
     * <p>
     * Exemplo de uso:
     * ConverterUtil.mapTo(opt.get(), DespMesDto.class);
     *
     * @param source    (objeto de entrada instanciado)
     * @param destClass (classe de saida)
     * @return nova instancia da classe de saida
     */
    public static <S, D> D mapTo(S source, Class<D> destClass) {

        return modelMapper.map(source, destClass);
    }

    /**
     * Converte um Objeto para um modelo
     *
     * @param <D>
     * @param entrada
     * @param saida
     * @return
     */
    public static <D> Object toModel(final Object entrada, Class<D> saida) {

        return modelMapper.map(entrada, saida);
    }

    /**
     * Converte uma Collection para outra Collection
     *
     * @param <D>
     * @param users
     * @param outClass
     * @return
     */
    public static <D> List<?> toCollection(List<?> users, Class<D> outClass) {
        return users.stream()
                .map(user -> toModel(user, outClass)).collect(Collectors.toList());
    }
}
