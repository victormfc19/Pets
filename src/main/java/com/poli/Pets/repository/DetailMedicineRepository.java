package com.poli.Pets.repository;

import com.poli.Pets.entity.DetailMedicineEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailMedicineRepository extends JpaRepository<DetailMedicineEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.detalle_medicamento WHERE id_mascota=:id_mascota", nativeQuery = true)
    int deleteAllById(Integer id_mascota);

}
