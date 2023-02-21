package com.secuite.secur.repository;

import com.secuite.secur.modeles.criteresevaluation;
import com.secuite.secur.modeles.formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repocriteres extends JpaRepository<criteresevaluation,Integer> {
    criteresevaluation findByIdo(int id);
    criteresevaluation findByNom(String nom);
    criteresevaluation findByNomAndFormations(String nom, formation p);
}
