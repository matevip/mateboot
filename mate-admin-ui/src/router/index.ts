import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import { ElNotification } from "element-plus";
import config from "@/config";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import systemRouter from "./systemRouter";
import { beforeEach, afterEach } from "./scrollBehavior";
import store from "@/store";

NProgress.configure({ showSpinner: false });

//系统路由
const routes: RouteRecordRaw[] = systemRouter;

// 白名单列表
const whiteList = ["/login"];

//系统特殊路由
const routes_404 = {
  path: "/:pathMatch(.*)*",
  hidden: true,
  component: () => import("@/layout/other/404.vue"),
};
let routes_404_r = () => {};

const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});

//设置标题
document.title = config.APP_NAME;

//判断是否已加载过动态/静态路由
var isGetRouter = false;

// 路由跳转前
router.beforeEach(async (to, from, next) => {
  NProgress.start();

  //动态标题
  document.title = to.meta.title
    ? `${to.meta.title} - ${config.APP_NAME}`
    : `${config.APP_NAME}`;

  // 如果token已存在
  if (store.userStore.token) {
    if (to.path === "/" || to.path === "/login") {
      next("/dashboard");
    } else {
      if (!store.userStore.user.id) {
        try {
          // 获取用户信息
          await store.userStore.getUserInfoAction();
          await store.userStore.getAuthorityListAction();
          console.log(store.userStore.user.id);
        } catch (error) {
          store.userStore?.setToken("");
          next("/login");
          return Promise.reject(error);
        }

        // 获取菜单列表
        const apiMenu = await store.userStore.getMenuListAction();
        const menuList = [...apiMenu];
        var menuRouter = filterAsyncRouter(menuList);
        menuRouter = flatAsyncRoutes(menuRouter, []);
        menuRouter.forEach((item: any) => {
          router.addRoute("layout", item);
        });

        // 添加错误路由
        router.addRoute(routes_404);

        //整页路由处理
        if (to.meta.fullpage) {
          to.matched = [to.matched[to.matched.length - 1]];
        }

        // 保存菜单数据
        // store.userStore.setMenu(routes.concat(menuRouter));

        beforeEach(to, from);

        next({ path: to.path, query: to.query });
      } else {
        next();
      }
    }
  } else {
    // 没有token的情况下，可以进入白名单
    if (whiteList.indexOf(to.path) > -1) {
      next();
    } else {
      routes_404_r;
      next({
        path: "/login",
      });
    }
  }
});

router.afterEach((to: any, from: any) => {
  afterEach(to);
  NProgress.done();
});

router.onError((error) => {
  NProgress.done();
  ElNotification.error({
    title: "路由错误",
    message: error.message,
  });
});

// 根据菜单列表，生成路由数据
function filterAsyncRouter(routerMap: any) {
  const accessedRouters: any = [];
  routerMap.forEach((menu: any) => {
    menu.meta = menu.meta ? menu.meta : {};
    //处理外部链接特殊路由
    if (menu.meta.type == "iframe") {
      menu.meta.url = menu.path;
      menu.path = `/i/${menu.name}`;
    }
    //MAP转路由对象
    const route: RouteRecordRaw = {
      path: menu.path,
      name: menu.name,
      meta: menu.meta,
      redirect: menu.redirect,
      children: [],
      component: loadComponent(menu.component),
    };
    // 有子菜单的情况
    if (menu.children && menu.children.length > 0) {
      route.children?.push(...filterAsyncRouter(menu.children));
    }
    accessedRouters.push(route);
  });
  return accessedRouters;
}

// 加载vue组件
const layoutModules = import.meta.glob("@/views/**/*.vue");

// 根据路径，动态获取vue组件
function loadComponent(path: string): any {
  // if (component) {
  //   return layoutModules[`/src/views/${component}.vue`];
  // } else {
  //   return () => import(`@/layout/other/empty.vue`);
  // }
  return layoutModules[`/src/views/${path}.vue`];
}

// 路由扁平化
function flatAsyncRoutes(routes: RouteRecordRaw[], breadcrumb: string[]) {
  const routerList: RouteRecordRaw[] = [];
  routes.forEach((route: any) => {
    if (route.children) {
      let childrenBreadcrumb: any = [...breadcrumb];
      childrenBreadcrumb.push(route);
      let tmpRoute = { ...route };
      tmpRoute.meta.breadcrumb = childrenBreadcrumb;
      delete tmpRoute.children;
      routerList.push(tmpRoute);
      if (route.children && route.children.length > 0) {
        routerList.push(...flatAsyncRoutes(route.children, childrenBreadcrumb));
      }
    } else {
      let tmpBreadcrumb: any = [...breadcrumb];
      tmpBreadcrumb.push(route);
      route.meta.breadcrumb = tmpBreadcrumb;
      routerList.push(route);
    }
  });
  return routerList;
}

export default router;
