package com.poli.Pets.repository;

import com.poli.Pets.entity.PetEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {

    @Query(value = "select m.id,m.nombre ,m.raza ,m.edad ,m.peso ,c.cedula ,c.nombres, c.apellidos,c.direccion ,c.telefono ,med.nombre ,med.descripcion ,med.dosis  \n" +
            "from mascota m join detalle_medicamento dm on m.id = dm.id_mascota \n" +
            "join medicamento med on dm.id_medicamento = med.id \n" +
            "join cliente c on c.cedula = m.id_cliente \n" +
            "where c.cedula=:cedula", nativeQuery = true)
    List<String> dataFromReport(String cedula);

    @Query(value = "select * from mascota m where m.id_cliente = :cedula", nativeQuery = true)
    List<PetEntity> dataByCedula(String cedula);

    @Query(value = "select m.id ,m.nombre ,m.raza ,m.edad ,m.peso from mascota m join cliente c on m.id_cliente = c.cedula \n" +
            "where m.id_cliente = :cedula", nativeQuery = true)
    List<String> infoByCedula(String cedula);

}
