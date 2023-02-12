package mehdi.satei.volvo.presentation.mapper

import mehdi.satei.domain.model.Model

interface ModelMapper<M: Model, UM: UiModel> {
    fun mapToUIModel(model: M, ): UM
}