package com.evg_ivanoff.thingstodo.model

data class Action(
    var activity: String?,          // описание деятельности
    var type: String?,              // тип деятельности: "education", "recreational", "social",
                                    // "diy", "charity", "cooking", "relaxation", "music", "busywork"
    var participants: Int?,         // количество участников
    var price: Double?,             // коэффициент стоимости от 0 до 1, где 0 - бесплатно
    var link: String?,              // ссылка, при наличии
    var key: Int?,                  // уникальный ключ деятельности
    var accessibility: Double?      // коэффициент доступности деятельности от 0 до 1,
                                    // где  0 - наиболее доступно
)
