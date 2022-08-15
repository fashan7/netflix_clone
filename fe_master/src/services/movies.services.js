import axios from 'axios'
import handler from './handler';

export const updateMovie = async(value) => {
    var username = sessionStorage.getItem("username");
	try {
        const header = {
            'Content-Type': 'application/json',
        }
        var commentTitle = value.titleM
        var rating = value.status
        var data = {commentTitle,rating,username }
        await axios.post(`/nod/rating/rateme`, data, {header});
    } catch (e) {
        throw e;
    }

}

export const MovieList = async (movieType) => { 
    const responseMovies = axios.get(`/nod/movie/get-movie-cat/${movieType}`);
    return responseMovies
};