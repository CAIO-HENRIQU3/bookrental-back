package com.caiohenrique.bookrental.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String entity, Integer id) {
        super(createMessage(entity, id));
    }

    private static String createMessage(String entity, Integer id) {
        if (id == null || id == 0) {
            throw new BusinessExceptions("'ID' de " + entity + " não pode ser nulo.");
        } else {
            return "Não foi possível encontrar " + entity + " com o id: " + id;
        }
    }

}