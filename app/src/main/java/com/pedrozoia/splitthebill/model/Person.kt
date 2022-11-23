package com.pedrozoia.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    var nome: String,
    var valorGasto: String,
    var debito: String,
    var descricao: String,
): Parcelable