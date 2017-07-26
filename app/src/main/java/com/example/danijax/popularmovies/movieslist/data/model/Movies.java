
package com.example.danijax.popularmovies.movieslist.data.model;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Movies {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private Object mBackdropPath;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("id")
    private Long mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private Object mPosterPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Movies() {
    }

    public Movies(String mTitle) {
        this.mTitle = mTitle;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public Object getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(Object backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        mGenreIds = genreIds;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public Object getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(Object posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(mAdult, movies.mAdult) &&
                Objects.equals(mBackdropPath, movies.mBackdropPath) &&
                Objects.equals(mGenreIds, movies.mGenreIds) &&
                Objects.equals(mId, movies.mId) &&
                Objects.equals(mOriginalLanguage, movies.mOriginalLanguage) &&
                Objects.equals(mOriginalTitle, movies.mOriginalTitle) &&
                Objects.equals(mOverview, movies.mOverview) &&
                Objects.equals(mPopularity, movies.mPopularity) &&
                Objects.equals(mPosterPath, movies.mPosterPath) &&
                Objects.equals(mReleaseDate, movies.mReleaseDate) &&
                Objects.equals(mTitle, movies.mTitle) &&
                Objects.equals(mVideo, movies.mVideo) &&
                Objects.equals(mVoteAverage, movies.mVoteAverage) &&
                Objects.equals(mVoteCount, movies.mVoteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAdult, mBackdropPath, mGenreIds, mId, mOriginalLanguage,
                mOriginalTitle, mOverview, mPopularity, mPosterPath, mReleaseDate, mTitle, mVideo,
                mVoteAverage, mVoteCount);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movies{");
        sb.append("mAdult=").append(mAdult);
        sb.append(", mBackdropPath=").append(mBackdropPath);
        sb.append(", mGenreIds=").append(mGenreIds);
        sb.append(", mId=").append(mId);
        sb.append(", mOriginalLanguage='").append(mOriginalLanguage).append('\'');
        sb.append(", mOriginalTitle='").append(mOriginalTitle).append('\'');
        sb.append(", mOverview='").append(mOverview).append('\'');
        sb.append(", mPopularity=").append(mPopularity);
        sb.append(", mPosterPath=").append(mPosterPath);
        sb.append(", mReleaseDate='").append(mReleaseDate).append('\'');
        sb.append(", mTitle='").append(mTitle).append('\'');
        sb.append(", mVideo=").append(mVideo);
        sb.append(", mVoteAverage=").append(mVoteAverage);
        sb.append(", mVoteCount=").append(mVoteCount);
        sb.append('}');
        return sb.toString();
    }
}
