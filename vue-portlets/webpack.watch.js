const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');
const exoServerPath = "C:/Users/Med Hadi/Desktop/v5.2 Platform/platform-5.3.x-SNAPSHOT";
const app = 'ideation-vue-portlets';

module.exports = merge(webpackProductionConfig, {
  output: {
    path: path.resolve(`${exoServerPath}/webapps/${app}/`),
    filename: 'js/[name].bundle.js'
  }
});