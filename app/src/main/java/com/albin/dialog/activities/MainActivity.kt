package com.albin.dialog.activities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albin.dialog.client.AppConstants
import com.albin.dialog.client.AppConstants.BACKGROUND_COLOR
import com.albin.dialog.client.AppConstants.TextButton
import com.albin.dialog.client.DialogDriver
import com.albin.dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dialogDriver: DialogDriver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflar el layout con View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar insets para adaptarse a las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.buttonShowDialog2) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón para el primer diálogo (diálogo simple)
        binding.buttonShowDialog.setOnClickListener {
            showDialog()
        }

        // Botón para el segundo diálogo (diálogo con lista)
        binding.buttonShowDialog2.setOnClickListener {
            showHeroDialog()
        }
    }

    // Función para el primer diálogo (diálogo simple)
    private fun showDialog() {
        dialogDriver = DialogDriver(this)
            .setTitleStyle(AppConstants.DIALOG_TITLE_CONFIRMATION, AppConstants.TITLE_SIZE)
            .setDescription(AppConstants.DIALOG_DESCRIPTION_CONFIRMATION)
            .setPositiveButton(AppConstants.POSITIVE_BUTTON_TEXT) {
                Toast.makeText(this, AppConstants.TOAST_CONFIRMATION, Toast.LENGTH_LONG).show()
            }
            .setPositiveButtonColor(TextButton)  // Establecer color personalizado para el botón positivo
            .setCancelableOnTouchOutside(true) // Configurar si se cierra al tocar fuera
            .setDialogBackgroundColor(BACKGROUND_COLOR) // Establecer el color de fondo verde dinámicamente

        dialogDriver?.build()
    }

    // Función para el segundo diálogo (diálogo con lista)
    private fun showHeroDialog() {
        dialogDriver = DialogDriver(this)
            .setTitleStyle(AppConstants.DIALOG_TITLE_SELECT_ROUTE, AppConstants.TITLE_SIZE)
            .setDescription(AppConstants.DIALOG_DESCRIPTION_SELECT_ROUTE)
            .setListItems(AppConstants.routes)
            .setPositiveButton(AppConstants.POSITIVE_BUTTON_TEXT_ROUTE) {
                Toast.makeText(this, AppConstants.TOAST_ROUTE_SELECTED, Toast.LENGTH_SHORT).show()
            }
            .setCancelableOnTouchOutside(false) // Configurar si se cierra al tocar fuera
            .setDialogBackgroundColor(BACKGROUND_COLOR)  // Establecer el color de fondo verde dinámicamente


        dialogDriver?.build()
    }
}