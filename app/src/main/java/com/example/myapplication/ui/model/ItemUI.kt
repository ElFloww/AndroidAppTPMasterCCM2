package com.example.myapplication.ui.model

sealed interface ItemUi {
    data class Item(
        val id: Long,
        val versionName: String,
        val versionNumber: String,
        val versionUrl: String,
        val versionYear: String,
    ) : ItemUi

    data class Header(
        val title: String,
    ) : ItemUi

    data class Footer(
        val numberOfElement: String,
    ) : ItemUi
}