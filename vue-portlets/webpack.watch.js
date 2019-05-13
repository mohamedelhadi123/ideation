const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');

module.exports = merge(webpackProductionConfig, {
  output: {
    path: path.resolve(`C:/Users/Hp/Desktop/ProjecetAlioua/platform53/webapps/ideation-vue-portlets/`),
    filename: 'js/[name].bundle.js'
  }
});