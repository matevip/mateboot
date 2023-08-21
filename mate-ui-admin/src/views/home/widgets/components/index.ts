import { markRaw } from "vue";
const resultComps: any = {};
// let requireComponent = require.context(
//   "./", // 在当前目录下查找
//   false, // 不遍历子文件夹
//   /\.vue$/ // 正则匹配 以 .vue结尾的文件
// );
// requireComponent.keys().forEach((fileName: any) => {
//   let comp = requireComponent(fileName);
//   resultComps[fileName.replace(/^\.\/(.*)\.\w+$/, "$1")] = comp.default;
// });
// const metas: any = import.meta.glob("./*.vue");
// for (let key in metas) {
//   let k = key.replace("model/", "");
//   resultComps[k.replace(/(\.\/|\.vue)/g, "")] = metas[key].default;
// }
// let requireComponent = require.context(
// 	'./', // 在当前目录下查找
// 	false, // 不遍历子文件夹
// 	/\.vue$/ // 正则匹配 以 .vue结尾的文件
// )

const comps: any = import.meta.globEager('./*.vue');
Object.keys(comps).forEach((element) => {
    const name = element.replace(/\.\/|\.vue/g, '');
    resultComps[name] = comps[element].default;
});

// const comps = import.meta.glob('./**/*.vue')

// for (const key in comps) {
//   comps[key]().then((res:any)=>{
//     // resultComps[key.replace(/(\.\/|\.vue)/g, "")] = res.default;
//     resultComps[key.replace(/(\.\/|\.vue)/g, "")] = res.default;
//     })
// }

export default markRaw(resultComps);
