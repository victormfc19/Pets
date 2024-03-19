package com.poli.Pets.repository;

import com.poli.Pets.entity.DetailMedicineEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailMedicineRepository extends JpaRepository<DetailMedicineEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.detalle_medicamento WHERE id_mascota=:id_mascota", nativeQuery = true)
    int deleteAllById(Integer id_mascota);

    @Transactional
    @Query(value = "select m.nombre ,m.descripcion ,m.dosis  from detalle_medicamento dm \n" +
            "join medicamento m on id_medicamento = m.id \n" +
            "where id_mascota = :id_mascota", nativeQuery = true)
    List<String> infoMedicinesByPet(Integer id_mascota);

}
