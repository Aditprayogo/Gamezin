package com.aditprayogo.gamezin.utils

import com.aditprayogo.core.domain.entity.GameData

object DummyGame {
    val gameDataList = listOf(
        GameData(
            "backgroundImage1",
            "clip1",
            listOf("genre1"),
            1,
            "name1",
            60,
            4.5,
            100,
            "2020-01-01",
            "information1"
        ),
        GameData(
            "backgroundImage2",
            "clip2",
            listOf("genre2"),
            2,
            "name2",
            120,
            3.5,
            50,
            "2021-01-01",
            "information2"
        )
    )
}