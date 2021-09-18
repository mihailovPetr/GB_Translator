package com.example.utils.ui

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {
    // Ссылка на root
    private var rootRef: WeakReference<View>? = null
    // Ссылка на View
    private var viewRef: WeakReference <T>? = null
    // Метод вызывается при каждом обращении к переменной
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        // Получаем root
        val currentRoot = rootGetter()

        if (currentRoot != rootRef?.get() || viewRef == null) {
            if (currentRoot == null) {
                throw IllegalStateException("Cannot get View, there is no root yet")
            }

            viewRef = WeakReference(currentRoot.findViewById(viewId))
            // Сохраняем ссылку на root, чтобы не искать его каждый раз заново
            rootRef = WeakReference(currentRoot)
        }

        checkNotNull(viewRef) { "View with id \"$viewId\" not found in root" }
        // Возвращаем View в момент обращения к ней
        return viewRef!!.get() as T
    }
}

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    // Возвращаем корневую View
    return ViewByIdDelegate({window.decorView.findViewById(android.R.id.content)}, viewId)
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}