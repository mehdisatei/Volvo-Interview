package mehdi.satei.data.mapper.base

import mehdi.satei.domain.model.Model

interface EntityMapper<E : Entity, M : Model> {
    fun mapToModel(entity: E): M
}