const mongoose = require('mongoose')

const ratingSchema = new mongoose.Schema({
    rating: {
        type: String,
        trim: true,
    },
    commentTitle: {
        type: String,
        trim: true,
    },
    commentContent: {
        type: String,
        trim: true,
    },
    username: {
        type: String,
        trim: true,
    },
})

module.exports = mongoose.model('Rating', ratingSchema)