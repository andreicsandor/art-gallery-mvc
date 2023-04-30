package org.gallery.model.repository;

import jakarta.persistence.EntityManager;
import org.gallery.model.Exhibit;

import java.util.List;

public class ExhibitRepository extends Repository<Exhibit> {

    public ExhibitRepository() {
        super(Exhibit.class);
    }

    public ExhibitRepository(String persistenceUnit) {
        super(Exhibit.class, persistenceUnit);
    }

    public List<Exhibit> listExhibits() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Exhibit> exhibits = entityManager.createQuery("SELECT e FROM Exhibit e", Exhibit.class).getResultList();

        entityManager.getTransaction().commit();

        return exhibits;
    }

    public Exhibit findByNameArtist(String keywordName, String keywordArtist) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Exhibit> exhibits = entityManager.createQuery("SELECT e FROM Exhibit e WHERE e.name = :name AND e.artist = :artist", Exhibit.class)
                .setParameter("name", keywordName)
                .setParameter("artist", keywordArtist)
                .getResultList();

        Exhibit exhibit = new Exhibit();
        if (exhibits.isEmpty()) {
            exhibit = null;
        } else {
            exhibit = exhibits.get(0);
        }

        entityManager.getTransaction().commit();

        return exhibit;
    }

    public List<Exhibit> filterExhibitsByArtist(String keyword) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Exhibit> exhibits = entityManager.createQuery("SELECT e FROM Exhibit e WHERE e.artist = :artistName", Exhibit.class)
                .setParameter("artistName", keyword)
                .getResultList();

        entityManager.getTransaction().commit();

        return exhibits;
    }

    public List<Exhibit> filterExhibitsByType(String keyword) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Exhibit> exhibits = entityManager.createQuery("SELECT e FROM Exhibit e WHERE e.type = :typeName", Exhibit.class)
                .setParameter("typeName", keyword)
                .getResultList();

        entityManager.getTransaction().commit();

        return exhibits;
    }
}
