const express = require('express')

const ratingModel = require('../models/ratingModel')

const Router = express.Router()

Router.get('/get-allratings/:username', async(req, res) => {
    const {username} = req.params

    const rating = await ratingModel.find({username: username})
    return res.status(200).json(rating)
})

Router.post('/rateme', async (req, res) => {
    const formResponse = req.body
    
    const ratingObj = new ratingModel(formResponse)
    await ratingObj.save()
    return res.status(200).json(ratingObj)
})

Router.get('/allrating', async(req, res) => {
    const {userid} = req.params

    const rating = await ratingModel.find({})
    return res.status(200).json(rating)
})



module.exports = Router