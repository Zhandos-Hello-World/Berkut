package kz.cicada.berkut.feature.tasks.domain.model

enum class TaskStatus {
    NEW, VERIFIED, CONFIRMED, UNKNOWN;

    companion object {
        fun find(value: String): TaskStatus {
            return try {
                entries.find { it.name == value }!!
            } catch (ex: Exception) {
                UNKNOWN
            }
        }
    }
}