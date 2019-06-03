const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');



module.exports = merge(webpackProductionConfig, {
  output: {
    path: '/home/khouloud/Bureau/Project/platform/webapps/ideation-vue-portlets/',
    filename: 'js/[name].bundle.js'
  }
});
