package com.secuite.secur.repository;

import com.secuite.secur.modeles.Etudiant;
import com.secuite.secur.modeles.promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface repoetudiant extends JpaRepository<Etudiant,Integer> {
    Etudiant findByUsername(String username);
    Etudiant findByNom(String username);

    Etudiant findByIde(int id);

    List<Etudiant> findByPromo(promotion p);
}
