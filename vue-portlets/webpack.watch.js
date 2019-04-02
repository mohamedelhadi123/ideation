const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');
const exoServerPath = "C:/Users/Med Hadi/Desktop/platform-5.2.0-RC06";
const app = 'ideation-vue-portlets';

module.exports = merge(webpackProductionConfig, {
  output: {
    path: path.resolve(`${exoServerPath}/webapps/${app}/`),
    filename: 'js/[name].bundle.js'
  }
});