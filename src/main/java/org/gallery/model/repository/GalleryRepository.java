package org.gallery.model.repository;

import jakarta.persistence.EntityManager;
import org.gallery.model.Account;
import org.gallery.model.Exhibit;
import org.gallery.model.Gallery;

import java.util.List;

public class GalleryRepository extends Repository<Gallery> {

    public GalleryRepository() {
        super(Gallery.class);
    }

    public GalleryRepository(String persistenceUnit) {
        super(Gallery.class, persistenceUnit);
    }

    public List<Gallery> listGalleries() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Gallery> galleries = entityManager.createQuery("SELECT g FROM Gallery g", Gallery.class).getResultList();

        entityManager.getTransaction().commit();

        return galleries;
    }

    public Gallery findByName(String keyword) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        List<Gallery> galleries = entityManager.createQuery("SELECT g FROM Gallery g WHERE g.name = :name", Gallery.class)
                .setParameter("name", keyword)
                .getResultList();

        Gallery gallery = new Gallery();
        if (galleries.isEmpty()) {
            gallery = null;
        } else {
            gallery = galleries.get(0);
        }

        entityManager.getTransaction().commit();

        return gallery;
    }

    public Gallery findByExhibit(Exhibit exhibit) {
        List<Gallery> galleries = listGalleries();
        for (Gallery gallery : galleries) {
            if (gallery.getExhibits() != null && gallery.getExhibits().contains(exhibit)) {
                return gallery;
            }
        }
        return null;
    }

    public Gallery findByEmployee(Account employee) {
        List<Gallery> galleries = listGalleries();
        for (Gallery gallery : galleries) {
            if (gallery.getEmployees() != null && gallery.getEmployees().contains(employee)) {
                return gallery;
            }
        }
        return null;
    }
}
