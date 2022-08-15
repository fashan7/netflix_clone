import "./listItem.scss";
import axios from 'axios'
// import {
//   PlayArrow,
//   Add,
//   ThumbUpAltOutlined,
//   ThumbDownOutlined,
// } from "@material-ui/icons";
import { useState } from "react";
import { MovieList } from "../../services/movies.services";
import { Link } from "react-router-dom";
import { useEffect } from "react";

export default function ListItem({ index, movieType }) {
  
  const [getmovie, setMovieList] = useState();
  useEffect(() => {
    MovieList(movieType).then((data)=>{
      setMovieList(data.data)
    })
  }); 
  if (!getmovie) 
    return null;
  return (
    <ul
      className="listItem"
    >
      {getmovie.map((row, index) => (
        <>
          <Link to={{
            pathname: "/watch",
            state:{row}
          }} >
            <li className="movieListMain" key={index}>
              <img src={row.imglink} alt={row.title} style={{height:200,width:100,display:"flex", justifyContent:"center", marginLeft:'25%', marginBottom:'10%'}}/>
              <span className="nm-collections-title-name">{row.title}</span>
            </li>
          </Link>
        </>
      ))}
    </ul>
  );
}
