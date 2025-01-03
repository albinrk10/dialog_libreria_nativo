package com.albin.dialog.client

object AppConstants {
    // Títulos y Descripciones
    const val DIALOG_TITLE_CONFIRMATION = "Transferencia"
    const val DIALOG_DESCRIPTION_CONFIRMATION = "Transferencia de $10.000 a la cuenta 1234567"
    const val DIALOG_TITLE_SELECT_ROUTE = "Seleccione una marca"
    const val DIALOG_DESCRIPTION_SELECT_ROUTE = "Seleccione una marca para continuar"

    // Rutas
    val routes: List<String> = listOf(
       //marca de autos
        "Toyota",
        "Chevrolet",
        "Ford",
        "Nissan",
        "Hyundai",


    )

    // Colores
    const val BACKGROUND_COLOR = "#FFFFFF"
    const val BUTTON_COLOR = "#FF0000"

    // Tamaños de fuente
    const val TITLE_SIZE = 24f
    const val DESCRIPTION_SIZE = 16f


    // Botones y mensajes
    const val POSITIVE_BUTTON_TEXT = "Sí"
    const val NEGATIVE_BUTTON_TEXT = "No"
    const val POSITIVE_BUTTON_TEXT_ROUTE = "Aceptar"
    const val NEGATIVE_BUTTON_TEXT_ROUTE = "Cancelar"

    // Mensajes de Toast
    const val TOAST_CONFIRMATION = "Transferencia realizada con éxito."
    const val TOAST_CANCELLED = "Se canceló la transferencia."
    const val TOAST_ROUTE_SELECTED = "Marca seleccionada"
    const val TOAST_SELECTION_CANCELLED = "Selección cancelada"
}
