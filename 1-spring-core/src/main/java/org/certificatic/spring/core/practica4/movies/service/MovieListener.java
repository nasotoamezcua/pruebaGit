package org.certificatic.spring.core.practica4.movies.service;

import org.certificatic.spring.core.practica4.movies.api.IMovieFinder;
import org.certificatic.spring.core.practica4.movies.model.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MovieListener {
	
	private @Getter IMovieFinder movieFinder;
	
	public Movie buscaPelicula(String title) {
		return this.movieFinder.find(title);
	}

}
