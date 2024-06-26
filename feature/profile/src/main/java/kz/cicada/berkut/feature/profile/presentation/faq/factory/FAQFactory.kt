package kz.cicada.berkut.feature.profile.presentation.faq.factory

import kz.cicada.berkut.feature.profile.presentation.model.FAQDvo

class FAQFactory {

    fun create(): List<FAQDvo> {
        return listOf(
            FAQDvo(
                question = "Как я могу убедиться, что ребенок использует приложение Berkut правильно?",
                answer = "Мы рекомендуем проводить совместные просмотры инструкции, видеоматериалов и обучающих материалов о приложении Berkut вместе с вашим ребенком. Это поможет ему лучше понять функционал приложения и правила его использования. Важно также регулярно проверять его знания и навыки в использовании приложения.",
            ),
            FAQDvo(
                question = "Как Berkut обеспечивает конфиденциальность данных моих детей?",
                answer = "Berkut использует безопасные протоколы передачи данных и механизмы аутентификации, чтобы обеспечить защиту конфиденциальности данных вас и ваших детей. Мы также рекомендуем вам обсудить с ними важность сохранения конфиденциальности персональной информации и правила безопасности при использовании приложения Berkut.",
            ),
            FAQDvo(
                question = "Как часто мне следует проверять приложение Berkut для обеспечения безопасности ребенка?",
                answer = "Рекомендуется регулярно проверять приложение Berkut для мониторинга местоположения ребенка и получения уведомлений о любых задержках или необычных ситуациях. Однако частота проверок может зависеть от конкретных обстоятельств и уровня доверия к безопасности местонахождения младшей сестры.",
            ),
            FAQDvo(
                question = "Какие возможности предоставляет функция сохраненных местоположений в Berkut?",
                answer = "Функция сохраненных местоположений позволяет сохранять часто посещаемые места, такие как дом, школа или парк, чтобы быстро получать доступ к ним и отслеживать перемещения ребенка. Это особенно удобно для мониторинга его маршрутов и обеспечения безопасности в знакомых местах.",
            ),
            FAQDvo(
                question = "Как  я могу получить поддержку по приложению Berkut?",
                answer = "Приложение предоставляет доступ к технической поддержке и информации о команде разработчиков через специальный раздел поддержки. Вы можете получить необходимые контакты и связаться напрямую.",
            ),
            FAQDvo(
                question = "Почему не работает система уведомлений?",
                answer = "Если у вас возникли проблемы с системой уведомлений, в первую очередь стоит проверить, дали ли вы приложению разрешение на отправку уведомлений. Для этого можно просмотреть настройки приложения на вашем устройстве и убедиться, что опция \"Уведомления\" включена для Berkut. Если вы уже дали разрешение, но уведомления по-прежнему не приходят, рекомендуем обратиться в службу поддержки. Наши специалисты помогут вам разобраться с проблемой и предоставят необходимую помощь для ее решения.",
            ),
        )
    }
}