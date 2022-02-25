package com.loneoaktech.tests.groupietest.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class DogBreed(
    val id: Int,
    val name: String
)

//val breadListSerializer: KSerializer<List<DogBreed>> = serializer()
//val dogBreeds = Json.decodeFromString( breadListSerializer, dogBreedsJson )
