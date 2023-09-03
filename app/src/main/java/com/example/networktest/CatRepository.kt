package com.example.networktest

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//TODO: CREIAMO L'OBJECT CHE VERRA' UTILIZZATO PER AVVIARE LA CHIAMATA DI RETE
// NELLA FUNZIONE createRetrofitInstance CREIAMO LA VERA E PROPRIA CHIAMATA
// DANDO AL COSTRUTTORE DI RETROFIT UN URL, UN CONVERTITORE E UN CLIENT
// (CHE VERRA' UTILIZZATO PER EFFETTUARE LE CHIAMATE)

//TODO: ALL'INIZIO DELL'OBJECT CREIAMO UNA VARIABILE CHE E' DEL TIPO DELL'INTERFACCIA CHE ABBIAMO CREATO
// CHE HA VALORE NULL
// NELLA FUNZIONE SOTTO DICIAMO ALL'OBJECT (IN SINTESI), SE L'INTERFACCIA NON E' INSTANZIATA ALLORA INSTANZIALA
// E POI FAI LA VERA CHIAMATA DI RETE VERA E PROPRIA. QUESTO SERVE A LIVELLO DI PERFORMANCE, CREARE RETROFIT
// E INSTANZIARE LA NOSTRA INTERFACCIA E' QUALCOSA DI ESTREMAMENTE PESANTE DAL PUNTO DI VISTA COMPUTAZIONALE
// QUINDI LO FACCIAMO SOLAMENTE LA PRIMA VOLTA CHE FACCIAMO UNA CHIAMATA DI RETE.

object CatRepository {

    var catEndPoint: CatEndPoint? = null

    suspend fun networkCall(): Response<CatData>? {
        if (catEndPoint == null){
            catEndPoint = createRetrofitInstance().create(CatEndPoint::class.java)
        }

        return catEndPoint?.networkCall()
    }


    fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS).build()).build()
    }

}