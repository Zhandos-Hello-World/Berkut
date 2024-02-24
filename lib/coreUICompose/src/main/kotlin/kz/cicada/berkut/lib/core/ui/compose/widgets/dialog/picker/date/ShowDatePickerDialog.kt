package kz.cicada.berkut.lib.core.ui.compose.widgets.dialog.picker.date

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.widgets.dialog.DialogControl
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun ShowDatePickerDialog(dialogControl: DialogControl<DatePickerDialogData, Long>) {
    ShowDialog(dialogControl) { data ->
        AlertDialogContent(data, dialogControl)
    }
}

@Composable
private fun <T : Any, R : Any> ShowDialog(
    dialogControl: DialogControl<T, R>,
    dialog: @Composable (data: T) -> Unit,
) {
    val state by dialogControl.stateFlow.collectAsState()
    (state as? DialogControl.State.Shown)?.data?.let { data ->
        dialog(data)
    }
}

@Composable
private fun AlertDialogContent(
    data: DatePickerDialogData,
    dialogControl: DialogControl<DatePickerDialogData, Long>,
) {
    val activity = LocalContext.current as AppCompatActivity
    val picker = MaterialDatePicker.Builder.datePicker().apply {
        data.selectedDate?.let { setSelection(it) }
        data.calendarConstraints?.let {
            setCalendarConstraints(
                getCalendarConstraints(minDate = it.first, maxDate = it.last),
            )
        }
    }.build()

    with(picker) {
        addOnPositiveButtonClickListener {
            dialogControl.sendResult(it)
        }
        addOnCancelListener {
            dialogControl.dismiss()
        }
        addOnDismissListener {
            dialogControl.dismiss()
        }
        addOnNegativeButtonClickListener {
            dialogControl.dismiss()
        }
        show(activity.supportFragmentManager, this.toString())
    }
}

private fun getCalendarConstraints(minDate: Long, maxDate: Long): CalendarConstraints {
    val dateValidatorMin: CalendarConstraints.DateValidator =
        DateValidatorPointForward.from(minDate)
    val dateValidatorMax: CalendarConstraints.DateValidator =
        DateValidatorPointBackward.before(maxDate)

    val listValidators = ArrayList<CalendarConstraints.DateValidator>()
    listValidators.apply {
        add(dateValidatorMin)
        add(dateValidatorMax)
    }
    val validators = CompositeDateValidator.allOf(listValidators)

    return CalendarConstraints.Builder()
        .setOpenAt(maxDate)
        .setStart(minDate)
        .setEnd(maxDate)
        .setValidator(validators)
        .build()
}

@Preview
@Composable
fun ShowDatePickerDialogPreview() {
    AppTheme {
        ShowDatePickerDialog(dialogControl = DialogControl())
    }
}