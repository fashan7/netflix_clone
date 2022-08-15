const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(createProxyMiddleware('/jav', { target: 'http://localhost:8090/netflix/',changeOrigin: true, pathRewrite: { '^/jav': '' } }));
  app.use(createProxyMiddleware('/nod', { target: 'http://127.0.0.1:4500/api/', changeOrigin: true, pathRewrite: { '^/nod': '' } }));
};