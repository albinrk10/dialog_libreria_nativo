# DialogDriver Library

DialogDriver es una biblioteca para facilitar la creación y personalización de diálogos en aplicaciones Android. Proporciona opciones para configurar títulos, descripciones, listas de elementos y colores personalizados, entre otros. Este README explica cómo implementar esta biblioteca en un proyecto Android y cómo utilizar sus métodos principales.

## Instalación

1. Agrega la biblioteca a tu proyecto copiando los archivos necesarios:
   - La clase `DialogDriver`.
   - Los archivos XML (`custom_simple_dialog.xml`, `custom_list_dialog.xml` y cualquier otro recurso relacionado).

2. Asegúrate de que los paquetes estén correctamente organizados según la estructura del proyecto.

## Uso

### Configuración Básica

1. **Crea una instancia de `DialogDriver`:**

```kotlin
val dialogDriver = DialogDriver(context)
```

2. **Configura los parámetros deseados:**

```kotlin
dialogDriver
    .setTitleStyle("Mi Título", 20f) // Establecer título y tamaño de fuente
    .setDescription("Este es el contenido del diálogo.")
    .setPositiveButton("Aceptar") {
        // Acción al presionar el botón positivo
    }
    .setDialogBackgroundColor("#FFFFFF") // Fondo del diálogo
```

3. **Construye y muestra el diálogo:**

```kotlin
dialogDriver.build()
```

### Ejemplo Completo: Diálogo Simple

```kotlin
val dialogDriver = DialogDriver(this)
    .setTitleStyle("Confirmación", 24f)
    .setDescription("¿Desea continuar?")
    .setPositiveButton("Confirmar") {
        Toast.makeText(this, "Acción confirmada", Toast.LENGTH_SHORT).show()
    }
    .setCancelableOnTouchOutside(true)
    .setDialogBackgroundColor("#ADD8E6")

dialogDriver.build()
```

### Ejemplo Completo: Diálogo con Lista

```kotlin
val dialogDriver = DialogDriver(this)
    .setTitleStyle("Seleccione una opción", 24f)
    .setDescription("Elija un elemento de la lista")
    .setListItems(listOf("Opción 1", "Opción 2", "Opción 3"))
    .setPositiveButton("Aceptar") {
        Toast.makeText(this, "Lista confirmada", Toast.LENGTH_SHORT).show()
    }
    .setDialogBackgroundColor("#ADD8E6")

dialogDriver.build()
```

### Métodos Disponibles

#### Título y Descripción
- `setTitleStyle(title: String?, size: Float?)`: Configura el título y su tamaño de fuente.
- `setDescription(description: String)`: Establece la descripción del diálogo.

#### Botones
- `setPositiveButton(text: String, listener: () -> Unit)`: Configura el texto y la acción del botón positivo.
- `setPositiveButtonColor(color: String)`: Establece el color del texto del botón positivo.

#### Lista de Ítems
- `setListItems(items: List<String>)`: Configura una lista de elementos para mostrar en el diálogo.

#### Estilo y Comportamiento
- `setDialogBackgroundColor(color: String)`: Cambia el color de fondo del diálogo.
- `setCancelableOnTouchOutside(cancelable: Boolean)`: Define si el diálogo se cierra al tocar fuera de él.

#### General
- `build()`: Construye y muestra el diálogo.
- `dismissDialog()`: Cierra el diálogo actual.

## Recursos Requeridos

Asegúrate de incluir los siguientes archivos en tu proyecto:

### custom_simple_dialog.xml
Archivo XML para diálogos simples.

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="center"
    android:id="@+id/dialogRoot">

    <!-- Contenedor para el título y el botón "X" -->
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <!-- Título -->
        <TextView
            android:id="@+id/dialogTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Dialog Title"
            android:textSize="20sp"
            android:textColor="@android:color/black"
            android:layout_centerHorizontal="true"
            android:paddingBottom="8dp" />

        <!-- Botón "X" -->
        <ImageView
            android:id="@+id/dialogCloseButton"
            android:layout_width="24dp"
            android:layout_height="24dp"
            android:src="@android:drawable/ic_menu_close_clear_cancel"
            android:layout_alignParentEnd="true"
            android:layout_marginEnd="8dp"
            android:contentDescription="Close"
            android:clickable="true"
            android:focusable="true" />
    </RelativeLayout>

    <!-- Descripción -->
    <TextView
        android:id="@+id/dialogDescription"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Dialog description goes here"
        android:textSize="16sp"
        android:textColor="@android:color/black"
        android:gravity="center"
        android:paddingBottom="16dp"/>

    <!-- LinearLayout para los botones (horizontal) -->
    <LinearLayout
        android:orientation="horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center">

        <!-- Botón Aceptar -->
        <Button
            android:id="@+id/dialogButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Aceptar"
            android:background="?android:attr/selectableItemBackground"
            android:padding="10dp"
            android:textColor="@android:color/black"
            android:layout_gravity="center" />
    </LinearLayout>
</LinearLayout>
```

### custom_list_dialog.xml
Archivo XML para diálogos con listas.

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="center">

    <!-- Botón de cierre (X) -->
    <ImageView
        android:id="@+id/dialogCloseButton"
        android:layout_width="34dp"
        android:layout_height="34dp"
        android:layout_gravity="end"
        android:src="@android:drawable/ic_menu_close_clear_cancel"
        android:padding="4dp"
        android:contentDescription="Cerrar diálogo" />

    <!-- Título -->
    <TextView
        android:id="@+id/dialogTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Dialog Title"
        android:textSize="20sp"
        android:textColor="@android:color/black"
        android:gravity="center"
        android:paddingBottom="8dp" />

    <!-- Descripción -->
    <TextView
        android:id="@+id/dialogDescription"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Dialog description goes here"
        android:textSize="16sp"
        android:textColor="@android:color/black"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- Lista de elementos -->
    <ListView
        android:id="@+id/dialogList"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:divider="@android:color/darker_gray"
        android:dividerHeight="0.5dp"
        android:paddingBottom="16dp" />

    <!-- Fila para los botones Aceptar y Cancelar -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center"
        android:layout_marginTop="16dp">

        <!-- Botón Aceptar -->
        <Button
            android:id="@+id/dialogButtonYes"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Aceptar"
            android:background="?android:attr/selectableItemBackground"
            android:textColor="@android:color/black"
            android:padding="10dp"
            android:layout_marginEnd="8dp" />
    </LinearLayout>
</LinearLayout>
```



