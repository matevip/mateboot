//hex颜色转rgb颜色
export const hexToRgb = (str: any): string => {
  str = str.replace("#", "");
  var hxs = str.match(/../g);
  for (var i = 0; i < 3; i++) hxs[i] = parseInt(hxs[i], 16);
  return hxs;
};
//rgb颜色转hex颜色
export const rgbToHex = (a: any, b: any, c: any): string => {
  var hexs = [a.toString(16), b.toString(16), c.toString(16)];
  for (var i = 0; i < 3; i++) {
    if (hexs[i].length == 1) hexs[i] = "0" + hexs[i];
  }
  return "#" + hexs.join("");
};
//加深
export const darken = (color: any, level: any): string => {
  var rgbc: any = hexToRgb(color);
  for (var i = 0; i < 3; i++) rgbc[i] = Math.floor(rgbc[i] * (1 - level));
  return rgbToHex(rgbc[0], rgbc[1], rgbc[2]);
};
//变淡
export const lighten = (color: any, level: any) => {
  var rgbc: any = hexToRgb(color);
  for (var i = 0; i < 3; i++)
    rgbc[i] = Math.floor((255 - rgbc[i]) * level + rgbc[i]);
  return rgbToHex(rgbc[0], rgbc[1], rgbc[2]);
};
