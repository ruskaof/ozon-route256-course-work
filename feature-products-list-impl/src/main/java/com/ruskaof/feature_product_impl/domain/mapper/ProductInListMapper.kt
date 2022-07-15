package com.ruskaof.feature_product_impl.domain.mapper

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import java.text.DateFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

private val numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"))
private val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.forLanguageTag("ru"))
private val todayDate = LocalDate.now()

fun NumberFormat.shortFormat(number: Long): String {
    return if (number.toString().length >= 4) {
        this.format(number / 1000) + "K"
    } else {
        this.format(number)
    }
}

fun ProductInListDTO.toVO(): ProductInListVO {
    val shippingDate = LocalDate.parse(shippingDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"))

    return ProductInListVO(
        guid = guid,
        image = image,
        name = name,
        price = numberFormat.format(price) + " ₽",
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        viewCounter = numberFormat.shortFormat(viewCounter),
        isExpensive = price >= 100,
        priceBeforeDiscount = numberFormat.format(priceBeforeDiscount) + " ₽",
        discount = if (price == priceBeforeDiscount) null else {
            "-" + ((1 - price / priceBeforeDiscount.toFloat()) * 100).toInt().toString() + " %"
        },
        thereWasDiscount = price != priceBeforeDiscount,
        shippingDate = parseShippingDate(todayDate, shippingDate),
        shippingCompanyName = shippingCompanyName,
        sellerName = sellerName,
        reviewCount = russianReviewCountString(reviewCount)
    )
}

private fun parseShippingDate(today: LocalDate, shippingDate: LocalDate): String {
    val shippingTimeDistance = ChronoUnit.DAYS.between(today, shippingDate)
    if (shippingTimeDistance == 1L) return "завтра"
    if (shippingTimeDistance == 2L) return "послезавтра"
    return shippingDate.getFormattedDateWithoutYear()
}

private fun LocalDate.getFormattedDateWithoutYear(): String {
    val date = Date.from(
        this.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
    )
    val formatted = dateFormat.format(date).split(" ")
    return "${formatted[0]} ${formatted[1]}"
}

private fun russianReviewCountString(num: Long): String {
    val arr = arrayOf("отзыв", "отзыва", "отзывов")
    val result: String = when {
        num % 100 in 5..20 -> arr[2]
        num % 10 == 1L -> arr[0]
        num % 10 in 2..4 -> arr[1]
        else -> arr[2]
    }
    return "$num $result"
}