package com.fragments.ombd_api.adapter

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fragments.ombd_api.POJO.Pelicula
import com.fragments.ombd_api.R
import com.fragments.ombd_api.databinding.ListItemBinding
import com.squareup.picasso.Picasso
import java.util.Locale

class PeliculasAdapter(private val peliculas: ArrayList<Pelicula>): RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ListItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = peliculas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculas[position]

        with(holder) {
            binding.tituloPelicula.text = pelicula.getTitulo()
            binding.anoPelicula.text = pelicula.getAno()
            Picasso.get().load(pelicula.getPoster()).into(binding.imagenPelicula)
        }


    }


}