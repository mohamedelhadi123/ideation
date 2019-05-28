const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');

module.exports = merge(webpackProductionConfig, {
  output: {
    path: path.resolve(`/home/ali/work/binaries/5.3/ideation/platform-5.3.0-M08/webapps/ideation-vue-portlets/`),
    filename: 'js/[name].bundle.js'
  }
});