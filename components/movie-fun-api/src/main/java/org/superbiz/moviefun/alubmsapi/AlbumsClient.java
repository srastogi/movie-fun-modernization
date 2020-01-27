package org.superbiz.moviefun.alubmsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public List<AlbumInfo> getAlbums() {
        ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
        };

        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public AlbumInfo find(long albumId) {
        return restOperations.getForEntity(albumsUrl + "/" + albumId, AlbumInfo.class).getBody();
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
    }
}
