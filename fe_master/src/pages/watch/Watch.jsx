import Navbar from "../../components/navbar/Navbar";
import { useState } from "react";
import "./watch.scss";
import { useLocation } from "react-router-dom";
import YouTube from "react-youtube";
import {
  PlayArrow,
  Add,
  ThumbUpAltOutlined,
  ThumbDownOutlined,
} from "@material-ui/icons";
import { updateMovie } from "../../services/movies.services";
import { MovieList } from "../../services/movies.services";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import movieTrailer from "movie-trailer";


export default function Watch() {
  let movieDetails = useLocation();
  const title = useState(movieDetails.state.row.title)
  const [trailerUrl, setTrailerUrl] = useState();

  const [getmovie, setMovieList] = useState();
  useEffect(() => {
    MovieList(movieDetails.state.row.category).then((data)=>{
      setMovieList(data.data)
    })
    const urlParams = new URLSearchParams(new URL(movieDetails.state.row.videourl).search)
    setTrailerUrl(urlParams.get("v"))

    
  }, [movieDetails]); 
  if (!getmovie) 
    return null;


  const handleLike = (e, titleM, status) => {

    const value ={titleM, status}

    updateMovie(value)
    alert("Movie is rated "+ status)
  }
  const opts = {
    height: "390",
    width: '100%',
    playerVars: {
      autoplay: 1
    }
  }

  
  return (
    
    <div className="watch">
      <Navbar />

      <YouTube videoId={trailerUrl} opts={opts}></YouTube>

      <div className="watchBody">
        <div className="section-header">
          <h2 className="section-header-text section-item">{movieDetails.state.row.title}</h2>
        </div>
        <div className="more-details-containerMain">
          <div className="more-details-cell cell-download">
            {/* <div className="more-details-label">{movieDetails.state.row.releaseYear} <span className="info-spacer">|</span><span className="maturity-number">{movieDetails.state.row.ageLimit}</span> <span className="info-spacer">|</span> {movieDetails.state.row.timeDuration} <span className="info-spacer">|</span> {movieDetails.state.row.genre}</div> */}
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                {movieDetails.state.row.description}
              </span>
            </div>
          </div>

        </div>
      </div>
      <div className="watchBody">
        <div className="more-details-containerMain">

          <div className="more-details-cell cell-download">
            <img src={movieDetails.state.row.imglink} alt={movieDetails.state.row.title} style={{height:200,width:100,display:"flex", justifyContent:"center"}}/>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                <div className="icons">
                  <PlayArrow className="icon" />
                  <Add className="icon" />
                  <ThumbUpAltOutlined className="icon" onClick={(event) => handleLike(event, movieDetails.state.row.title,'Like')}/>
                  <ThumbDownOutlined className="icon" onClick={(event) => handleLike(event, movieDetails.state.row.title, 'Dislike')}/>
                </div>
              </span>
            </div>
          </div>

        </div>
      </div>

      <div className="watchBody">
        <div className="section-header">
          <h2 className="section-header-text section-item">More Details</h2>
        </div>
        <div className="more-details-container">
          <div className="more-details-cell cell-download">
            <div className="more-details-label">Watch offline</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Available to download
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">Genres</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Argentinian, Courtroom Movies, Documentary Films, Historical
                Documentaries, Crime Movies
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">This movie is...</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">Exciting</span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">Audio</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                English, Spanish - Audio Description, Spanish [Original]
              </span>
            </div>
          </div>
        </div>
      </div>
      <div className="watchBody">
        <div className="section-header">
          <h2 className="section-header-text section-item">More Like This</h2>
        </div>
        <ul
      className="listItem"
    >
        {getmovie.map((row) => (
        <>
          <Link to={{
            pathname: "/watch",
            state:{row}
          }} >
            <li className="movieListMain">
              <img src={row.imglink} alt={row.title}  style={{height:200,width:100,display:"flex", justifyContent:"center", marginLeft:'25%', marginBottom:'10%'}}/>
              <span className="nm-collections-title-name">{row.title}</span>
            </li>
          </Link>
        </>
      ))}
        </ul>
        
      </div>
      <div className="watchBody">
        <div className="section-header">
          <h2 className="section-header-text section-item">Coming Soon</h2>
        </div>
        <div className="more-details-container">
          <div className="more-details-cell cell-download">
            <div className="more-details-label">Lost Ollie</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                A patchwork rabbit with floppy ears and fuzzy memories embarks
                on an epic quest to find his best friend — the young boy he
                desperately loves.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">La legge di Lidia Poët</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Forbidden from practicing law, a woman prepares an appeal to
                overturn the court's decision in this true story of Lidia Poët,
                Italy's first female lawyer.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">
              Guillermo Del Toro's Cabinet of Curiosities
            </div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Acclaimed Academy Award-winning filmmaker Guillermo del Toro
                curates this collection of sinister stories, each more
                horrifying than the next.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">Inside the Mind of a Cat</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Cat experts dive into the mind of the feline to reveal the true
                capabilities of the pouncing pet in this captivating and cuddly
                documentary.
              </span>
            </div>
          </div>
        </div>
        <div className="more-details-container">
          <div className="more-details-cell cell-download">
            <div className="more-details-label">Lost Ollie</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                A patchwork rabbit with floppy ears and fuzzy memories embarks
                on an epic quest to find his best friend — the young boy he
                desperately loves.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">La legge di Lidia Poët</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Forbidden from practicing law, a woman prepares an appeal to
                overturn the court's decision in this true story of Lidia Poët,
                Italy's first female lawyer.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">
              Guillermo Del Toro's Cabinet of Curiosities
            </div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Acclaimed Academy Award-winning filmmaker Guillermo del Toro
                curates this collection of sinister stories, each more
                horrifying than the next.
              </span>
            </div>
          </div>

          <div className="more-details-cell cell-download">
            <div className="more-details-label">Inside the Mind of a Cat</div>
            <div className="more-details-item-container">
              <span className="more-details-item item-download">
                Cat experts dive into the mind of the feline to reveal the true
                capabilities of the pouncing pet in this captivating and cuddly
                documentary.
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
