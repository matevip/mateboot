/**
 * @description 自动import导入所有 api 模块
 */

// const files = require.context('./model', false, /\.js$/)
const modules = {}
// files.keys().forEach((key) => {
// 	modules[key.replace(/(\.\/|\.js)/g, '')] = files(key).default
// })
const metas = import.meta.globEager('./model/*.js')
for (let key in metas) {
	let k = key.replace('model/', '')
	modules[k.replace(/(\.\/|\.js)/g, '')] = metas[key].default
}

export default modules
