package com.loneoaktech.tests.groupietest.data

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object DogData {

    val breeds by lazy { Json.decodeFromString<List<DogBreed>>(dogBreedsJson)}
}