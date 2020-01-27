package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.alubmsapi.AlbumFixtures;
import org.superbiz.moviefun.alubmsapi.AlbumInfo;
import org.superbiz.moviefun.alubmsapi.AlbumsClient;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;

import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient moviesClient;
    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public HomeController(MoviesClient moviesClient, AlbumsClient albumsClient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesClient;
        this.albumsClient = albumsClient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movieInfo : movieFixtures.load()) {
            moviesClient.addMovie(movieInfo);
        }

        for (AlbumInfo albumInfo : albumFixtures.load()) {
            albumsClient.addAlbum(albumInfo);
        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }
}
