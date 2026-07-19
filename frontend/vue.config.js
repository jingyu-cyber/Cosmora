module.exports = {
  transpileDependencies: true,
  devServer: { proxy: { '/api': { target: 'http://127.0.0.1:9070', changeOrigin: true } } },
  chainWebpack: config => { config.plugin('html').tap(args => { args[0].title = 'Qiongjing Cosmora'; return args }) }
}