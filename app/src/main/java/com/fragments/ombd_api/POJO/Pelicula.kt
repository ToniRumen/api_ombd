package com.fragments.ombd_api.POJO

class Pelicula {

    private var titulo: String? = null
    private var ano: String? = null
    private var poster: String? = null

    constructor(titulo: String?, ano: String?, poster: String?) {
        this.titulo = titulo
        this.ano = ano
        this.poster = poster

    }

    fun getTitulo(): String? {
        return titulo
    }
    fun getAno(): String? {
        return ano
    }
    fun getPoster(): String? {
        return poster
    }
    fun setTitulo(titulo: String?) {
        this.titulo = titulo
    }
    fun setAno(ano: String?) {
        this.ano = ano
        }
    fun setPoster(poster: String?) {
        this.poster = poster
    }

}