package com.example.networktest

import retrofit2.Response
import retrofit2.http.GET

//TODO: CREIAMO L'INTERFACCIA CHE VERRA' UTILIZZATA DA RETROFIT PER CREARE LE CHIAMATE DI RETE VERE E PROPRIE
// UN'INTERFACCIA PUO' CONTENERE UN NUMERO VARIABILE DI FUNZIONI (CHIAMATE DI RETE)

interface CatEndPoint {

    @GET("fact")
    suspend fun networkCall(): Response<CatData>

}