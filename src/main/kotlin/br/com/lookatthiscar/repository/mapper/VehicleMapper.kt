package br.com.lookatthiscar.repository.mapper

import br.com.lookatthiscar.model.VehicleData
import br.com.lookatthiscar.model.VehicleEntity
import br.com.lookatthiscar.model.VehicleJson
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface VehicleMapper {

    @Mappings(
        Mapping(source = "carModel.currentTextValue", target = "carModel"),
        Mapping(source = "carMake.currentTextValue", target = "carMake"),
    )
    fun entityFromDTO(vehicle: VehicleJson) : VehicleEntity

    @Mappings(
        Mapping(source = "carMake.currentTextValue", target = "carMake"),
        Mapping(source = "numberOfSeats.currentValue", target = "seats"),
        Mapping(source = "engineSize.currentValue", target = "engineCc"),
        Mapping(source = "fuelType.currentValue", target = "fuel"),
    )
    fun entityFromDataDTO(vehicle: VehicleData) : VehicleEntity

    @Mappings(
        Mapping(source = "carMake", target = "carMake.currentTextValue"),
        Mapping(source = "carModel", target = "carModel.currentTextValue"),
    )
    fun dtoFromEntity(vehicle: VehicleEntity) : VehicleJson
}