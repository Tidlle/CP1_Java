package br.com.fiap.reflection;

import br.com.fiap.annotation.Descricao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlGenerator {

    public static String gerarSelect(Class<?> clazz) {
        String nomeTabela = getNomeTabela(clazz);
        return "SELECT * FROM " + nomeTabela;
    }

    public static String gerarSelectPorId(Class<?> clazz, Object id) {
        String nomeTabela = getNomeTabela(clazz);
        String colunaId = getNomeColunaId(clazz);
        return "SELECT * FROM " + nomeTabela + " WHERE " + colunaId + " = " + formatarValor(id);
    }

    public static String gerarInsert(Object obj) {
        Class<?> clazz = obj.getClass();
        String nomeTabela = getNomeTabela(clazz);

        List<String> colunas = new ArrayList<>();
        List<String> valores = new ArrayList<>();

        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);

                    colunas.add(column.name());
                    valores.add(formatarValor(field.get(obj)));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao gerar INSERT via Reflection", e);
        }

        return "INSERT INTO " + nomeTabela +
                " (" + String.join(", ", colunas) + ") VALUES (" +
                String.join(", ", valores) + ")";
    }

    public static String gerarUpdate(Object obj) {
        Class<?> clazz = obj.getClass();
        String nomeTabela = getNomeTabela(clazz);

        List<String> sets = new ArrayList<>();
        String condicaoId = "";

        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    Object valor = field.get(obj);

                    if (field.isAnnotationPresent(Id.class)) {
                        condicaoId = column.name() + " = " + formatarValor(valor);
                    } else {
                        sets.add(column.name() + " = " + formatarValor(valor));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao gerar UPDATE via Reflection", e);
        }

        return "UPDATE " + nomeTabela +
                " SET " + String.join(", ", sets) +
                " WHERE " + condicaoId;
    }

    public static String gerarDelete(Class<?> clazz, Object id) {
        String nomeTabela = getNomeTabela(clazz);
        String colunaId = getNomeColunaId(clazz);
        return "DELETE FROM " + nomeTabela + " WHERE " + colunaId + " = " + formatarValor(id);
    }

    private static String getNomeTabela(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Descricao.class)) {
            return clazz.getAnnotation(Descricao.class).descricao();
        }

        if (clazz.isAnnotationPresent(Table.class)) {
            return clazz.getAnnotation(Table.class).name();
        }

        return clazz.getSimpleName().toUpperCase();
    }

    private static String getNomeColunaId(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Column.class)) {
                return field.getAnnotation(Column.class).name();
            }
        }
        throw new RuntimeException("Nenhum campo @Id encontrado.");
    }

    private static String formatarValor(Object valor) {
        if (valor == null) return "NULL";
        if (valor instanceof String || valor instanceof Character) {
            return "'" + valor + "'";
        }
        if (valor instanceof java.util.Calendar) {
            return "'" + ((java.util.Calendar) valor).getTime() + "'";
        }
        return valor.toString();
    }
}