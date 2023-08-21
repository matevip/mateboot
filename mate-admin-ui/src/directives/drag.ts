function drag(el: any) {
  if (!el) {
    return new Error("v-drag must mount in el");
  }
  let oDiv = el; //当前元素
  let firstTime: number = 0;
  let lastTime: number = 0;
  oDiv.onmousedown = function (e: any) {
    //鼠标按下，计算当前元素距离可视区的距离
    let disX = e.clientX - oDiv.offsetLeft;
    let disY = e.clientY - oDiv.offsetTop;
    document.onmousemove = function (e) {
      oDiv.setAttribute("drag-flag", true);
      firstTime = new Date().getTime();
      //通过事件委托，计算移动的距离
      let l = e.clientX - disX;
      let t = e.clientY - disY;

      //移动当前元素
      if (t > 0 && t < document.body.clientHeight - 50) {
        oDiv.style.top = t + "px";
      }
      if (l > 0 && l < document.body.clientWidth - 50) {
        oDiv.style.left = l + "px";
      }
    };
    document.onmouseup = function () {
      lastTime = new Date().getTime();
      if (lastTime - firstTime > 200) {
        oDiv.setAttribute("drag-flag", false);
      }
      document.onmousemove = null;
      document.onmouseup = null;
    };
    // return false不加的话可能导致黏连，就是拖到一个地方时div粘在鼠标上不下来，相当于onmouseup失效
    return false;
  };
}

export default {
  mounted(el: any) {
    drag(el);
  },
  // 在包含组件的VNode及其子组件的VNode更新后调用
  updated(el: any) {
    drag(el);
  },
};
