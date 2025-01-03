package com.albin.dialog.client

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.albin.dialog.client.AppConstants.DESCRIPTION_SIZE
import com.albin.dialog.databinding.CustomListDialogBinding
import com.albin.dialog.databinding.CustomSimpleDialogBinding

class DialogDriver(private val context: Context) {

    private var title: String? = null
    private var titleSize: Float = DESCRIPTION_SIZE

    private var description: String? = null
    private var positiveButtonText: String? = null
    private var negativeButtonText: String? = null
    private var positiveButtonClickListener: (() -> Unit)? = null
    private var negativeButtonClickListener: (() -> Unit)? = null
    private var listItems: List<String>? = null
    private var dialog: AlertDialog? = null

    private var backgroundColor: Int? = null
    private var backgroundBorderColor: Int? = null
    private var backgroundBorderWidth: Int? = null

    // Setters para estilo del título
    fun setTitle(title: String, size: Float = DESCRIPTION_SIZE) = apply {
        this.title = title
        this.titleSize = size
    }

    // Setters para descripción
    fun setDescription(description: String) = apply {
        this.description = description
    }

    // Setters para el botón positivo
    fun setPositiveButton(text: String, listener: () -> Unit) = apply {
        this.positiveButtonText = text
        this.positiveButtonClickListener = listener
    }

    // Setters para el botón negativo
    fun setNegativeButton(text: String, listener: () -> Unit) = apply {
        this.negativeButtonText = text
        this.negativeButtonClickListener = listener
    }

    // Setters para lista de ítems
    fun setListItems(items: List<String>) = apply {
        this.listItems = items
    }

    // Setters para color de fondo y borde
    fun setBackgroundColor(colorBackground: String? = null, colorBorder: String? = null, borderWidth: Int? = null) = apply {
        if (colorBackground != null) {
            this.backgroundColor = Color.parseColor(colorBackground)
        }
        if (colorBorder != null) {
            this.backgroundBorderColor = Color.parseColor(colorBorder)
        }
        if (borderWidth != null) {
            this.backgroundBorderWidth = borderWidth
        }
    }

    // Construcción del diálogo dependiendo de los parámetros
    fun build() {
        if (listItems.isNullOrEmpty()) {
            buildSimpleDialog()
        } else {
            buildListDialog()
        }
    }

    // Método que configura el fondo del diálogo
    private fun setDialogBackground(binding: View) {
        val backgroundDrawable = ContextCompat.getDrawable(context,
            android.R.drawable.alert_light_frame
        )?.mutate()
        if (backgroundDrawable is GradientDrawable) {
            backgroundColor?.let {
                backgroundDrawable.setColor(it)
            }
            backgroundBorderColor?.let { borderColor ->
                backgroundBorderWidth?.let { borderWidth ->
                    backgroundDrawable.setStroke(borderWidth, borderColor)
                }
            }
            binding.background = backgroundDrawable
        }
    }

    // Método para crear el diálogo simple
    private fun buildSimpleDialog() {
        val binding = CustomSimpleDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title ?: "Default Title"
        binding.dialogDescription.text = description ?: "Default description"
        binding.dialogTitle.textSize = titleSize
        binding.dialogButton.text = positiveButtonText ?: "Aceptar"

        setDialogBackground(binding.root)

        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        binding.dialogButton.setOnClickListener {
            positiveButtonClickListener?.invoke()
            dismissDialog()
        }

        negativeButtonText?.let {
            binding.dialogButtonNo.text = it
            binding.dialogButtonNo.setOnClickListener {
                negativeButtonClickListener?.invoke()
                dismissDialog()
            }
            binding.dialogButtonNo.visibility = View.VISIBLE
        }

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    // Método para crear un diálogo con lista de elementos
    private fun buildListDialog() {
        val binding = CustomListDialogBinding.inflate(LayoutInflater.from(context))
        binding.dialogTitle.text = title ?: "Default Title"
        binding.dialogDescription.text = description ?: "Default description"
        setDialogBackground(binding.root)

        listItems?.let {
            val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
            binding.dialogList.adapter = adapter

            binding.dialogList.setOnItemClickListener { _, _, position, _ ->
                val selectedItem = listItems?.get(position)
                Toast.makeText(context, "Seleccionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }
        }

        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        binding.dialogButtonYes.text = positiveButtonText ?: "Aceptar"
        binding.dialogButtonYes.setOnClickListener {
            positiveButtonClickListener?.invoke()
            dismissDialog()
        }

        if (negativeButtonText != null) {
            binding.dialogButtonNo.text = negativeButtonText
            binding.dialogButtonNo.setOnClickListener {
                negativeButtonClickListener?.invoke()
                dismissDialog()
            }
            binding.dialogButtonNo.visibility = View.VISIBLE
        }

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    // Método para cerrar el diálogo
    fun dismissDialog() {
        dialog?.dismiss()
    }
    fun setTitleStyle(title: String?, size: Float?) = apply {
        if (!title.isNullOrEmpty()) {
            this.title = title
        }
        if (size != null) {
            this.titleSize = size
        }
    }

}