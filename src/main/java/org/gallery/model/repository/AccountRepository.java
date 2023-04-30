package org.gallery.model.repository;

import jakarta.persistence.EntityManager;
import org.gallery.model.Account;

import java.util.List;

public class AccountRepository extends Repository<Account> {

    public AccountRepository() {
        super(Account.class);
    }

    public AccountRepository(String persistenceUnit) {
        super(Account.class, persistenceUnit);
    }

    public List<Account> listAccounts() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();

        entityManager.getTransaction().commit();

        return accounts;
    }

    public Account findByUsername(String keyword) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
                .setParameter("username", keyword)
                .getResultList();

        Account account = new Account();
        if (accounts.isEmpty()) {
            account = null;
        } else {
            account = accounts.get(0);
        }

        entityManager.getTransaction().commit();

        return account;
    }
}
