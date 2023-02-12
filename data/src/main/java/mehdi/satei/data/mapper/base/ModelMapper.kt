package mehdi.satei.data.mapper.base

import mehdi.satei.domain.model.Model

interface ModelMapper<M: Model, E: Entity> {
    fun mapToEntity(model: M): E
}