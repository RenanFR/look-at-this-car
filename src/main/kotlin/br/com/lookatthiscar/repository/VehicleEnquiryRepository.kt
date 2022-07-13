package br.com.lookatthiscar.repository

import br.com.lookatthiscar.model.entity.VehicleEnquiry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleEnquiryRepository: JpaRepository<VehicleEnquiry, Int> {
}