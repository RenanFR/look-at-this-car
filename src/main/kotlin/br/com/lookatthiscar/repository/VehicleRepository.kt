package br.com.lookatthiscar.repository

import br.com.lookatthiscar.model.entity.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository: JpaRepository<VehicleEntity, Int> {
}