package mehdi.satei.data.mapper.base

import mehdi.satei.domain.model.Model

interface DtoMapper<D : Dto, M : Model> {
    fun mapToModel(dto: D): M
}