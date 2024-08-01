package com.alaershov.mars_colony.bottom_sheet.material3.pages

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages

/**
 * State Holder для списка состояний BottomSheet. Для каждого
 * BototmSheet хранится одновременно его логическое и UI состояние.
 *
 * Этот класс позволяет не пересоздавать UI стейт каждого боттомщита при изменении
 * логического состояния `pages`, а вместо этого хранит запомненные инстансы UI стейтов,
 * и обновляет их так, чтобы BottomSheet не пересоздавались при обновлении `pages`.
 */
@OptIn(ExperimentalDecomposeApi::class)
@Stable
internal class ComponentPagesSheetState {

    private val _componentSheetStateListState: MutableState<List<ComponentSheetState>> =
        mutableStateOf(listOf())
    val componentSheetStateListState: State<List<ComponentSheetState>> =
        _componentSheetStateListState

    /**
     * Обновить список состояний BottomSheet на основе текущего состояния `pages`.
     *
     * Если в список добавились новые компоненты, создаёт для них новые ComponentSheetState.
     *
     * Если состояния в списке изменились, то обновляет существующие инстансы, без пересоздания.
     */
    fun update(pages: ChildPages<*, BottomSheetContentComponent>) {
        val oldList = componentSheetStateListState.value

        // сохраняем компоненты изнутри ComponentSheetState старого списка, потому что следующая операция их мутирует
        val oldComponentList = oldList.map { it.componentState.value }

        var newList = pages.items.mapIndexed { index, child ->
            val component = child.instance

            // если нашли уже существующее состояние для текущей позиции в списке, обновляем его,
            // иначе создаём новое
            val componentSheetState = oldList.getOrNull(index)?.apply { updateComponent(component) }
                ?: ComponentSheetState(
                    initialComponent = component,
                )
            componentSheetState
        }

        val newComponentList = newList.map { it.componentState.value }

        // Для поддержки анимированного закрытия верхнего боттомщита
        // если видим, что в новом списке компонентов всё то же самое, как в старом,
        // но удалился последний компонент - вместо его удаления зануляем его
        // содержимое в старом списке. Это тригерит анимацию скрытия BottomSheet, после
        // завершения которой стейт обновится ещё раз, и последний пустой компонент удалится.
        if (oldComponentList.isNotEmpty() && oldComponentList.dropLast(1) == newComponentList) {
            val lastState = oldList.last()
            if (lastState.componentState.value != null) {
                lastState.updateComponent(null)
                newList = newList + lastState
            }
        }
        _componentSheetStateListState.value = newList
    }
}
