package com.holis.san01.util;

import org.modelmapper.ModelMapper;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Converter {

    private static ModelMapper modelMapper = new ModelMapper();

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

    /**
     * Remover acentuação de uma string
     *
     * @param value
     * @return
     */
    public static String removeAccents(String value) {
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizer).replaceAll("");
    }

    // public static <D> List<?> toList(List<?> entradas, Class<D> saidas) {
    // return entradas.stream()
    // .map(entrada -> toModel(entrada, saidas)).collect(Collectors.toList());
    // }

    // public Object stringToObject(String mensagemKafka, Object classe) {
    // Class<?> clazz = classe.getClass();
    //
    // Field[] fields = clazz.getFields();
    // List<String> users = Stream.of(mensagemKafka.split(",", -1))
    // .collect(Collectors.toList());
    // try {
    // for (int i = 0; i < fields.length; i++) {
    // Field fieldID = clazz.getField(fields[i].getName());
    //
    // if (fieldID.getType().getSimpleName().equalsIgnoreCase("Integer"))
    // fieldID.set(classe, new Integer(users.get(i)));
    // if (fieldID.getType().getSimpleName().equalsIgnoreCase("int"))
    // fieldID.set(classe, Integer.parseInt(users.get(i)));
    // if (fieldID.getType().getSimpleName().equalsIgnoreCase("String"))
    // fieldID.set(classe, users.get(i));
    //
    // }
    // } catch (NumberFormatException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return classe;
    // }

    // public String objectToString(String mensagemKafka, Object classe) {
    // String deserialize = "";
    // Class<?> clazz = classe.getClass();
    //
    // Field[] fields = clazz.getFields();
    // List<String> users = Stream.of(mensagemKafka.split(",", -1)).collect(Collectors.toList());
    // try {
    // for (int i = 0; i < fields.length; i++) {
    // Field fieldID = clazz.getField(fields[i].getName());
    //
    // deserialize += fieldID.get(classe) + ",";
    // }
    // } catch (NumberFormatException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return deserialize.substring(0, deserialize.length() - 1);
    // }

    // private String gerarPiPullNext() {
    // return Base64.getEncoder().encodeToString(LocalDateTime.now().toString().getBytes());
    // }

}
