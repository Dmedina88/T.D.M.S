package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.data.PoopType

class AddEditBindings(view: View) {
  val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
  val poopTypeSpinner = view.findViewById<Spinner>(R.id.poop_spinner)
  val notesEditText = view.findViewById<EditText>(R.id.notes)
  val saveButton = view.findViewById<Button>(R.id.save)

  init {
    poopTypeSpinner.adapter = ArrayAdapter<PoopType>(
        view.context,
        android.R.layout.simple_list_item_1, PoopType.values()
    )

  }

}