package com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.children.NavigationSource
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.KSerializer

/**
 * Возвращает статус страницы ACTIVE, если она выбрана, и INACTIVE если нет.
 *
 * Это требуется для BottomSheet навгиации на основе Pages,
 * чтобы было видно весь стек открытых BottomSheet.
 *
 * В отличие от стандартного [com.arkivanov.decompose.router.pages.getDefaultPageStatus],
 * который возвращает DESTROYED для всех страниц, которые не являются соседями выбранной страницы.
 */
fun getBottomSheetPageStatus(index: Int, pages: Pages<*>): ChildNavState.Status {
    return if (index == pages.selectedIndex) {
        ChildNavState.Status.RESUMED
    } else {
        ChildNavState.Status.CREATED
    }
}

/**
 * Создает ChildPages для использования с BottomSheet.
 */
inline fun <reified C : Any, T : Any> ComponentContext.bottomSheetPages(
    source: NavigationSource<PagesNavigation.Event<C>>,
    serializer: KSerializer<C>?,
    noinline initialPages: () -> Pages<C> = { Pages() },
    key: String = "BottomSheetChildPages",
    noinline pageStatus: (index: Int, Pages<C>) -> ChildNavState.Status = ::getBottomSheetPageStatus,
    handleBackButton: Boolean = false,
    noinline childFactory: (configuration: C, ComponentContext) -> T,
): Value<ChildPages<C, T>> =
    childPages(
        source = source,
        serializer = serializer,
        initialPages = initialPages,
        key = key,
        pageStatus = pageStatus,
        handleBackButton = handleBackButton,
        childFactory = childFactory,
    )
