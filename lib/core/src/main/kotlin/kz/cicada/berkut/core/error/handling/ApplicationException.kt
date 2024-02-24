package kz.cicada.berkut.core.error.handling

open class ApplicationException(cause: Throwable?) : Exception(cause)

/**
 * Отсутствие доступа к данным (Access token невалиден или истек)
 */
class UnauthorizedException(cause: Throwable?, val status: String?) : ApplicationException(cause)

/**
 * Получен ответ от сервера, но он невалиден - 4xx, 5xx
 */
class ServerException(cause: Throwable?, val status: String?) : ApplicationException(cause)

/**
 * Ошибка передачи данных
 */
class TransportException(cause: Throwable?) : ApplicationException(cause)

/**
 * Не удалось подключиться к серверу и проблема скорее всего у клиента
 */
class NoInternetException(cause: Throwable?) : ApplicationException(cause)

/**
 *  Не удалось подключиться к серверу и проблема скорее всего на сервере
 */
class NoServerResponseException(cause: Throwable?) : ApplicationException(cause)

/**
 * Проблемы при парсинге json или нехватка полей
 */
class DeserializationException(cause: Throwable?) : ApplicationException(cause)

/**
 * Указывает, что клиент и сервер не смогли согласовать желаемый уровень безопасности.
 * Проблема скорее всего у клиента.
 * Требуется проверка Даты и времени на устройстве.
 */
class SslHandshakeException(cause: Throwable?) : ApplicationException(cause)

/**
 * Какая-то неизвестная проблема
 */
class UnknownException(cause: Throwable?) : ApplicationException(cause)