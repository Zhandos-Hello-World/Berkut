package kz.cicada.berkut.lib.core.ui.compose.widgets.dialog.picker.date

data class DatePickerDialogData(
    val selectedDate: Long? = null,
    val calendarConstraints: LongRange? = null,
)