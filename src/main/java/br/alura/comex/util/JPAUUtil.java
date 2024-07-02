package br.alura.comex.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUUtil {
    // Declara uma constante estática e final que armazena a instância da fábrica de gerenciadores de entidades
    // A fábrica de gerenciadores de entidades é criada utilizando a unidade de persistência "loja"
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("comex");

    // Método público e estático que retorna um novo EntityManager
    public static EntityManager getEntityManager() {
        // Cria e retorna um novo gerenciador de entidades a partir da fábrica
        return FACTORY.createEntityManager();
    }


}
