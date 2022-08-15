const express = require('express')
const fetch = require('node-fetch')
const axios = require('axios')

let base_javurl = "http://localhost:8090/netflix/"

const movieDefModel = require('../models/movieDefModel')

const movieModel = require('../models/movieDefModel')

const Router = express.Router()

Router.get('/list-all', async (req, res) => {
    const movie = await movieModel.find({})

    return res.status(200).json(movie)
})

Router.post('/add-movie', async (req, res) => {
    const url = base_javurl.concat('movies')
    const formResponse = req.body

    const header = {
        'Content-Type': 'application/json',
    }
    await axios.post(url, formResponse, {header}).then(function (response) {
        const movieObj = new movieDefModel(formResponse)
        movieObj.save()
        return res.status(200).json(movieObj)
      })
      .catch(function (error) {
        return res.status(400).json({"Oops":"Already have same movie"})
      });;
})

function handleErrors(response) {
    if(!response.ok) {
      throw new Error("Request failed " + response.statusText);
    }
    return response;
}

function jsonConcat(o1, o2) {
    for (var key in o2) {
     o1[key] = o2[key];
    }
    return o1;
   }

Router.get('/get-movie-cat/:category', async(req, res) => {
    
    const {category} = req.params
    var json_result = [];
    const movies = await movieDefModel.find({category: category}, {_id: 0 })
    for(var objjson in movies){
        var objRes = movies[objjson]
        const externalid = objRes.externalId
        const url = base_javurl.concat('get-movie/'+externalid)

        const movieJav = await axios.get(url);
        let responseJava = movieJav.data
        
        const obj = JSON.stringify(objRes)
        
        const ovj = JSON.parse(obj)
        ovj['imglink'] = responseJava['imglink']
        json_result.push(ovj)
    }

    return res.status(200).json(json_result)
})

module.exports = Router
