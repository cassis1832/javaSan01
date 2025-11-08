package com.holis.san01.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitária para criação dinâmica de Specifications JPA
 * com suporte a operadores (> >= < <= =) e LIKE (%valor%).
 */
public class SpecificationUtils {

    private static final Pattern OPERATOR_PATTERN = Pattern.compile("^(<=|>=|<|>|=)?(.*)$");

    public static <T> Specification<T> createSpecification(Map<String, String> filters) {
        return (root, query, cb) -> {
            Predicate[] predicates = filters.entrySet().stream()
                    .filter(e -> e.getValue() != null && !e.getValue().isBlank())
                    .map(e -> buildPredicate(root.get(e.getKey()), e.getValue().trim(), cb))
                    .toArray(Predicate[]::new);

            return cb.and(predicates);
        };
    }

    private static Predicate buildPredicate(Path<?> path, String rawValue, CriteriaBuilder cb) {
        Class<?> type = path.getJavaType();
        Matcher matcher = OPERATOR_PATTERN.matcher(rawValue);

        String operator = "=";
        String value = rawValue;

        if (matcher.matches()) {
            if (matcher.group(1) != null) operator = matcher.group(1);
            if (matcher.group(2) != null) value = matcher.group(2).trim();
        }

        // 🔹 Strings
        if (type.equals(String.class)) {
            String lowerVal = value.toLowerCase();
            if (lowerVal.contains("%")) {
                return cb.like(cb.lower(path.as(String.class)), lowerVal);
            }
            return cb.like(cb.lower(path.as(String.class)), "%" + lowerVal + "%");
        }

        // 🔹 Números
        if (Number.class.isAssignableFrom(type)) {
            try {
                Number numberValue = parseNumber(value, type);
                return switch (operator) {
                    case ">" -> cb.gt(path.as(Number.class), numberValue);
                    case ">=" -> cb.ge(path.as(Number.class), numberValue);
                    case "<" -> cb.lt(path.as(Number.class), numberValue);
                    case "<=" -> cb.le(path.as(Number.class), numberValue);
                    default -> cb.equal(path, numberValue);
                };
            } catch (NumberFormatException ex) {
                return cb.disjunction();
            }
        }

        // 🔹 Booleanos
        if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            boolean boolValue = value.equalsIgnoreCase("true") || value.equals("1");
            return cb.equal(path, boolValue);
        }

        // 🔹 Datas (LocalDate)
        if (type.equals(LocalDate.class)) {
            try {
                LocalDate dateValue = LocalDate.parse(value);
                return switch (operator) {
                    case ">" -> cb.greaterThan(path.as(LocalDate.class), dateValue);
                    case ">=" -> cb.greaterThanOrEqualTo(path.as(LocalDate.class), dateValue);
                    case "<" -> cb.lessThan(path.as(LocalDate.class), dateValue);
                    case "<=" -> cb.lessThanOrEqualTo(path.as(LocalDate.class), dateValue);
                    default -> cb.equal(path, dateValue);
                };
            } catch (Exception ex) {
                return cb.disjunction();
            }
        }

        // 🔹 Datas e horas (LocalDateTime)
        if (type.equals(LocalDateTime.class)) {
            try {
                LocalDateTime dateTimeValue = LocalDateTime.parse(value);
                return switch (operator) {
                    case ">" -> cb.greaterThan(path.as(LocalDateTime.class), dateTimeValue);
                    case ">=" -> cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), dateTimeValue);
                    case "<" -> cb.lessThan(path.as(LocalDateTime.class), dateTimeValue);
                    case "<=" -> cb.lessThanOrEqualTo(path.as(LocalDateTime.class), dateTimeValue);
                    default -> cb.equal(path, dateTimeValue);
                };
            } catch (Exception ex) {
                return cb.disjunction();
            }
        }

        // 🔹 Fallback (igualdade)
        return cb.equal(path, value);
    }

    private static Number parseNumber(String value, Class<?> type) {
        if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(value);
        }
        if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.parseLong(value);
        }
        if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.parseDouble(value);
        }
        if (type.equals(Float.class) || type.equals(float.class)) {
            return Float.parseFloat(value);
        }
        if (type.equals(Short.class) || type.equals(short.class)) {
            return Short.parseShort(value);
        }
        throw new NumberFormatException("Tipo numérico não suportado: " + type);
    }
}

