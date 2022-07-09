package br.com.lookatthiscar.repository

import br.com.lookatthiscar.model.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository: JpaRepository<VehicleEntity, Int> {
}