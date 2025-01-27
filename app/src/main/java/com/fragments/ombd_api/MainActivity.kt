package com.fragments.ombd_api

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fragments.ombd_api.API.OMBdAPIService
import com.fragments.ombd_api.POJO.Pelicula
import com.fragments.ombd_api.adapter.PeliculasAdapter
import com.fragments.ombd_api.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var pelisAdapter: PeliculasAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration


    private var arrayPeliculas = ArrayList<Pelicula>()
    private var buscador = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)


        //Coger texto del buscador
        binding.botonBuscar.setOnClickListener {
            arrayPeliculas.clear()
            buscador = binding.buscadorTitulo.text.toString()



        //Corrutina
        lifecycleScope.launch {
            getMoviesfuncion()
        }
        }

        setContentView(binding.root)

        pelisAdapter = PeliculasAdapter(arrayPeliculas)
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)


        binding.recycler.apply {
            adapter = pelisAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
        }
    }






    @SuppressLint("NotifyDataSetChanged")
    private suspend fun getMoviesfuncion() {

        var apiKey = "76021b1e"

        try {
            // Instancia retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Instancia del servicio
            val ombdApiService = retrofit.create(OMBdAPIService::class.java)

            // Llamada a la API
            val response = ombdApiService.getMovies(apiKey,buscador)

            // Verificar si la respuesta es exitosa
                val peliculas = response.body()?.Search ?: emptyList()

                // Imprimir las películas en el log
                for (pelicula in peliculas) {

                    // Crear un objeto Pelicula y agregarlo a la lista
                    var objetoPelicula = Pelicula(pelicula.Title, pelicula.Year, pelicula.Poster)

                    arrayPeliculas.add(objetoPelicula)
                    pelisAdapter.notifyDataSetChanged()
                }

        } catch (e: Exception) {
            // Capturar excepciones y mostrar detalles del error
            Log.e("API", "Error al obtener las películas: ${e.message}")
        }
    }


}