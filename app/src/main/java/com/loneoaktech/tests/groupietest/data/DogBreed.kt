package com.loneoaktech.tests.groupietest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogBreed(

    @SerialName("id")
    val id: Int,

    val name: String
)

//val breadListSerializer: KSerializer<List<DogBreed>> = serializer()
//val dogBreeds = Json.decodeFromString( breadListSerializer, dogBreedsJson )
